package com.example.tomcattest.servlet;

import com.example.tomcattest.model.GenerativeItem;
import com.example.tomcattest.model.Item;
import com.example.tomcattest.model.ItemType;
import com.example.tomcattest.model.StockItem;
import com.example.tomcattest.repository.ItemRepository;
import com.example.tomcattest.util.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "ItemServlet", value = "/items")
public class ItemServlet extends HttpServlet {
//    int id = 0;
//    Group group = new Group(++id, "name");
//    Item item = ItemMock.getItem(GenerativeItem.class);

//        item.setId();
//        item.setBasePrice();
//        item.setName();
//        item.setImageUrl();
//
//        group.addItem(item);
//        storage.addGroup(group);

    private static final String PARAM_TYPE = "type";

    private final ItemRepository itemRepository = ItemRepository.getInstance();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeParam = req.getParameter(PARAM_TYPE);
        resp.setContentType("application/json");
        if (typeParam == null || typeParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing param: " + PARAM_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        ItemType itemType = ItemType.valueOf(typeParam);

        String payload = req.getReader().lines().collect(Collectors.joining());
        Item item;
        switch (itemType) {
            case GENERATIVE:
                item = objectMapper.readValue(payload, GenerativeItem.class);
                break;
            case STOCK:
                item = objectMapper.readValue(payload, StockItem.class);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Wrong type: " + itemType);
                return;
        }

        Integer itemId = URLUtils.getLastPathSegment(req, resp);
        if (itemId == null) return;

        item.setId(itemId);
        if (itemRepository.updateItem(item) == 0) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Resource not found: " + itemId);
        }

        resp.getWriter().write(objectMapper.writeValueAsString(item));
    }

    /**
     * Get all items
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer itemId = URLUtils.getLastPathSegment(req, resp);
        if (itemId == null) return;

        Optional<Item> itemOpt = itemRepository.findItemById(itemId);
        if (itemOpt.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(itemOpt.get()));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Resource not found: " + itemId);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer itemId = URLUtils.getLastPathSegment(req, resp);
        if (itemId == null) return;

        if (!itemRepository.deleteById(itemId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Resource not found: " + itemId);
        }
    }
}

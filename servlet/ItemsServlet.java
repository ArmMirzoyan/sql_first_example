package com.example.tomcattest.servlet;

import com.example.tomcattest.model.GenerativeItem;
import com.example.tomcattest.model.Item;
import com.example.tomcattest.model.ItemType;
import com.example.tomcattest.model.StockItem;
//import com.example.tomcattest.repository.ItemJdbcRepository;
import com.example.tomcattest.repository.DataAccessObject.ItemHibernateRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "ItemsServlet", value = "/generative-items")
public class ItemsServlet extends HttpServlet {

    private static final String PARAM_TYPE = "type";

    //private final ItemJdbcRepository itemJdbcRepository = new ItemJdbcRepository();
    ItemHibernateRepo itemHibernateRepo = new ItemHibernateRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

//        long id = itemJdbcRepository.getById(item);
//        item.setId(id);

        resp.getWriter().write(objectMapper.writeValueAsString(item));
    }

    /**
     * Get all items
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().write(objectMapper.writeValueAsString(itemHibernateRepo.getAllItems()));
    }
}
package com.example.tomcattest.servlet;

import com.example.tomcattest.util.Hibernate.DataAccessObject.HibernateGroupRepo;
import com.example.tomcattest.model.Group;
import com.example.tomcattest.util.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "GroupServlet", urlPatterns = "/group/*")
public class GroupServlet extends HttpServlet {

    //private final GroupJdbcRepository groupJdbcRepository = new GroupJdbcRepository();
    private final HibernateGroupRepo hibernateGroupRepo = new HibernateGroupRepo();

    /**
     * Get all groups
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Integer groupId = URLUtils.getLastPathSegment(req, resp);
        if (groupId == null) return;

        Optional<Group> groupOpt = Optional.ofNullable(hibernateGroupRepo.getById(groupId));
        if (groupOpt.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(groupOpt.get()));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Resource not found: " + groupId);
        }
    }
}

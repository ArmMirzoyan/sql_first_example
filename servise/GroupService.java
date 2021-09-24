package com.example.tomcattest.servise;

import com.example.tomcattest.model.Group;

import java.util.List;

public interface GroupService {
    void add(Group group);

    List<Group> getAll();

    void removeById(int id);

    Group getById(int id);
}

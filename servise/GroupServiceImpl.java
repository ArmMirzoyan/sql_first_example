package com.example.tomcattest.servise;

import com.example.tomcattest.model.Group;
import com.example.tomcattest.repository.GroupHibernateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceImpl implements GroupService {

    private GroupHibernateRepository groupHibernateRepository;


    public GroupServiceImpl(GroupHibernateRepository groupHibernateRepository) {
        this.groupHibernateRepository = groupHibernateRepository;
    }

    @Override
    public void add(Group group) {
        groupHibernateRepository.add(group);
    }

    @Override
    public List<Group> getAll() {
        return groupHibernateRepository.getAll();
    }

    @Override
    public void removeById(int id) {
        groupHibernateRepository.removeById(id);
    }

    @Override
    public Group getById(int id) {
        return groupHibernateRepository.getById(id);
    }
}

package faust.service;

import faust.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroups();

    List<String> getGroupNames();

    Group getGroup(int groupId);

    void saveGroup(Group group);

    void deleteGroup(int groupId);
}

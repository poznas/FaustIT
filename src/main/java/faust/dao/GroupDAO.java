package faust.dao;

import faust.entity.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> getGroups();

    Group getGroup(int groupId);

    void saveGroup(Group group);

    void deleteGroup(int groupId);

    Group getGroup(String userGroupName);
}

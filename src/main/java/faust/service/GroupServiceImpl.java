package faust.service;

import faust.dao.GroupDAO;
import faust.dao.UserDAO;
import faust.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;
    private final UserDAO userDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO, UserDAO userDAO) {
        this.groupDAO = groupDAO;
        this.userDAO = userDAO;
    }


    public List<Group> getGroups() {
        List<Group> groups = groupDAO.getGroups();
        for (Group group : groups) {
            group.fillUserNames();
        }
        return groups;
    }


    public List<String> getGroupNames() {
        List<String> groupNames = new ArrayList<String>();
        for (Group group : groupDAO.getGroups()) {
            groupNames.add(group.getGroupName());
        }
        return groupNames;
    }

    public Group getGroup(int groupId) {
        return groupDAO.getGroup(groupId).fillUserNames();
    }

    public void saveGroup(Group group) {
        for (String userName : group.getUserNames()) {
            group.addUser(userDAO.getUser(userName));
        }
        groupDAO.saveGroup(group);
    }

    public void deleteGroup(int groupId) {
        groupDAO.deleteGroup(groupId);
    }
}

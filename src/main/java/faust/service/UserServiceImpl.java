package faust.service;

import faust.dao.GroupDAO;
import faust.dao.UserDAO;
import faust.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final GroupDAO groupDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, GroupDAO groupDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.groupDAO = groupDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        List<User> users = userDAO.getUsers();
        for (User user : users) {
            user.fillGroupNames();
        }
        return users;
    }

    public void removeFromGroup(User user, int groupId) {

    }

    public void saveUser(User user) {
        for (String userGroupName : user.getGroupNames()) {
            user.addGroup(groupDAO.getGroup(userGroupName));
        }
        if (user.isEditingPassword()) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        }
        userDAO.saveUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public User getUser(int userId) {
        return userDAO.getUser(userId).fillGroupNames();
    }

    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<String>();
        for (User user : userDAO.getUsers()) {
            userNames.add(user.getUsername());
        }
        return userNames;
    }

    public void saveNewPassword(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        userDAO.updatePassword(user.getId(), hashedPassword);
    }
}

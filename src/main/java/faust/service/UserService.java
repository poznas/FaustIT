package faust.service;

import faust.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void removeFromGroup(User user, int groupId);

    void saveUser(User user);

    void deleteUser(int userId);

    User getUser(int userId);

    List<String> getUserNames();

    void saveNewPassword(User user);
}

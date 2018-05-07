package faust.dao;

import faust.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void saveUser(User user);

    void deleteUser(int userId);

    User getUser(int userId);

    void updatePassword(int id, String hashedPassword);

    User getUser(String userName);
}

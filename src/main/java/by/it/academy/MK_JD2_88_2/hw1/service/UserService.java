package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.storage.DBUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.UserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.util.*;

public class UserService implements IUserService {

    private static final IUserService instance = new UserService();
    private final IUserStorage storage = DBUserStorage.getInstance();

    private UserService() {
    }

    @Override
    public void createUser(User user) {
        this.storage.add(user);
    }

    @Override
    public List<User> getUsers() {
        return this.storage.getAll();
    }

    @Override
    public User getUserByLogin(String login) {
        return this.storage.get(login);
    }

    @Override
    public int getUserCount() {
        return this.storage.getCount();
    }

    @Override
    public void deleteUserByLogin(String login) {
        this.storage.delete(login);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User user = this.getUserByLogin(login);
        if (isExist(login)) {
            String userPassword = user.getPassword();
            return Objects.equals(userPassword, password);
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String login) {
        return getUserByLogin(login) != null;
    }

    public static IUserService getInstance() {
        return instance;
    }
}

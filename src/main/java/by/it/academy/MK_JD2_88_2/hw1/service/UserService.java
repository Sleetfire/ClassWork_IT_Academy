package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserMessageAuditDecorator;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.util.*;

public class UserService implements IUserService {

    private static final IUserService instance = new UserService();
    //private final IUserStorage storage = DBUserStorage.getInstance();
    //private final IUserStorage storage = HibernateUserStorage.getInstance();
    private final IUserStorage storage = HibernateUserMessageAuditDecorator.getInstance();

    private UserService() {
    }

    @Override
    public void create(User user) {
        this.storage.add(user);
    }

    @Override
    public List<User> getAll() {
        return this.storage.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return this.storage.get(login);
    }

    @Override
    public int getCount() {
        return this.storage.getCount();
    }

    @Override
    public void deleteByLogin(String login) {
        this.storage.delete(login);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User user = this.getByLogin(login);
        if (isExist(login)) {
            String userPassword = user.getPassword();
            return Objects.equals(userPassword, password);
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(String login) {
        return getByLogin(login) != null;
    }

    public static IUserService getInstance() {
        return instance;
    }
}

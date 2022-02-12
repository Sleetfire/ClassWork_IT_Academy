package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService implements IUserService {

    private final List<User> userList = new ArrayList<>();
    private static final IUserService instance = new UserService();

    private UserService() {
    }

    @Override
    public void createUser(User user) {
        this.userList.add(user);
    }

    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(this.userList);
    }

    public static IUserService getInstance() {
        return instance;
    }
}

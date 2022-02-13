package by.it.academy.MK_JD2_88_2.hw1.service;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import java.util.*;

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

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optionalUser = this.userList.stream()
                .filter(user -> Objects.equals(user.getLogin(), login))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public static IUserService getInstance() {
        return instance;
    }
}

package by.it.academy.MK_JD2_88_2.hw1.controllers.mains;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.HibernateUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.time.LocalDate;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {

        IUserStorage storage = HibernateUserStorage.getInstance();
        User user = User.Builder.createBuilder()
                .setLogin("doropei")
                .setPassword("doropei")
                .setName("boris")
                .setBirthday(LocalDate.now())
                .setRgDate(LocalDate.now())
                .build();
        storage.add(user);

        List<User> users = storage.getAll();
        for (User user1 : users) {
            System.out.println(user1.toString());
        }

        System.out.println(storage.getCount());

        System.out.println(storage.get("doropei"));

        storage.delete("doropei");


    }

}

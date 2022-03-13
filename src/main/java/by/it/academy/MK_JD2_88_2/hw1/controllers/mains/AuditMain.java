package by.it.academy.MK_JD2_88_2.hw1.controllers.mains;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.DBAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AuditMain {
    public static void main(String[] args) {

        IAuditUserStorage storage = DBAuditUserStorage.getInstance();

        AuditUser registrationAudit = new AuditUser(LocalDateTime.now(),
                "Регистрация",
                null,
                User.Builder.createBuilder().setLogin("good").build());

        AuditUser updateAudit = new AuditUser(LocalDateTime.now(),
                "Переименование",
                User.Builder.createBuilder().setLogin("good").build(),
                User.Builder.createBuilder().setLogin("good").build());

        try {
            storage.create(registrationAudit, DBInitializer.getInstance().getDataSource().getConnection());
            storage.create(updateAudit, DBInitializer.getInstance().getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<AuditUser> audits = storage.read(Pageable.of(1, 20));

        System.out.println(audits);


    }
}

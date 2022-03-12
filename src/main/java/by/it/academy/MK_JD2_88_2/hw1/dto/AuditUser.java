package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDateTime;

public class AuditUser {

    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private User author;
    private User user;

    public AuditUser(Long id, LocalDateTime dtCreate, String text, User author, User user) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public AuditUser(LocalDateTime dtCreate, String text, User author, User user) {
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", user=" + user +
                '}';
    }
}

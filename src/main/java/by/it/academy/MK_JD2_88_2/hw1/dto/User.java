package by.it.academy.MK_JD2_88_2.hw1.dto;

import java.time.LocalDate;
import java.util.*;

public class User {

    private Long id;
    private String login;
    private String password;
    private String name;
    private LocalDate rgDate;
    private LocalDate birthday;


    public User(Long id, String login, String password, String name, LocalDate rgDate, LocalDate birthday) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.rgDate = rgDate;
        this.birthday = birthday;
    }

    public User(String login, String password, String name, LocalDate rgDate, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.rgDate = rgDate;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRgDate() {
        return rgDate;
    }

    public void setRgDate(LocalDate rgDate) {
        this.rgDate = rgDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(id, user.id)
                && Objects.equals(password, user.password)
                && Objects.equals(name, user.name)
                && Objects.equals(rgDate, user.rgDate)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, rgDate, birthday, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", rgDate=" + rgDate +
                ", birthday=" + birthday +
                '}';
    }

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String name;
        private LocalDate rgDate;
        private LocalDate birthday;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRgDate(LocalDate rgDate) {
            this.rgDate = rgDate;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            return new User(this.id, this.login, this.password, this.name, this.rgDate, this.birthday);
        }
    }
}

package by.it.academy.MK_JD2_88_2.hw1.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "dt_rg")
    private LocalDate rgDate;
    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AuditUserEntity> userAudits = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<AuditUserEntity> authorAudits = new ArrayList<>();

    public User(String login, String password, String name, LocalDate rgDate, LocalDate birthday) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.rgDate = rgDate;
        this.birthday = birthday;
    }

    public User() {
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public List<AuditUserEntity> getUserAudits() {
        return userAudits;
    }

    public void setUserAudits(List<AuditUserEntity> userAudits) {
        this.userAudits = userAudits;
    }

    public List<AuditUserEntity> getAuthorAudits() {
        return authorAudits;
    }

    public void setAuthorAudits(List<AuditUserEntity> authorAudits) {
        this.authorAudits = authorAudits;
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
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(messages, user.messages)
                && Objects.equals(userAudits, user.userAudits)
                && Objects.equals(authorAudits, user.authorAudits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, rgDate, birthday, id, messages, userAudits, authorAudits);
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
                ", messages=" + messages +
                ", userAudits=" + userAudits +
                ", authorAudits=" + authorAudits +
                '}';
    }

    public static class Builder {
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
            return new User(this.login, this.password, this.name, this.rgDate, this.birthday);
        }
    }
}

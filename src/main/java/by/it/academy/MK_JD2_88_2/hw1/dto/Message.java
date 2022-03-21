package by.it.academy.MK_JD2_88_2.hw1.dto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "sender_login")
    private String senderLogin;
    @Column(name = "recipient_login")
    private String recipientLogin;
    @Column(name = "text")
    private String text;
    @Column(name = "date_time")
    private LocalDateTime dateTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Message() {

    }

    public Message(String senderLogin, String recipientLogin, String text, LocalDateTime dateTime) {
        this.senderLogin = senderLogin;
        this.recipientLogin = recipientLogin;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(senderLogin, message.senderLogin)
                && Objects.equals(id, message.id)
                && Objects.equals(recipientLogin, message.recipientLogin)
                && Objects.equals(text, message.text)
                && Objects.equals(dateTime, message.dateTime)
                && Objects.equals(user, message.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderLogin, recipientLogin, text, dateTime, id, user);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderLogin='" + senderLogin + '\'' +
                ", recipientLogin='" + recipientLogin + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                '}';
    }
}

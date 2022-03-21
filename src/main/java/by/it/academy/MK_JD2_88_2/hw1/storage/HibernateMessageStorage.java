package by.it.academy.MK_JD2_88_2.hw1.storage;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import javax.persistence.*;
import java.util.List;

public class HibernateMessageStorage implements IMessageStorage {

    private static IMessageStorage instance = new HibernateMessageStorage();
    private final EntityManagerFactory entityManagerFactory;

    private HibernateMessageStorage() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("by.it.academy.MK_JD2_88_2.jpa");
    }

    @Override
    public void add(Message message) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Message> getAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<Message> messages = entityManager.createQuery("from Message", Message.class).getResultList();
        entityManager.close();
        return messages;
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Message where sender_login = :paramName", Message.class);
        query.setParameter("paramName", login);
        List<Message> messages = query.getResultList();
        return messages;
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from Message where recipient_login = :paramName", Message.class);
        query.setParameter("paramName", login);
        List<Message> messages = query.getResultList();
        return messages;
    }

    @Override
    public int getCount() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Long count = (Long) entityManager.createQuery("select count (*) from Message").getSingleResult();
        entityManager.close();
        return Math.toIntExact(count);
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Message where senderLogin = :paramName");
        query.setParameter("paramName", login);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static IMessageStorage getInstance() {
        return instance;
    }
}

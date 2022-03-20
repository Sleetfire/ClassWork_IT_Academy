package by.it.academy.MK_JD2_88_2.hw1.storage;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import javax.persistence.*;
import java.util.List;

public class HibernateUserStorage implements IUserStorage {

    private static IUserStorage instance = new HibernateUserStorage();
    private final EntityManagerFactory entityManagerFactory;

    private HibernateUserStorage() {
        entityManagerFactory = Persistence.createEntityManagerFactory("by.it.academy.MK_JD2_88_2.jpa");
    }

    @Override
    public void add(User user) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public User get(String login) {
        User user;
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("from User where login = :paramName", User.class);
        query.setParameter("paramName", login);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        entityManager.close();
        return user;
    }

    @Override
    public int getCount() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Long count = (Long) entityManager.createQuery("select count (*) from User").getSingleResult();
        entityManager.close();
        return Math.toIntExact(count);
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from User where login = :paramName");
        query.setParameter("paramName", login);
        int result = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}

package by.it.academy.MK_JD2_88_2.hw1.storage;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUserEntity;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserEntityStorage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateAuditUserStorage implements IAuditUserEntityStorage {

    private static IAuditUserEntityStorage instance = new HibernateAuditUserStorage();
    private final EntityManagerFactory entityManagerFactory;

    private HibernateAuditUserStorage() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("by.it.academy.MK_JD2_88_2.jpa");
    }

    @Override
    public void create(AuditUserEntity auditUserEntity, EntityManager entityManager) {
        entityManager.persist(auditUserEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<AuditUserEntity> read(Pageable pageable) {
        int limit = 0;
        int offset = 0;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }

            if (limit != 0 && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<AuditUserEntity> audits = entityManager.createQuery("from AuditUserEntity", AuditUserEntity.class)
                .getResultList()
                .stream().skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
        return audits;
    }

    @Override
    public void deleteByUserId(int userId) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from AuditUserEntity where user_id = :paramName");
        query.setParameter("paramName", userId);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static IAuditUserEntityStorage getInstance() {
        return instance;
    }
}

package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.AuditUserAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.UserAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api.IEntityDTOAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.AuditUserEntity;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateAuditUserStorage implements IAuditUserStorage {

    private static IAuditUserStorage instance = new HibernateAuditUserStorage();
    private final HibernateDBInitializer hibernateDBInitializer;
    private final IEntityDTOAdapter<AuditUserEntity, AuditUser> auditUserAdapter;

    private HibernateAuditUserStorage() {
        this.hibernateDBInitializer = HibernateDBInitializer.getInstance();
        this.auditUserAdapter = new AuditUserAdapter();
    }


    @Override
    public void create(AuditUser auditUser) {
        EntityManager entityManager = hibernateDBInitializer.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this.auditUserAdapter.dtoToEntity(auditUser));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<AuditUser> read(Pageable pageable) {
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
        EntityManager entityManager = hibernateDBInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditUserEntity> query = cb.createQuery(AuditUserEntity.class);
        Root<AuditUserEntity> root = query.from(AuditUserEntity.class);
        query.select(root);

        List<AuditUserEntity> auditsEntities = entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        List<AuditUser> audits = new ArrayList<>(auditsEntities.size());
        auditsEntities.forEach(auditUserEntity -> audits.add(this.auditUserAdapter.entityToDTO(auditUserEntity)));
        entityManager.close();
        return audits;
    }

    public void deleteByUser(User user) {
        UserEntity userEntity = new UserAdapter().dtoToEntity(user);
        EntityManager entityManager = hibernateDBInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<AuditUserEntity> criteriaDelete = cb.createCriteriaDelete(AuditUserEntity.class);
        Root<AuditUserEntity> root = criteriaDelete.from(AuditUserEntity.class);
        criteriaDelete.where(
                cb.equal(root.get("user"), userEntity)
        );
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static IAuditUserStorage getInstance() {
        return instance;
    }
}

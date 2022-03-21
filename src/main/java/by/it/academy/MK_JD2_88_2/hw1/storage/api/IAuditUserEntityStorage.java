package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUserEntity;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAuditUserEntityStorage {

    void create(AuditUserEntity auditUserEntity, EntityManager entityManager);

    List<AuditUserEntity> read(Pageable pageable);

    void deleteByUserId(int userId);

}

package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;

import java.util.List;

public interface IAuditUserStorage {

    Long create(AuditUser audit);

    List<AuditUser> read(Pageable pageable);

}

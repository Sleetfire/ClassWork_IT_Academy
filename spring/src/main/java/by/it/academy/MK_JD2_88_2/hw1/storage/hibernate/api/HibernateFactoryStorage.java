package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IFactoryStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.HibernateUserMessageAuditDecorator;
import org.springframework.stereotype.Component;

@Component
public class HibernateFactoryStorage implements IFactoryStorage {

    private IHibernateUserStorage hibernateUserStorage;
    private IHibernateMessageStorage hibernateMessageStorage;
    private IHibernateAuditStorage hibernateAuditStorage;
    private HibernateDBInitializer hibernateDBInitializer;
    
    public HibernateFactoryStorage(IHibernateUserStorage hibernateUserStorage, IHibernateMessageStorage hibernateMessageStorage,
                                   IHibernateAuditStorage hibernateAuditStorage, HibernateDBInitializer hibernateDBInitializer) {
        this.hibernateUserStorage = hibernateUserStorage;
        this.hibernateMessageStorage = hibernateMessageStorage;
        this.hibernateAuditStorage = hibernateAuditStorage;
        this.hibernateDBInitializer = hibernateDBInitializer;
    }

    @Override
    public IUserStorage getUserStorage() {
        return new HibernateUserMessageAuditDecorator(hibernateUserStorage, hibernateMessageStorage,
                hibernateAuditStorage, hibernateDBInitializer);
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return new HibernateMessageStorage(hibernateDBInitializer);
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return new HibernateAuditUserStorage(hibernateDBInitializer);
    }
}

package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import org.springframework.stereotype.Component;

@Component
public class ChoiceFactoryStorage implements IFactoryStorage {

    private IFactoryStorage hibernateFactoryStorage;
    private IFactoryStorage sqlFactoryStorage;

    public ChoiceFactoryStorage(IFactoryStorage hibernateFactoryStorage) {
        this.hibernateFactoryStorage = hibernateFactoryStorage;
    }

    @Override
    public IUserStorage getUserStorage() {
        return hibernateFactoryStorage.getUserStorage();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return hibernateFactoryStorage.getMessageStorage();
    }

    @Override
    public IAuditUserStorage getAuditUserStorage() {
        return hibernateFactoryStorage.getAuditUserStorage();
    }

}

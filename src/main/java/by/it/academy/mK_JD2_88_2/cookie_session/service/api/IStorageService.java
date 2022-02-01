package by.it.academy.mK_JD2_88_2.cookie_session.service.api;

import by.it.academy.mK_JD2_88_2.cookie_session.dto.Person;

public interface IStorageService<T, E> {

    /**
     * Сохраняет пользователя в хранилище
     * @param person пользователь
     * @param storage хранилище (может быть Cookie или Session)
     */
    void savePerson(Person person, T storage);

    /**
     * Достаёт пользователя из хранилища
     * @param storage хранилище (может быть Cookie или Session)
     * @return пользователь
     */
    Person getPerson(E storage);

}

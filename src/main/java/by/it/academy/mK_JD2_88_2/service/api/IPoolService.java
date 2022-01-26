package by.it.academy.mK_JD2_88_2.service.api;

import by.it.academy.mK_JD2_88_2.service.api.dto.Pool;

import java.util.List;

public interface IPoolService {

    /**
     * Возвращает список артистов
     * @return список артистов
     */
    List<String> getArtists();

    /**
     * Возвращает список жанров
     * @return список жанров
     */
    List<String> getGenres();

    /**
     * Создает голос
     * @param pool голос
     */
    void createPool(Pool pool);

}

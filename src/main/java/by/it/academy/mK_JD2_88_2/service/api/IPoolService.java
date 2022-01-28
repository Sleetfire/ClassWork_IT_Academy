package by.it.academy.mK_JD2_88_2.service.api;

import by.it.academy.mK_JD2_88_2.service.api.dto.Pool;
import by.it.academy.mK_JD2_88_2.service.api.dto.SavedPool;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    /**
     * Возвращает список сохраненнных голосов
     * @return лист сохраненных голосов
     */
    List<SavedPool> getPools();

    /**
     * Позволяет установить список сохраненных голосов
     * @param pools список сохраненных голосов
     */
    void setPools(List<SavedPool> pools);

    /**
     * Сортирует список на основе компаратора
     * @param comparator компаратор
     * @return отсортированный список сохраненных голсов
     */
    List<SavedPool> getSortedPool(Comparator<SavedPool> comparator);

    /**
     * Сортирует карту отображения
     * @param map карта отображения типа: ключ - строка, значение - целочисленный тип данных
     * @return список объектов в формате "ключ=значение"
     */
    List<Object> mapSort(Map<String, Integer> map);








}

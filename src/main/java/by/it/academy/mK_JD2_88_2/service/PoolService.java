package by.it.academy.mK_JD2_88_2.service;

import by.it.academy.mK_JD2_88_2.service.api.IPoolService;
import by.it.academy.mK_JD2_88_2.service.api.dto.Pool;
import by.it.academy.mK_JD2_88_2.service.api.dto.SavedPool;

import java.time.LocalDateTime;
import java.util.*;

public class PoolService implements IPoolService {

    private List<String> artists = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<SavedPool> pools = new ArrayList<>();

    public PoolService() {
        this.artists.add("Ария");
        this.artists.add("Квин");
        this.artists.add("Металлика");
        this.artists.add("АЦ/ДЦ");

        this.genres.add("Поп");
        this.genres.add("Рок");
        this.genres.add("Джаз");
        this.genres.add("Фонк");
        this.genres.add("Народная");
        this.genres.add("Классика");
        this.genres.add("Рэп");
        this.genres.add("Инди");
        this.genres.add("Блюз");
        this.genres.add("Хэви-метал");
        this.genres.add("Хип-хоп");
    }

    public List<SavedPool> getPools() {
        return this.pools;
    }

    public void setPools(List<SavedPool> pools) {
        this.pools = pools;
    }

    @Override
    public List<String> getArtists() {
        return Collections.unmodifiableList(this.artists);
    }

    @Override
    public List<String> getGenres() {
        return Collections.unmodifiableList(this.genres);
    }

    @Override
    public void createPool(Pool pool) {
        SavedPool savedPool = new SavedPool(LocalDateTime.now(), pool);
        Storage.savedPoolList.add(savedPool); // this.pools.add(savedPool);
    }

    public List<SavedPool> getSortedPool(Comparator<SavedPool> comparator) {
        List<SavedPool> sortedPools = this.pools;
        sortedPools.sort(comparator);
        return sortedPools;
    }

    public List<Object> mapSort(Map<String, Integer> map) {
        List list = new ArrayList(map.entrySet());
        list.sort((Comparator<Map.Entry<String, Integer>>) (a, b) -> b.getValue() - a.getValue());
        return list;
    }

}

package by.it.academy.mK_JD2_88_2.service.api.dto;

import java.time.LocalDateTime;

public class SavedPool {

    private final LocalDateTime time;
    private final Pool pool;

    public SavedPool(LocalDateTime time, Pool pool) {
        this.time = time;
        this.pool = pool;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Pool getPool() {
        return pool;
    }
}

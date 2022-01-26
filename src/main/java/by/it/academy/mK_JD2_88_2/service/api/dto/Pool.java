package by.it.academy.mK_JD2_88_2.service.api.dto;

public class Pool {

    private int artist;
    private int[] genres;
    private String about;

    public Pool(int artist, int[] genres, String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
    }

    public int getArtist() {
        return artist;
    }

    public int[] getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }
}

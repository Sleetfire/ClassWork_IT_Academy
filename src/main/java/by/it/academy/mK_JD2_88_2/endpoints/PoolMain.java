package by.it.academy.mK_JD2_88_2.endpoints;

import by.it.academy.mK_JD2_88_2.service.PoolService;
import by.it.academy.mK_JD2_88_2.service.api.IPoolService;
import by.it.academy.mK_JD2_88_2.service.api.dto.Pool;

import java.util.Arrays;
import java.util.List;

public class PoolMain {
    public static void main(String[] args) {
        PoolService service = new PoolService();
        List<String> artists = service.getArtists();
        List<String> genres = service.getGenres();

        int choiceArtist = 2;
        int[] choiceGenres = new int[3];

        choiceGenres[0] = 0;
        choiceGenres[1] = 5;
        choiceGenres[2] = 8;

        String about = "Привет, я Андрей";

        Pool pool = new Pool(choiceArtist, choiceGenres, about);
        service.createPool(pool);

        System.out.println(service.getPools().get(0).getPool().getArtist());
        System.out.println(Arrays.toString(service.getPools().get(0).getPool().getGenres()));
        System.out.println(service.getPools().get(0).getPool().getAbout());
        System.out.println(service.getPools().get(0).getTime());






    }


}

package by.it.academy.mK_JD2_88_2.endpoints.pools_servlets;

import by.it.academy.mK_JD2_88_2.service.PoolService;
import by.it.academy.mK_JD2_88_2.service.Storage;
import by.it.academy.mK_JD2_88_2.service.api.IPoolService;
import by.it.academy.mK_JD2_88_2.service.api.dto.SavedPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "BestArtistsListServlet", urlPatterns = "/best_artists_list")
public class BestArtistsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        IPoolService service = new PoolService();
        service.setPools(Storage.savedPoolList);
        Map<String, Integer> artistsTop = new HashMap<>();

        List<SavedPool> pools = service.getPools();
        for (SavedPool pool : pools) {
            int artistIndex = pool.getPool().getArtist();
            String artist = service.getArtists().get(artistIndex);
            if (!artistsTop.containsKey(artist)) {
                artistsTop.put(artist, 1);
            } else {
                int count = artistsTop.get(artist);
                artistsTop.replace(artist, ++count);
            }
        }

        List<Object> sorted = service.mapSort(artistsTop);
        writer.write("<h3>Список лучших артистов</h3>");
        writer.write("<ol>");
        for (Object o : sorted) {
            writer.write("<li>" + o.toString() + "</li>");
        }
        writer.write("</ol>");
    }
}

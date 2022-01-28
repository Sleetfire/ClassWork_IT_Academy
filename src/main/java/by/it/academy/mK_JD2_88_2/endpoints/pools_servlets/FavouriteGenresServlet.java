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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FavouriteGenresServlet", urlPatterns = "/favourites_genres_list")
public class FavouriteGenresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        IPoolService service = new PoolService();
        service.setPools(Storage.savedPoolList);

        Map<String, Integer> genresMap = new HashMap<>();
        List<SavedPool> pools = service.getPools();
        for (SavedPool pool : pools) {
            int[] genresIndexes = pool.getPool().getGenres();
            for (int index : genresIndexes) {
                String genre = service.getGenres().get(index);
                if (!genresMap.containsKey(genre)) {
                    genresMap.put(genre, 1);
                } else {
                    int count = genresMap.get(genre);
                    genresMap.replace(genre, ++count);
                }
            }
        }

        List<Object> sorted = service.mapSort(genresMap);
        writer.write("<h3>Список самых любимых жанров</h3>");
        writer.write("<ol>");
        for (Object o : sorted) {
            writer.write("<li>" + o.toString() + "</li>");
        }
        writer.write("</ol>");

    }
}

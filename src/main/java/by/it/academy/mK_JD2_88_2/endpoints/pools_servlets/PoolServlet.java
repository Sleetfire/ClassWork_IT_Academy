package by.it.academy.mK_JD2_88_2.endpoints.pools_servlets;

import by.it.academy.mK_JD2_88_2.service.PoolService;
import by.it.academy.mK_JD2_88_2.service.api.dto.Pool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PoolServlet", urlPatterns = "/pool")
public class PoolServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        PoolService service = new PoolService();


        int artistIndex = parseStringToNumber(req.getParameter("artist"));
        String[] genres = req.getParameterValues("genre");
        if (genres.length < 3) {
            throw new IOException("Некорректный ввод");
        }

        int[] genresIndexes = new int[genres.length];
        for (int i = 0; i < genresIndexes.length; i++) {
            genresIndexes[i] = parseStringToNumber(genres[i]);
        }

        String about = req.getParameter("about");

        Pool pool = new Pool(artistIndex, genresIndexes, about);
        service.createPool(pool);

        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/best_artists_list\">Список лучших исполнителей</a><br>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/favourites_genres_list\">Список любимых жанров</a><br>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/last_short_text\">Последний краткий ответ</a>");

    }

    private int parseStringToNumber(String stringNumber) {
        int number = 0;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }
}

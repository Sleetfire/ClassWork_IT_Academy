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
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "LastShortTextServlet", urlPatterns = "/last_short_text")
public class LastShortTextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        IPoolService service = new PoolService();
        service.setPools(Storage.savedPoolList);
        List<SavedPool> sortedPools = service.getSortedPool(Comparator.comparing(SavedPool::getTime));

        writer.write("<h3>Последние краткие ответы: от самого первого до последнего</h3>");
        writer.write("<ol>");
        for (SavedPool pool : sortedPools) {
            writer.write("<li><b>О себе:</b> " + pool.getPool().getAbout() + ". <b>Время голосования:</b> " +
                    pool.getTime().toString() + ".</li>");
        }
        writer.write("</ol>");
    }
}

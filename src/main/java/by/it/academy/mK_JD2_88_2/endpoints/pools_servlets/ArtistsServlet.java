package by.it.academy.mK_JD2_88_2.endpoints.pools_servlets;

import by.it.academy.mK_JD2_88_2.service.PoolService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ArtistsServlet", urlPatterns = "/artists")
public class ArtistsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PoolService service = new PoolService();
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h2>Список артистов</h2>");
        writer.write("<ol>");
        for (String artist : service.getArtists()) {
            writer.write("<li>" + artist + "</li>");
        }
        writer.write("</ol>");
        writer.write("<a href = \"/MK_JD2-88-2-0.0.0/genres\">Список жанров</a>");

    }
}

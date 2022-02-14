package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService service;

    public SignUpServlet() {
        this.service = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));

        if (isUserCreated(login)) {
            req.setAttribute("userCreated", true);
            req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setBirthday(birthday);
            this.service.createUser(user);
            req.getRequestDispatcher("/views/mainPage.jsp").forward(req, resp);
        }
    }

    private boolean isUserCreated(String login) {
        User user = this.service.getUserByLogin(login);
        return user != null;
    }
}

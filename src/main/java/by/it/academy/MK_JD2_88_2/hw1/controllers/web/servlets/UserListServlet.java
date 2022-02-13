package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.service.MessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.UserService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserListServlet", urlPatterns = "/users")
public class UserListServlet extends HttpServlet {

    private final IUserService service;
    private final IMessageService messageService;

    public UserListServlet() {
        this.service = UserService.getInstance();
        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<User> userList = this.service.getUsers();
        for (User user : userList) {
            writer.write(user.toString() + "<br>");
        }

        if (this.messageService != null) {
            writer.write(messageService.getAllMessages().toString());
        } else {
            writer.write("Равны нулю!");
        }
    }
}

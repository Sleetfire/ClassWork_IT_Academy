package by.it.academy.MK_JD2_88_2.hw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String index(@SessionAttribute(name = "user", required = false) User user, Model model){
        if (user != null) {
            model.addAttribute("user", user);
           return "profile";
        }
        return "mainPage";
    }
}

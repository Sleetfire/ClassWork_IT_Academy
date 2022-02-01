package by.it.academy.mK_JD2_88_2.cookie_session.service;

import by.it.academy.mK_JD2_88_2.cookie_session.dto.Person;
import by.it.academy.mK_JD2_88_2.cookie_session.service.api.IStorageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CookieService implements IStorageService<HttpServletResponse, HttpServletRequest> {

    private static IStorageService instance;

    private static final String FIRST_NAME_COOKIE_KEY = "firstName";
    private static final String LAST_NAME_COOKIE_KEY = "lastName";
    private static final String AGE_COOKIE_KEY = "age";

    private CookieService() {
    }

    @Override
    public void savePerson(Person person, HttpServletResponse storage) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        int age = person.getAge();
        saveCookie(FIRST_NAME_COOKIE_KEY, firstName, storage);
        saveCookie(LAST_NAME_COOKIE_KEY, lastName, storage);
        saveCookie(AGE_COOKIE_KEY, Integer.toString(age), storage);
    }

    @Override
    public Person getPerson(HttpServletRequest storage) {
        String firstName = getValueFromCookie(FIRST_NAME_COOKIE_KEY, storage);
        String lastName = getValueFromCookie(LAST_NAME_COOKIE_KEY, storage);
        int age = Integer.parseInt(getValueFromCookie(AGE_COOKIE_KEY, storage));
        Person person = new Person(firstName, lastName, age);
        return person;
    }

    private void saveCookie(String cookieName, String value, HttpServletResponse resp) {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(cookie);
    }

    private String getValueFromCookie(String cookieName, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookieName, cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return ""; // вроде лучше, чем null
    }

    public static IStorageService getInstance() {
        if (instance == null) {
            return new CookieService();
        }
        return instance;
    }
}

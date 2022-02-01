package by.it.academy.mK_JD2_88_2.cookie_session.service;

import by.it.academy.mK_JD2_88_2.cookie_session.dto.Person;
import by.it.academy.mK_JD2_88_2.cookie_session.service.api.IStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService implements IStorageService<HttpServletRequest, HttpServletRequest> {

    private static IStorageService instance;

    private static final String SESSION_PERSON_KEY = "person";

    private SessionService() {
    }

    @Override
    public void savePerson(Person person, HttpServletRequest storage) {
        HttpSession session = storage.getSession();
        session.setAttribute(SESSION_PERSON_KEY, person);
    }

    @Override
    public Person getPerson(HttpServletRequest storage) {
        HttpSession session = storage.getSession();
        return (Person) session.getAttribute(SESSION_PERSON_KEY);
    }

    public static IStorageService getInstance() {
        if (instance == null) {
            return new SessionService();
        }
        return instance;
    }




}

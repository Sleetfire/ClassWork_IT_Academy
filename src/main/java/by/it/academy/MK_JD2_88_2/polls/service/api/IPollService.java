package by.it.academy.MK_JD2_88_2.polls.service.api;

import by.it.academy.MK_JD2_88_2.polls.service.api.dto.ChoiceWithCounter;
import by.it.academy.MK_JD2_88_2.polls.service.api.dto.Poll;
import by.it.academy.MK_JD2_88_2.polls.service.api.dto.SavedPoll;

import java.util.Comparator;
import java.util.List;

public interface IPollService {

    /**
     * Возвращает список артистов
     * @return список артистов
     */
    List<String> getArtists();

    /**
     * Возвращает список жанров
     * @return список жанров
     */
    List<String> getGenres();

    /**
     * Создает голос
     * @param pool голос
     */
    void createPoll(Poll pool);

    List<ChoiceWithCounter<String>> getArtistTop(Comparator<ChoiceWithCounter>... comparator);

    List<ChoiceWithCounter<String>> getGenreTop(Comparator<ChoiceWithCounter>... comparator);

    List<SavedPoll> getPolls();

    void setPolls(List<SavedPoll> polls);














}

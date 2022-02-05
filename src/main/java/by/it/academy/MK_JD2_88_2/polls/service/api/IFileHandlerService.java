package by.it.academy.MK_JD2_88_2.polls.service.api;

import by.it.academy.MK_JD2_88_2.polls.service.api.dto.SavedPoll;

import java.util.List;

public interface IFileHandlerService {

    List<SavedPoll> readFromFile(String fileName);

    void writeInFile(String fileName, List<SavedPoll> info);


}

package by.it.academy.MK_JD2_88_2.polls.listeners;

import by.it.academy.MK_JD2_88_2.polls.service.FileHandlerService;
import by.it.academy.MK_JD2_88_2.polls.service.PollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IFileHandlerService;
import by.it.academy.MK_JD2_88_2.polls.service.api.IPollService;
import by.it.academy.MK_JD2_88_2.polls.service.api.dto.SavedPoll;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class RunCompletionListener implements ServletContextListener {

    private final IFileHandlerService handlerService;
    private final IPollService pollService = PollService.getInstance();
    private static final String FILE_NAME = "polls.txt";

    public RunCompletionListener() {
        this.handlerService = FileHandlerService.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<SavedPoll> polls = this.handlerService.readFromFile(FILE_NAME);
        if (polls != null) {
            for (SavedPoll poll : polls) {
                this.pollService.createPoll(poll.getPool());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.handlerService.writeInFile(FILE_NAME, this.pollService.getPolls());
    }
}

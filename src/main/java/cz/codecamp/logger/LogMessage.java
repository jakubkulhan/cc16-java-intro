package cz.codecamp.logger;

import cz.codecamp.logger.fragments.LogMessageFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogMessage {

    private final LogLevelEnum level;
    private final String message;
    private final List<LogMessageFragment> fragments;

    private LogMessage(LogLevelEnum level, String message, List<LogMessageFragment> fragments) {
        this.level = level;
        this.message = message;
        this.fragments = Collections.unmodifiableList(fragments);
    }

    public static LogMessage basicLogMessage(LogLevelEnum level, String message) {
        return new LogMessage(level, message, new ArrayList<>());
    }

    public LogMessage addFragment(LogMessageFragment fragment) {
        return new LogMessage(level, message, copyFragmentsWithNewElement(fragment));
    }

    private List<LogMessageFragment> copyFragmentsWithNewElement(LogMessageFragment fragment) {
        List<LogMessageFragment> newFragments = new ArrayList<>(fragments);
        newFragments.add(fragment);
        return Collections.unmodifiableList(newFragments);
    }

    public LogLevelEnum getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public List<LogMessageFragment> getFragments() {
        return new ArrayList<>(fragments);
    }
}

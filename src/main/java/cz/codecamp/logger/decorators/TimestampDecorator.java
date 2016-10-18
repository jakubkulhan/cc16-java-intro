package cz.codecamp.logger.decorators;

import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.fragments.TimeStampFragment;
import cz.codecamp.logger.util.TimeProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class TimestampDecorator implements Function<LogMessage, LogMessage> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");

    @Override
    public LogMessage apply(LogMessage logMessage) {
        return logMessage.addFragment(createFragment(format(generateTimeStamp())));
    }

    private LocalDateTime generateTimeStamp() {
        return TimeProvider.getInstance().getTime();
    }

    private String format(LocalDateTime time) {
        return time.format(formatter);
    }

    private TimeStampFragment createFragment(String time) {
        return new TimeStampFragment(time);
    }
}

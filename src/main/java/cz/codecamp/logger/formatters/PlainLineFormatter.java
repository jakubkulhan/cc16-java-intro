package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.fragments.LogMessageFragment;
import cz.codecamp.logger.loggers.PrintableLogMessageConverter;

public class PlainLineFormatter implements PrintableLogMessageConverter {
    @Override
    public String makePrintable(LogMessage message) {
        StringBuilder builder = new StringBuilder();
        for (LogMessageFragment fragment : message.getFragments()) {
            builder.append(fragment.getPlainLineDisplayedText());
        }
        builder.append("[").append(message.getLevel().name()).append("]: ");
        builder.append(message.getMessage());
        builder.append('\n');
        return builder.toString();
    }
}

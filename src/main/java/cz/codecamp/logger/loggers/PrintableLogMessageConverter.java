package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogMessage;

public interface PrintableLogMessageConverter {
    String makePrintable(LogMessage message);

}

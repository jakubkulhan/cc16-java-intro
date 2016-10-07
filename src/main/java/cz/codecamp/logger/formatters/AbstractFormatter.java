package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.text.SimpleDateFormat;

public abstract class AbstractFormatter implements FormatterInterface {

    final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("YYYY-MM-DDD hh:mm:ss");
}

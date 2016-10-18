package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.filters.LogMessageFilter;
import cz.codecamp.logger.writers.LogMessageWriter;

import java.util.List;
import java.util.function.Function;

import static cz.codecamp.logger.LogMessage.basicLogMessage;

public class SingleDestinationLogger implements PragmaticLoggerInterface {

    private final List<LogMessageFilter> filters;
    private final List<Function<LogMessage, LogMessage>> decorators;
    private final PrintableLogMessageConverter converter;
    private final LogMessageWriter writer;

    public SingleDestinationLogger(List<LogMessageFilter> filters, List<Function<LogMessage, LogMessage>> decorators, PrintableLogMessageConverter converter, LogMessageWriter writer) {
        this.filters = filters;
        this.decorators = decorators;
        this.converter = converter;
        this.writer = writer;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (passesFilters(level, message)) {
            write(convert(decorateMessageContent(level, message)));
        }
    }

    protected boolean passesFilters(LogLevelEnum level, String message) {
        return filters.stream().allMatch(filter -> filter.test(level, message));
    }

    protected LogMessage decorateMessageContent(LogLevelEnum level, String message) {
        Function<LogMessage, LogMessage> combinedDecorators = decorators.stream().reduce(Function.identity(), Function::andThen);
        return combinedDecorators.apply(basicLogMessage(level, message));
    }

    protected String convert(LogMessage message) {
        return converter.makePrintable(message);
    }

    protected void write(String printableMessage) {
        writer.log(printableMessage);
    }


}

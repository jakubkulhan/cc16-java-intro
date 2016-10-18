package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.PragmaticLoggerInterface;
import cz.codecamp.logger.filters.LogLevelFilter;
import cz.codecamp.logger.filters.LogMessageFilter;
import cz.codecamp.logger.formatters.PlainLineFormatter;
import cz.codecamp.logger.writers.LogMessageWriter;
import cz.codecamp.logger.writers.SystemOutLogMessageWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LoggerBuilder {


    public static SingleDestinationLoggerBuilder singleDestinationLogger() {
        return new SingleDestinationLoggerBuilder();
    }

    public static class SingleDestinationLoggerBuilder {
        private LogLevelEnum threshold = LogLevelEnum.DEBUG;
        private List<LogMessageFilter> filters = new ArrayList<>();
        private List<Function<LogMessage, LogMessage>> decorators = new ArrayList<>();
        private PrintableLogMessageConverter converter = new PlainLineFormatter();
        private LogMessageWriter writer = new SystemOutLogMessageWriter();

        public SingleDestinationLoggerBuilder() {

        }

        public SingleDestinationLoggerBuilder withLogLevelThreshold(LogLevelEnum threshold) {
            this.threshold = threshold;
            return this;
        }

        public SingleDestinationLoggerBuilder withDecorators(Function<LogMessage, LogMessage>... decorators) {
            this.decorators.addAll(Arrays.asList(decorators));
            return this;
        }

        public SingleDestinationLoggerBuilder withPrintableLogMessageConverter(PrintableLogMessageConverter converter) {
            this.converter = converter;
            return this;
        }

        public SingleDestinationLoggerBuilder withLogMessageWriter(LogMessageWriter writer) {
            this.writer = writer;
            return this;
        }

        public PragmaticLoggerInterface build() {
            filters.add(new LogLevelFilter(threshold));
            return new SingleDestinationLogger(filters, decorators, converter, writer);
        }
    }
}

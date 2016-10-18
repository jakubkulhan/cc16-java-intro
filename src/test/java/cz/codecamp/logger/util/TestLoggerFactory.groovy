package cz.codecamp.logger.util

import cz.codecamp.logger.LogLevelEnum
import cz.codecamp.logger.LoggerInterface
import cz.codecamp.logger.PragmaticLoggerInterface
import cz.codecamp.logger.decorators.InvokersCodeLocationDecorator
import cz.codecamp.logger.decorators.TimestampDecorator
import cz.codecamp.logger.formatters.JsonFormatter
import cz.codecamp.logger.formatters.PlainLineFormatter
import cz.codecamp.logger.loggers.MultiLogger
import cz.codecamp.logger.writers.DateBasedRotatingFileWriter
import cz.codecamp.logger.writers.SimpleLogMessageFileWriter
import cz.codecamp.logger.writers.SystemErrLogMessageWriter

import static cz.codecamp.logger.loggers.LoggerBuilder.singleDestinationLogger

class TestLoggerFactory {

    PragmaticLoggerInterface getAlmightyLoggerSolution() {
        new MultiLogger(firstLogger(), secondLogger(), thirdLogger(), fourthLogger())
    }

    private PragmaticLoggerInterface firstLogger() {
        singleDestinationLogger()
                .withLogLevelThreshold(LogLevelEnum.DEBUG)
                .withDecorators(new TimestampDecorator(), new InvokersCodeLocationDecorator())
                .withPrintableLogMessageConverter(new PlainLineFormatter())
                .withLogMessageWriter(new SimpleLogMessageFileWriter("first.log"))
                .build()
    }

    private LoggerInterface secondLogger() {
        singleDestinationLogger()
                .withLogLevelThreshold(LogLevelEnum.INFO)
                .withPrintableLogMessageConverter(new JsonFormatter())
                .build()
    }

    private LoggerInterface thirdLogger() {
        singleDestinationLogger()
                .withLogLevelThreshold(LogLevelEnum.WARNING)
                .withLogMessageWriter(new SystemErrLogMessageWriter())
                .build();
    }

    private LoggerInterface fourthLogger() {
        singleDestinationLogger()
                .withLogLevelThreshold(LogLevelEnum.ERROR)
                .withDecorators(new TimestampDecorator(), new InvokersCodeLocationDecorator())
                .withPrintableLogMessageConverter(new JsonFormatter())
                .withLogMessageWriter(new DateBasedRotatingFileWriter("fourth.log"))
                .build()
    }
}

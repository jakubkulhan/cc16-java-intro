package cz.codecamp.logger.loggers;

/**
 * Created by honzapua on 18.10.2016.
 */
public class ImperativeMultiLoggerTest extends MultiLoggerTestSupport {

    @Override
    protected MultiLogger getMultiLogger() throws Exception {
        return new ImperativeMultiLogger(
                createPrintStreamLogger(getPsLogger1()),
                createPrintStreamLogger(getPsLogger2())
        );
    }

}

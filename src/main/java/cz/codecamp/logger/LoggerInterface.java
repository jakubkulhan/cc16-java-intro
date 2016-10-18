package cz.codecamp.logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public interface LoggerInterface {

    void log(LogLevelEnum level, String message);

}

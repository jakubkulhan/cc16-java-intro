package cz.codecamp.logger;

/**
 * Created by honzapua on 16.10.2016.
 */
public class SingleFormatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        String result = String.format("[%s] %s", level.name(), message); //%n new line byla smazana

        return result;
    }
}

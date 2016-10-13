package cz.codecamp.logger;


import java.util.Date;

public class Formatter implements FormatterInterface{

    private StringBuilder builder;

    @Override
    public String format(LogLevelEnum level, String message) {
        builder = new StringBuilder();
        builder.append("[" + level.name() + "]").append(" ");
        builder.append("[" + df.format(new Date()) + "]").append(" ");
        builder.append(message).append("\n");
        return builder.toString();
    }
}

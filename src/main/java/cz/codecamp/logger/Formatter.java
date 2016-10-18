package cz.codecamp.logger;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class Formatter implements FormatterInterface {

    private StringBuilder builder;

    @Override
    public String format(LogLevelEnum level, String message) {
        builder = new StringBuilder();
        builder.append(getClassPath()).append(" ");
        builder.append("[").append(level.name()).append("]").append(" ");
        builder.append("[").append(df.format(new Date())).append("]").append(" ");
        builder.append(message).append("\n");
        return builder.toString();
    }

    private String getClassPath() {

        List<StackTraceElement> elements;


        String path = "";
        elements = Arrays.asList(Thread.currentThread().getStackTrace());

        Iterator<StackTraceElement> iterator = elements.iterator();

        StackTraceElement element;

        while (iterator.hasNext()) {
            if ((element = iterator.next()).getClassName().contains("cz.codecamp")) {
                path = "";
                path += element.getLineNumber();
                path += ":" + element.getClassName();
                path += "." + element.getMethodName();
            }
        }
        return path;
    }
}

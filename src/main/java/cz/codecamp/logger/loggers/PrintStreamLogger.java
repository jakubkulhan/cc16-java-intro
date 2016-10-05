package cz.codecamp.logger.loggers;

public class PrintStreamLogger<T> {

    private T printStream;

    public PrintStreamLogger(T printStream) {
        this.printStream = printStream;
    }
}

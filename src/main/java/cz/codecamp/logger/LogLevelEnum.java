package cz.codecamp.logger;


public enum LogLevelEnum {
    DEBUG,
    INFO,
    WARNING,
    ERROR;

    public boolean isLowerOrEqualTo(LogLevelEnum other) {
        return this.ordinal() <= other.ordinal();
    }
}

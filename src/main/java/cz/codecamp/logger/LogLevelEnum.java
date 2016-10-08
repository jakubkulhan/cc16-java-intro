package cz.codecamp.logger;

public enum LogLevelEnum {
    DEBUG(0),
    INFO(1),
    WARNING(2),
    ERROR(3);

    private final int level;

    LogLevelEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

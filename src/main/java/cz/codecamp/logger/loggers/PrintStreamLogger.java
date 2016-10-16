package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.PrintStream;

/**
 * Created by honzapua on 9.10.2016.
 */

/* tohle FilleLooger Stout logger meaji spolecny kod zde. Kdyz mam neco zobecnit musim identifikovat to co maji spolecne

    presunuti spolecne logiky do teto nove tridy. Presunuti implementaci interfacu

    uprava puvodnich trid na vyuzivani nove spolecne implementace (api, protokol se nemeni)
        pridani konstruktoru, odebrani metod.

    otazka zabezbeceni muze se stat, ze kvuli sobe. Bude package private. Nezmenili jsme protokol.


    */
class PrintStreamLogger implements PragmaticLoggerInterface { /* smazali jsme public je to private package */
    private PrintStream stream;
    private final FormatterInterface formater;
    private final LogLevelEnum threshold;

    //implementace Pragmatic loggeru do interfacu. DOhledaji se PREDCI vsech loggeru. Kde to ma smysl implementovat.

    /**
     *
     * @param stream inicializacni hodnota, ktera se da potom menit pomoci getter a setter
     * @param formater
     * @param threshold
     */
    public PrintStreamLogger(PrintStream stream, FormatterInterface formater, LogLevelEnum threshold) {
        this.stream = stream; // inicializace finalu prave jednou s konstruktorem
        this.formater = formater;
        this.threshold = threshold;
    }

    @Override
    public void log(LogLevelEnum level, String message) { //log je implementace interfacu
        if (stream == null) {
            return;
        }

        if (level.ordinal() < threshold.ordinal()) {
            return;
        }

        String line = formater.format(level, message);
        stream.println(line);
    }

    /**
     * vraci stream do ktereho se loguje. Urceno pouze pro potomky. getter setter aby slo menit soubor za chodu.
     * @return stream
     */
    protected PrintStream getStream() {
        return stream;
    }

    protected void setStream(PrintStream stream) {
        this.stream = stream;
    }
}

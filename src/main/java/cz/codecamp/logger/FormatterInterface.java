package cz.codecamp.logger;

/**
 *Formatuje radku logu. Dela to implementacni trida
 */
public interface FormatterInterface {
    /**
     * Formatuje radku logu, vklada severitu, vlastni text zpravy, pripadne dalsi informace vychazejici z implementace
     * tohoto rozhrani
     *
     * @param level severity
     * @param message vlastni zprava logu
     *
     * @return naformatovana radka = syrovy retezec primo do file. NEobsahuje EOL.
     */
    String format(LogLevelEnum level, String message);
}

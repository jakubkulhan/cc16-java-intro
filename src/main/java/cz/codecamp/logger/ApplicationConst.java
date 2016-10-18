package cz.codecamp.logger;

/**
 * Created by honzapua on 18.10.2016.
 */
public final class ApplicationConst {

    public static final String EOL_SEQUENCE;

    //inicializace konstanty citelne
    static {
        String eolProperty = System.getProperty("line.separator");
        if (eolProperty == null) {
            eolProperty = "\n";
        }
        EOL_SEQUENCE = eolProperty;
    }

    private ApplicationConst() {
    }
}

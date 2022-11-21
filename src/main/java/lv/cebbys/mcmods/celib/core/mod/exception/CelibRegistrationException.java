package lv.cebbys.mcmods.celib.core.mod.exception;

public class CelibRegistrationException extends RuntimeException {
    public CelibRegistrationException(String msg) {
        super(msg);
    }

    public CelibRegistrationException(String msg, Throwable t) {
        super(msg, t);
    }
}

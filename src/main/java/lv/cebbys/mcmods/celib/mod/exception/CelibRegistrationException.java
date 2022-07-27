package lv.cebbys.mcmods.celib.mod.exception;

public class CelibRegistrationException extends RuntimeException {
    public CelibRegistrationException(String msg) {
        super(msg);
    }

    public CelibRegistrationException(String msg, Throwable t) {
        super(msg, t);
    }
}

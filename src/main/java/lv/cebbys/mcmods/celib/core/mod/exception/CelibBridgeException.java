package lv.cebbys.mcmods.celib.core.mod.exception;

public class CelibBridgeException extends RuntimeException {
    public CelibBridgeException(String msg) {
        super(msg);
    }

    public CelibBridgeException(String msg, Throwable t) {
        super(msg, t);
    }
}

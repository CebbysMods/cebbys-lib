package lv.cebbys.mcmods.celib.core.mod.exception;

public class TransformationException extends RuntimeException {
    public TransformationException(String msg) {
        super(msg);
    }

    public TransformationException(String msg, Throwable t) {
        super(msg, t);
    }
}

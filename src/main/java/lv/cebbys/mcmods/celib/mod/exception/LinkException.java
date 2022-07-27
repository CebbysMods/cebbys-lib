package lv.cebbys.mcmods.celib.mod.exception;

public class LinkException extends RuntimeException {
    public LinkException(String msg) {
        super(msg);
    }

    public LinkException(String msg, Throwable t) {
        super(msg, t);
    }
}

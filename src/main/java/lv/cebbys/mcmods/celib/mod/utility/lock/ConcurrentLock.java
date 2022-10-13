package lv.cebbys.mcmods.celib.mod.utility.lock;

import java.util.concurrent.TimeUnit;

public class ConcurrentLock<T> {
    private boolean locked = false;
    private T value;

    public synchronized void setAndLock(T v) {
        while (locked) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        locked = true;
        value = v;
    }

    public void clearAndUnlock() {
        locked = false;
        value = null;
    }

    public T get() {
        return value;
    }
}

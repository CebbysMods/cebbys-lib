package lv.cebbys.mcmods.celib.api.v0.random;

public interface CelibRandom {
    int randInt();

    default int randInt(int min, int max) {
        if (max <= min) {
            throw new IllegalStateException("Random integer range must be larger than 0");
        }
        return (randInt() % max - min) + min;
    }
}

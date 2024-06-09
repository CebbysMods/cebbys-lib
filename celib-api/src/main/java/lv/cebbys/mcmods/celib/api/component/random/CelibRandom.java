package lv.cebbys.mcmods.celib.api.component.random;

public interface CelibRandom {
    int randInt();

    default int randInt(int min, int max) {
        if (max <= min) {
            throw new IllegalStateException("Random integer range must be larger than 0");
        }
        int range = max - min;
        return (randInt() % range) + min;
    }
}

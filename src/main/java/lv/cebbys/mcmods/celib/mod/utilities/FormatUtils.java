package lv.cebbys.mcmods.celib.mod.utilities;

import lv.cebbys.mcmods.celib.mod.exception.TransformationException;

public class FormatUtils {

    public static boolean isInt(String number) {
        try {
            toInt(number);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public static int toInt(String number) throws TransformationException {
        try {

            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new TransformationException("Failed to convert String to int", e);
        }
    }
}

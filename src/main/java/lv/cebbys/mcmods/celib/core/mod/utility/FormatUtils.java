package lv.cebbys.mcmods.celib.core.mod.utility;

import lv.cebbys.mcmods.celib.core.mod.exception.TransformationException;

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

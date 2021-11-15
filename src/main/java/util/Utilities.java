package util;

public class Utilities {

    public static long stripToLong(String s) {
        s = s.replaceAll("[^\\d.]", "");
        return Long.parseLong(s);
    }
}

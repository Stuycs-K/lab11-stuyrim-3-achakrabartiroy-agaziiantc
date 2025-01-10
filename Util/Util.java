package Util;

public class Util {
    public static final String[] topics = new String[]{"Food", "Cellular anatomy", "Human anatomy", "Protein synthesis", "Circulatory system"};

    public static String multstring(String str, int n){
        String out = new String();
        for(int i=0; i<n; i++){
            out += str;
        }
        return out;
    }
    public static String clr(int n){
        return ("\u001b[" + n + "m");
    }
    public static String unclr = "\u001b[0m";
}

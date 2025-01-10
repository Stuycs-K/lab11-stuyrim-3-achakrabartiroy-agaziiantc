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
}

package Util;

public class Util {
    public static String multstring(String str, int n){
        String out = new String();
        for(int i=0; i<n; i++){
            out += str;
        }
        return out;
    }
}

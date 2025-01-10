package Util;

import java.util.List;
import java.util.Scanner;

public class Listener implements Runnable {
    private Scanner sc;
    private List<String> out;
    String thisislowkeyuselessngl;
    public Listener(Scanner sc, List<String> out) {
        this.sc = sc;
        this.out = out;
    }

    @Override
    public void run() {
        while(true){
            thisislowkeyuselessngl = sc.next();
            out.add(thisislowkeyuselessngl);
        }
    }
}

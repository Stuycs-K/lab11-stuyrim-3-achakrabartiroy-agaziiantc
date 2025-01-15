package Util;

import java.util.List;
import java.util.Scanner;

public class Listener implements Runnable {
    private Scanner sc;
    private List<String> out;
    String thisislowkeyuselessngl;
    private int mode;
    public Listener(Scanner sc, List<String> out) {
        this.sc = sc;
        this.out = out;
        mode = 0;
    }

    public void setMode(int n){ //I LOVE ABSTRACTION
        this.mode = n;
    }

    @Override
    public void run() {
        while(true){
            if(this.mode == 1) {
                thisislowkeyuselessngl = sc.next(); //status update this was NOT useless
            }if(this.mode == 0) {
                thisislowkeyuselessngl = sc.nextLine(); //if this if statement is above the other it breaks everything. tbh this implementation is horrible but IT WORKS so I am NOT touching it.
            }
            out.add(thisislowkeyuselessngl);
        }
    }
}

import Render.Screen;
import Util.Listener;
import Util.Sprite;
import Util.TextSprite;
import Util.spriteSheet;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int cd = 750;
    public static void main(String[] args) {
        Screen screen = new Screen(75, 30);
        Thread screenThread = new Thread(screen);
        screenThread.start();
        TextSprite Title = new TextSprite("Crowded Halls V: Stuyrim", 25, 5);
        TextSprite text1 = new TextSprite("Press any button to start!", 24, 7); //will later be replaced to a "press 2 to launch multiplayer" or smth. Idk I might not have the time to implement proper multiplayer
        Sprite k = spriteSheet.K.clone();
        k.move(37, 15);
        screen.addTextSprite(Title);
        screen.addTextSprite(text1);
        screen.addSprite(k);
        ArrayList<String> Input = new ArrayList<>();
        Listener in = new Listener(new Scanner(System.in), Input);
        Thread inThread = new Thread(in);
        inThread.start();
        while(Input.isEmpty()){
            Title.move(0, 1);
            text1.move(0, 1);
            try {TimeUnit.MILLISECONDS.sleep(cd);} catch (InterruptedException ignored) {}
            Title.move(0, -1);
            text1.move(0, -1);
            try {TimeUnit.MILLISECONDS.sleep(cd);} catch (InterruptedException ignored) {}
            //System.out.println("Press any button to start!");
        }
    }
}

import Render.Screen;
import Render.Text;
import Util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Test {
    public static void main(String[] args) {
        //This is just to test if my functions work as I hope they do.
        Screen screen = new Screen(75, 25);
		Sprite yes = spriteSheet.dvd.clone(); //Make sure to use .clone() on sprite creation because otherwise you will just move the template sprite, and you do not want to do that.
        TextSprite title = new TextSprite("abcdefghijklmnop1234567890", 5, 5);
        Thread game = new Thread(screen);
        game.start();
        screen.addSprite(yes);
        screen.addTextSprite(title);
        int vectx = 1;
        int vecty = 1;
        while(true) {
             //one function in and im already in spaghetti hell
            //Text.hideCursor(); //this does not work.

            yes.move(vectx, vecty);
            if(yes.x+3 == Screen.getWidth() || yes.x == 0) {
                vectx *= -1;
            }
            if(yes.y+3 == Screen.getHeight() || yes.y == 0) {
                vecty *= -1;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100); //This is the core game clock that controls the FPS. I don't think it matters very much, so there's not a lot of useful control with it.
            } catch (InterruptedException ignored) {
            }

        }
    }
}

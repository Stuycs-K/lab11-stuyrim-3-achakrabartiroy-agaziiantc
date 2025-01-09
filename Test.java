import Render.Screen;
import Render.Text;
import Util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        //This is just to test if my functions work as I hope they do.
        Screen screen = new Screen(25, 25);
		Sprite yes = spriteSheet.testSprite.clone(); //Make sure to use .clone() on sprite creation because otherwise you will just move the template sprite, and you do not want to do that.
        TextSprite title = new TextSprite(" This might be real", 5, 5);
        Thread game = new Thread(screen);
        game.start();
        screen.addSprite(yes);
        screen.addTextSprite(title);

        while(true) {
             //one function in and im already in spaghetti hell
            //Text.hideCursor(); //this does not work.

            yes.move(1, 1);
            try {
                TimeUnit.MILLISECONDS.sleep(1); //The game runs at 2.5fps but that can be easily changed by editing this line. I severely doubt that processing time will be a major factor
            } catch (InterruptedException ignored) {
            }

        }
    }
}

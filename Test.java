import Render.Screen;
import Render.Text;
import Util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        //This is just to test if my functions work as I hope they do.
        Screen screen = new Screen(10, 25);
		Sprite yes = spriteSheet.testSprite.clone(); //Make sure to use .clone() on sprite creation because otherwise you will just move the template sprite, and you do not want to do that.
        screen.addSprite(yes);

        while(true) {
             //one function in and im already in spaghetti hell
            Text.hideCursor(); //this does not work.
            screen.run();
            yes.move(1, 1);


        }
    }
}

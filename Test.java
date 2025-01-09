import Render.Screen;
import Render.Text;
import Util.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        //This is just to test if my functions work as I hope they do.
        Screen screen = new Screen(10, 25);
		Sprite yes = spriteSheet.testSprite;
        screen.addSprite(yes);

        while(true) {
             //one function in and im already in spaghetti hell
            Text.hideCursor();
            screen.draw();
            yes.move(1, 1);
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            }catch (InterruptedException ignored) {}

        }
    }
}

import Render.Screen;
import Render.Text;
import Util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class whatamIdoing{
    int vectx; int vecty; Sprite sp;
    public whatamIdoing(int vectx, int vecty, Sprite sp){
        this.vectx = vectx;
        this.vecty = vecty;
        this.sp = sp;
    }
}
public class Test {
    public static void main(String[] args) {
        //This is just to test if my functions work as I hope they do.
        //and it has a bouncing dvd. I don't know why. The bouncing dvd does not relate to any functionality that this game will have.
        Screen screen = new Screen(75, 25);
		Sprite yes = spriteSheet.dvd.clone(); //Make sure to use .clone() on sprite creation because otherwise you will just move the template sprite, and you do not want to do that.
        whatamIdoing why = new whatamIdoing(1, 1, yes);
        List<whatamIdoing> ls = new ArrayList<>();
        ls.add(why);
        TextSprite title = new TextSprite("abcdefghijklmnop1234567890", 5, 5);
        List<String> queue = new LinkedList<>(); //possibly the first recorded voluntary usage of linked list in human history
        Thread game = new Thread(screen);
        game.start();
        screen.addSprite(yes);
        screen.addTextSprite(title);
        Listener in = new Listener(new Scanner(System.in), queue);
        Thread inp = new Thread(in);
        inp.start();
        while(true) {
             //one function in and im already in spaghetti hell
            //Text.hideCursor(); //this does not work.
            for(whatamIdoing i : ls) {
                i.sp.move(i.vectx, i.vecty);
                if (i.sp.x + 5 == Screen.getWidth() || i.sp.x == 0) {
                    i.vectx *= -1;
                }
                if (i.sp.y + 3 == Screen.getHeight() || i.sp.y == 0) {
                    i.vecty *= -1;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100); //This is the core game clock that controls the FPS. I don't think it matters very much, so there's not a lot of useful control with it.
            } catch (InterruptedException ignored) {
            }
            if(queue.size() > 0){
                //System.out.println(queue.toString());
                if(queue.getLast().equals("DVD")){
                    Sprite nsp = spriteSheet.dvd.clone();
                    screen.addSprite(nsp);
                    ls.add(new whatamIdoing(1, 1, nsp));
                    queue.removeLast();
                }
            }

        }
    }
}

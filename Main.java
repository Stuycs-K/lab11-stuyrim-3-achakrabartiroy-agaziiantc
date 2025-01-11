import Render.Screen;
import Render.Text;
import Util.Listener;
import Util.Sprite;
import Util.TextSprite;
import Util.spriteSheet;
import Character.Team;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int cd = 750;
    public static void main(String[] args) {
        /*
        Disclaimer:
            This will be extreme levels of voodo magic.
            If screen dimensions are changed even slightly, I think that everything will not just break, but will also crash and explode and burn and go thermonuclear.
            The entire game WILL be hard coded.
            Future me who will have to fix some obscure bug is going to hate past me.
         */
        Screen screen = new Screen(75, 30);
        Thread screenThread = new Thread(screen);
        screenThread.start();
        TextSprite Title = new TextSprite("Crowded Halls V: Stuyrim", 25, 5);
        TextSprite text1 = new TextSprite("Enter any input to start!", 24, 7); //will later be replaced to a "press 2 to launch multiplayer" or smth. Idk I might not have the time to implement proper multiplayer
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
        Input.remove(0); //In the future this could be replaced with a switch case based on input.
        screen.rmTextSprite(Title);
        screen.rmTextSprite(text1);
        Title = null;
        text1 = null; //sentenced to garbage collection.
        for(int i=0; i<10; i++){
            k.move(0, 1);
            try {TimeUnit.MILLISECONDS.sleep(cd / 4);} catch (InterruptedException ignored) {}
        }
        screen.rmSprite(k);
        k = null;
        System.gc(); //you know it got bad when I unironically make a call to System.gc()....

        //Title screen ends here, now for game logic...
        TextSprite prompt = new TextSprite("Input your team name", 1, 5);
        screen.addTextSprite(prompt);
        screen.pause();
        System.out.println("\033\143");
        screen.draw(); //juuust to make sure
        try {TimeUnit.MILLISECONDS.sleep(cd/100);} catch (InterruptedException ignored) {}
        System.out.println("\033\143");
        screen.draw(); //bandaid fix to stupid bug that I don't want to solve normally
        Text.go(11, 4);

        while(Input.isEmpty()){
            prompt.move(0, 1);
            try {TimeUnit.MILLISECONDS.sleep(cd);} catch (InterruptedException ignored) {}
            prompt.move(0, -1);
            try {TimeUnit.MILLISECONDS.sleep(cd);} catch (InterruptedException ignored) {}
            //System.out.println("Press any button to start!");
        }
        Team plrTeam = new Team(Input.remove(0));
        screen.unpause();
        TextSprite output = new TextSprite("Your team name is: " + plrTeam.name, 1, 6);
        screen.addTextSprite(output);
        try {TimeUnit.MILLISECONDS.sleep(cd);} catch (InterruptedException ignored) {}
        TextSprite ln2 = new TextSprite("Great! Let's move on to building your team", 1, 7);
        screen.addTextSprite(ln2);
        try {TimeUnit.MILLISECONDS.sleep(cd*3);} catch (InterruptedException ignored) {}
        screen.rmTextSprite(prompt);
        screen.rmTextSprite(output);
        screen.rmTextSprite(ln2);
        prompt = null;
        output = null;
        ln2 = null;
        System.gc();




    }
}

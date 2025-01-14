import Render.Border;
import Render.Screen;
import Render.Text;
import Util.Listener;
import Util.Sprite;
import Util.TextSprite;
import Util.spriteSheet;
import Character.Team;
import Character.Banfield;
import Character.Sterr;
import Character.Weinwurm;
import Character.AdventurerSheet;
import Character.Adventurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int cd = 7;//50;
    public static Screen screen = new Screen(75, 30); //Do not touch this.
    public static void sendHelp(){ //call this function whenever you touch screen rendering
        try {
            TimeUnit.MILLISECONDS.sleep(cd / 100);
        } catch (InterruptedException ignored) {}
        System.out.println("\033\143"); //profilaktika
        screen.draw(); //bandaid fix to stupid bug that I don't want to solve normally
    }
    public static void sleep(int t){
        try {
            TimeUnit.MILLISECONDS.sleep(t);
        } catch (InterruptedException ignored) {
        }
    }
    public static void main(String[] args) {
        /*
        I chose to use this instead of Game.java, since it might unironically be easier to just code the entire thing without a skeleton than to fit it into a skeleton
        I'll probably end up copying a bunch of stuff over from Game.java
        Disclaimer:
            This will be extreme levels of voodoo magic.
            If screen dimensions are changed even slightly, I think that everything will not just break, but will also crash and explode and burn and go thermonuclear.
            The entire game WILL be hard coded.
            Future me who will have to fix some obscure bug is going to hate current me.
            0 good coding practices will be followed.
            The entire thing is being held up by sticks and stones.
            Viewer discretion is advised.
         */

        Thread screenThread = new Thread(screen);
        screenThread.start();
        TextSprite Title = new TextSprite("Crowded Halls V: Stuyrim", 25, 5);
        TextSprite text1 = new TextSprite("Enter any input to start!", 24, 7); //will later be replaced to a "press 2 to launch multiplayer" or smth. Idk I might not have the time to implement proper multiplayer, or I might just not bother. I probably will just not bother.
        Sprite k = spriteSheet.K.clone();
        k.move(37, 15);
        screen.addTextSprite(Title);
        screen.addTextSprite(text1);
        screen.addSprite(k);
        ArrayList<String> Input = new ArrayList<>();
        Listener in = new Listener(new Scanner(System.in), Input);
        Thread inThread = new Thread(in);
        inThread.start();
        while (Input.isEmpty()) {
            Title.move(0, 1);
            text1.move(0, 1);
            try {
                TimeUnit.MILLISECONDS.sleep(cd);
            } catch (InterruptedException ignored) {
            }
            Title.move(0, -1);
            text1.move(0, -1);
            try {
                TimeUnit.MILLISECONDS.sleep(cd);
            } catch (InterruptedException ignored) {
            }
            //System.out.println("Press any button to start!");
        }
        Input.clear();
        screen.rmTextSprite(Title);
        screen.rmTextSprite(text1);
        Title = null;
        text1 = null; //sentenced to garbage collection.
        for (int i = 0; i < 10; i++) {
            k.move(0, 1);
            try {
                TimeUnit.MILLISECONDS.sleep(cd / 8);
            } catch (InterruptedException ignored) {
            }
        }
        screen.rmSprite(k);
        k = null;
        //System.gc(); //you know it got bad when I unironically make a call to System.gc()....
        //after further consideration I decided that the jvm knows better than me when to fire gc

        //Title screen ends here, now for game logic...
        TextSprite prompt = new TextSprite("Input your team name", 1, 5);
        screen.addTextSprite(prompt);
        screen.pause();
        System.out.println("\033\143");
        sendHelp(); //this function name is slowly starting to become less of a joke and more of a cry for help
        screen.draw(); //juuust to make sure
        sendHelp();
        Text.go(11, 4);

        while (Input.isEmpty()) {
            prompt.move(0, 1);
            try {
                TimeUnit.MILLISECONDS.sleep(cd);
            } catch (InterruptedException ignored) {
            }
            prompt.move(0, -1);
            try {
                TimeUnit.MILLISECONDS.sleep(cd);
            } catch (InterruptedException ignored) {
            }
            //System.out.println("Press any button to start!");
        }

        Team plrTeam = new Team(Input.remove(0)); //very important line

        Input.clear();
        screen.unpause();
        sendHelp();
        TextSprite output = new TextSprite("Your team name is: " + plrTeam.name, 1, 6);
        screen.addTextSprite(output);
        try {
            TimeUnit.MILLISECONDS.sleep(cd);
        } catch (InterruptedException ignored) {
        }
        TextSprite ln2 = new TextSprite("Great! Let's move on to building your team", 1, 7);
        screen.addTextSprite(ln2);
        try {
            TimeUnit.MILLISECONDS.sleep(cd * 3);
        } catch (InterruptedException ignored) {
        }
        screen.rmTextSprite(prompt);
        screen.rmTextSprite(output);
        screen.rmTextSprite(ln2);
        prompt = null;
        output = null;
        ln2 = null;
        //System.gc();
        sendHelp();
        TextSprite[] tsarr = new TextSprite[]{
                new TextSprite("Team builder 2.1.2", 1, 1), //yep that's right I had to refactor this and add a feature before I even committed the first version
                new TextSprite("Choose 3 characters by inputting their number: ", 2, 2),
                new TextSprite("1 - Banfield", 3, 4),
                new TextSprite("2 - Sterr", 3, 5),
                new TextSprite("3 - Weinwurm", 3, 6)
                //more to be added in the future
        };
        screen.addGroupTextSprite(tsarr);
        TextSprite desc[] = new TextSprite[0]; //this is so that I can rm it later
        while(plrTeam.len < 3) {
            Input.clear();
            screen.unpause();
            screen.draw();
            screen.pause();
            sendHelp();
            Text.go(11, 20);
            while (Input.isEmpty()) {
                //just do nothing
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ignored) {}
            }
            sendHelp();
            //System.out.println(Arrays.toString(plrTeam.team));
            String input = Input.getFirst();
            if(input.equals("1") && !(plrTeam.hasA(1))){
                desc = new TextSprite[]{
                        new TextSprite("Banfield-Tank", 15, 13),
                        new TextSprite("Special resource: Phospholipids", 15, 15),
                        new TextSprite("Normal attack: Gives a fake quiz", 15, 17),
                        new TextSprite("Special attack: Makes the target do a large presentation", 15, 19),
                        new TextSprite("Support Ability: Builds a cellular wall", 15, 21),
                        new TextSprite("Input a name to confirm your selection. Input -1 to clear your selection.", 15, 23)
                };

                screen.rmTextSprite(tsarr[Integer.parseInt(input)+1]);
                screen.addGroupTextSprite(desc);
                screen.draw();
                Text.go(11, 20);
                Input.clear(); //cannot be trusting the user to NOT input 2 lines within 1ms.
                while (Input.isEmpty()) {
                    //just do nothing
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException ignored) {}
                }
                if(Input.get(0).equals("-1")){
                    screen.addTextSprite(tsarr[Integer.parseInt(input)+1]);
                    screen.rmGroupTextSprite(desc);
                }else{
                    plrTeam.add(new Banfield(Input.remove(0)));
                    screen.rmGroupTextSprite(desc);
                }



            } else if (input.equals("2") && !(plrTeam.hasA(2))) { //yanderedev is calling
                desc = new TextSprite[]{
                        new TextSprite("Sterr-DPS", 15, 13),
                        new TextSprite("Special resource: Skill issues", 15, 15),
                        new TextSprite("Normal attack: Calls target a skill issue", 15, 17),
                        new TextSprite("Special attack: Gives a test, all enemies have a chance to take damage based on their prepardness.", 15, 19),
                        new TextSprite("Support Ability: Tutors target", 15, 21),
                        new TextSprite("Input a name to confirm your selection. Input -1 to clear your selection.", 15, 23)
                };

                screen.rmTextSprite(tsarr[Integer.parseInt(input)+1]);
                screen.addGroupTextSprite(desc);
                screen.draw();
                Text.go(11, 20);
                Input.clear(); //cannot be trusting the user to NOT input 2 lines within 1ms.
                while (Input.isEmpty()) {
                    //just do nothing
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException ignored) {}
                }
                if(Input.get(0).equals("-1")){
                    screen.addTextSprite(tsarr[Integer.parseInt(input)+1]);
                    screen.rmGroupTextSprite(desc);
                }else{
                    plrTeam.add(new Sterr(Input.remove(0)));
                    screen.rmGroupTextSprite(desc);
                }



            } else if (input.equals("3") && !(plrTeam.hasA(3))) {
                desc = new TextSprite[]{
                        new TextSprite("Weinwurm-Healer", 15, 13),
                        new TextSprite("Special resource: Health Insurance Policies (HIPs)", 15, 15),
                        new TextSprite("Normal attack: Throws pencils at target", 15, 17),
                        new TextSprite("Special attack: Gives target a good Health Insurance policy", 15, 19),
                        new TextSprite("Support Ability: Gives target a free 100", 15, 21),
                        new TextSprite("Input a name to confirm your selection. Input -1 to clear your selection.", 15, 23)
                };

                screen.rmTextSprite(tsarr[Integer.parseInt(input)+1]);
                screen.addGroupTextSprite(desc);
                screen.draw();
                Text.go(11, 20);
                Input.clear(); //cannot be trusting the user to NOT input 2 lines within 1ms.
                while (Input.isEmpty()) {
                    //just do nothing
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException ignored) {}
                }
                if(Input.get(0).equals("-1")){
                    screen.addTextSprite(tsarr[Integer.parseInt(input)+1]);
                    screen.rmGroupTextSprite(desc);
                }else{
                    plrTeam.add(new Weinwurm(Input.remove(0)));
                    screen.rmGroupTextSprite(desc);
                }



            } else {
                sendHelp(); //There is a stupid bug that makes the inputs stop doing anything. I have literally no clue what causes it. I am therefore bringing in the metaphorical nuclear option to deal with this.
                Input.clear();
                input = null;
            }


        }

        screen.rmGroupTextSprite(tsarr);
        screen.rmGroupTextSprite(desc); //if you didn't notice yet I am a huge fan of removing everything 50 times just to make sure it gets removed. It doesn't work sadly.
        sendHelp();
        screen.unpause();
        TextSprite teamText = new TextSprite("Your team is: " + Arrays.toString(plrTeam.team).substring(1, Arrays.toString(plrTeam.team).length()-1), 2, 5);
        screen.addTextSprite(teamText);
        sleep(cd*3);
        for(int i=1; i<50; i++){
            teamText.move(1, 0);
            sleep((cd / 2) / (i+4));
        }
        for(int i=0; i<14; i++){
            teamText.move(1, 0);
            teamText.text = Arrays.copyOfRange(teamText.text, 0, teamText.text.length - 1);
            sleep((cd / 2) / (i+54));
        }
        screen.rmTextSprite(teamText);
        sendHelp();
        Team enemyTeam = AdventurerSheet.randomTeam();
        teamText = new TextSprite("Enemy team is: " + Arrays.toString(enemyTeam.team).substring(1, Arrays.toString(enemyTeam.team).length()-1), 2, 5);
        screen.addTextSprite(teamText);
        sleep(cd*3);
        for(int i=1; i<50; i++){
            teamText.move(1, 0);
            sleep((cd / 2) / (i+4));
        }
        for(int i=0; i<14; i++){
            teamText.move(1, 0);
            teamText.text = Arrays.copyOfRange(teamText.text, 0, teamText.text.length-1);
            sleep((cd / 2) / (i+54));
        }
        screen.rmTextSprite(teamText);
        //the pain never end
        //but this should be the end of the intro screen
        //can start making actual game now.
        //If my group mate is reading this specific sentence then please delete it, otherwise I will know that you didn't even look at the main. I happen to have conveniently placed this next to a very important section, and it's a pretty damn long line so it should be visible enough to a point where you will not miss it if you are just going through the code. Pretty neat diagram right next to this too
        Border midDiv = new Border(1, 38);
        Border topDiv = new Border(0, 10);
        Border topDiv2 = new Border(0, 24);
        screen.addBorder(midDiv);
        screen.addBorder(topDiv);
        screen.addBorder(topDiv2);
        Sprite[] plrsprites = new Sprite[3];
        Sprite[] ensprites = new Sprite[3];
		for(int i=0; i<3; i++){
			Sprite spplr = spriteSheet.Stickman.clone();
			screen.addSprite(spplr);
            plrsprites[i] = spplr;
            plrTeam.team[i].setSprite(spplr);
			spplr.teleport(9 + i * 6, 4);

			Sprite spen = spriteSheet.Stickman.clone();
			screen.addSprite(spen);
            ensprites[i] = spen;
            enemyTeam.team[i].setSprite(spen);
			spen.teleport(47 + i * 6, 4);
		}
		/*
		The screen is to be divided into 6 sections
		=======================================
		=plr sprites       = enemy sprites    =
		======================================= vertical divisor at 38
		=				   =                  = horizontal divisors at 10 and 24
		=plr stats/actions = enemy stats      =
		=======================================
   		=         i        =    o             =
		=======================================
		*/

		ArrayList<TextSprite[]> textwall = new ArrayList<>(); //scope voodoo magic, I need this thing to not be gc'd or bad things will happen.
        //textwall indexing: 0 = plr, 1 = enemy, 2=plr, 3=enemy, 4=plr, 5=enemy. possibly not the best way of doing this but I don't care
		for(int i=0; i<3; i++){
			Adventurer plr = plrTeam.team[i]; //TBH it would probably be a good idea to refactor this to be an accessor method but too much effort for literally no benefit other than maybe escaping Mr K's wrath which I am willing to tank
			Adventurer en = enemyTeam.team[i];
			
			textwall.add(new TextSprite[]{
                    new TextSprite(plr.toString(), 1, 11 + i * 5),
                    new TextSprite("HP: " + plr.getHP() + "/" + plr.getmaxHP() + " (" + (plr.getRegen()[1] + plr.getRCV()) + ")", 1, 12 + i * 5),
                    new TextSprite("Special: " + plr.getSpecial() + "/" + plr.getSpecialMax(), 1, 13 + i * 5),
            });
            textwall.add(new TextSprite[]{
				new TextSprite(en.toString(), 40, 11+i*5),
				new TextSprite("HP: " + en.getHP() + "/" + en.getmaxHP() + " (" + (en.getRegen()[1] + en.getRCV()) + ")", 40, 12+i*5),
				new TextSprite("Special: " + en.getSpecial() + "/" + en.getSpecialMax(), 40, 13+i*5),
			});
			
		}
		for(int i=0; i<textwall.size(); i++){
			screen.addGroupTextSprite(textwall.get(i)); //cursed stuff
		}
		sendHelp();
        //do stuff here
        boolean partyTurn = true;
        int whichPlayer = 0;
        int whichOpponent = 0;
        int turn = 0;
        String input = "";
        for(int i=0; i<3; i++){
            plrTeam.team[i].team = plrTeam;
            enemyTeam.team[i].team = enemyTeam; //definitely the worst way of doing this, but I do not care
        }
        Sprite pointer = spriteSheet.arrow1.clone();
        byte[] promptText1 = "Input 1 to attack, 2 to support, 3 to special attack, 4 to restore special, q to quit, anything else to skip turn".getBytes();
        byte[] promptText2 = "Input a number to target, 0-2 is your team, and 3-5 is the enemy team.".getBytes();
        prompt = new TextSprite(promptText1, 1, 25);
        output = new TextSprite("", 39, 25);
        screen.addSprite(pointer);
        screen.addTextSprite(prompt);
        screen.addTextSprite(output);
        String[] inp = new String[2]; //just so that I don't write this array on every iteration of the loop
        while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))) {
            //Read user input
            for(whichPlayer=0; whichPlayer<3; whichPlayer++){
                sendHelp();
                for(int i=0; i<3; i++){
                    //spaghetti but it does its job
                    plrTeam.team[i].getSprite().RCE(spriteSheet.Stickman);
                    enemyTeam.team[i].getSprite().RCE(spriteSheet.Stickman);
                }

                plrsprites[whichPlayer].RCE(spriteSheet.StickmanAttacking1); //truly one of the animations of all time.
                pointer.teleport(20, 12+whichPlayer*5); //if the hp is longer than 20 characters then we have bigger problems anyways
                //System.out.println();

                //choose move
                prompt.text = promptText1;
                while(Input.isEmpty()){
                    sleep(1); //do nothing, learned fancy term for this today called "fencing"
                }
                input = (Input.removeFirst()); //player can pre-fire turns, this functionality can be removed by clearing input after reading it, so this is a design choice.
                inp[0] = input;
                prompt.text = promptText2;
                if(!"1234".contains(inp[0])){
                    //player did not do a valid turn. could be lenient and let the player reselect turn but will not do that.
                    continue;
                }
                if(inp[0].equals("4")){
                    //if the player does restore special, it should not let the player select a target because that would be stupid.
                    plrTeam.team[whichPlayer].restoreSpecial(3); //I completely forgot all the balancing numbers by now so this is probably not very well balanced but at this point I simply no longer care
                    continue;
                }
                while(Input.isEmpty()){
                    sleep(1); //I really ought to throw this into a separate method but whatever
                }
                //choose target
                input = Input.removeFirst();
                try {
                    inp[1] = input;
                    int targetwrap = Integer.parseInt(inp[1]);
                    Adventurer target;
                    if (targetwrap <= 2) {
                        target = plrTeam.team[targetwrap]; //yeah you can attack your own team. game design is my passion.
                    } else {
                        target = enemyTeam.team[targetwrap % 3];
                    }
                    switch (inp[0]) {
                        case "1":
                            output.text = plrTeam.team[whichPlayer].attack(target).getBytes();
                            target.getSprite().RCE(spriteSheet.StickmanHit1);
                            break;
                        case "2":
                            output.text = plrTeam.team[whichPlayer].support(target).getBytes();
                            target.getSprite().RCE(spriteSheet.StickmanSupport1);
                            break;
                        case "3":
                            output.text = plrTeam.team[whichPlayer].specialAttack(target).getBytes(); //TODO: this doesn't modify stats for some reason. Presumably this is a very big issue.
                            target.getSprite().RCE(spriteSheet.StickmanHit1);
                            break;
                        default:
                            break;
                    }
                }catch(NumberFormatException ignored) {//yeah just do nothing, this would go into default anyways.}
                }
                screen.pause();
                for(int i=0; i<textwall.size(); i++){

                    screen.rmGroupTextSprite(textwall.get(i)); //cursed stuff, I do not like this.
                }
                for(int i=0; i<3; i++){
                    Adventurer plr = plrTeam.team[i]; //TBH it would probably be a good idea to refactor this to be an accessor method but too much effort for literally no benefit other than maybe escaping Mr K's wrath which I am willing to tank
                    Adventurer en = enemyTeam.team[i];

                    textwall.add(new TextSprite[]{
                            new TextSprite((i) + "-" + plr.toString(), 1, 11 + i * 5),
                            new TextSprite("HP: " + plr.getHP() + "/" + plr.getmaxHP() + " (" + (plr.getRegen()[1] + plr.getRCV()) + ")", 1, 12 + i * 5),
                            new TextSprite("Special: " + plr.getSpecial() + "/" + plr.getSpecialMax(), 1, 13 + i * 5),
                    });
                    textwall.add(new TextSprite[]{
                            new TextSprite((i+3) + "-" + en.toString(), 40, 11+i*5),
                            new TextSprite("HP: " + en.getHP() + "/" + en.getmaxHP() + " (" + (en.getRegen()[1] + en.getRCV()) + ")", 40, 12+i*5),
                            new TextSprite("Special: " + en.getSpecial() + "/" + en.getSpecialMax(), 40, 13+i*5),
                    });

                }
                for(int i=0; i<textwall.size(); i++){
                    screen.addGroupTextSprite(textwall.get(i)); //cursed stuff
                }
                screen.unpause();
                sendHelp();
                //im 2000 lines in bro I do not want to write actual algorithms no more :sob:

                sleep(750); //TODO: replace this with the cd variable when done havign cd variable be basically 0

            }
        }

        //cleanup
        for(int i=0; i<textwall.size(); i++){

            screen.rmGroupTextSprite(textwall.get(i)); //cursed stuff, I do not like this.
        }
	
    }
}

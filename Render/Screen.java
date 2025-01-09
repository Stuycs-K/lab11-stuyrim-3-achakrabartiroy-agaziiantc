package Render;

import Util.Util;
import Util.Sprite;
import Util.TextSprite;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Screen implements Runnable {
    private static int width, height; //having more than one would not be useful, so this will be static.
    private OutputStream buffer;
    private static byte[] instance; //rendering is done by iterating over every byte in this array and mapping it to a corresponding character. If you need a character that isn't in the byte range you have bigger problems. It is static because I want it to be the same across multiple screens (in the future if I have the time I will implement LAN multiplayer)
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<TextSprite> textsprites = new ArrayList<>();

    public Screen(int width, int height, int outputtype) {//Using outputtype because if I have the time to do this then later on I want to try adding LAN multiplayer
        this.width = width;
        this.height = height;
        if(width < 3 || height < 3) {
            System.err.println("WARNING: Horrible width and height choice, proceeding is not recommended."); //I could throw an exception here but I am a strong believer of warning the user about doing something stupid but still letting them do it.
        }
        if(outputtype == 1) {
            buffer = new BufferedOutputStream(System.out);
        }
        instance = new byte[width * height];
        Arrays.fill(instance, (byte) ' ');

    }
    public Screen(int width, int height) {
        this(width, height, 1);
    }


    public static int getHeight() {
        return height;
    }
    public static int getWidth() {
        return width;
    }

    public static void setHeight(int height) {
        Screen.height = height;
    }
    public static void setWidth(int width) {
        Screen.width = width;
    }


    private void drawBox(){
            for(int i = 0; i < width; i++){
                this.instance[i] = '=';
            }
            for(int i=0; i<this.height; i++){
                this.instance[i*width] = '=';
                this.instance[i*width + width-1] = '=';
            }
            for(int i = 0; i < width; i++){
                this.instance[((height-1) * width) + i] = '=';
            }

    }
    public void addSprite(Sprite sp){
        sprites.add(sp);
    }
    public void rmSprite(Sprite sp){
        sprites.remove(sp);
    }
    public void addTextSprite(TextSprite ts){textsprites.add(ts);}
    public void rmTextSprite(TextSprite ts){textsprites.remove(ts);}

    private void drawSprite(Sprite sp){

		//offset SHOULD be y * this.width + x. If it is not that then I messed up in drawBox().
        //char[] s = this.buffer.toString().toCharArray();
		for(int i=0; i<sp.map.length; i++){
			//this will iterate through the sprite's map and add the corresponding element of texture to it.
            int loc = (sp.x + sp.map[i][0]) + (sp.y + sp.map[i][1]) * width;
            if(loc >= width * height){
                loc = loc%(width * height);
            }
            instance[loc] = (sp.texture[i]);
        }
	}

    private void drawTextSprite(TextSprite sp){
        //int ln = 0;
        int x = sp.x;
        int y = sp.y; //Cannot have these values as an address
        for(int i=0; i<sp.text.length; i++){
            x++;
            int loc = (x) + (y * width);
            while(instance[loc] == '='){/*certified voodoo magic. possibly the single worst way to go about collision detection. I have a whole comment essay written out but I chose to do this anyways.

                =            loc     =sp.y*width

                this if statement gets triggered if instead of the thing above it's
                =               =sp.y*width loc <this will overflow onto the next line
                if that happens, I want to move down from sp.x instead of overflowing fully.
                 */
                //text overflow handling
                //ln++;
                //you can tell a lot about how well something runs by seeing how many //s it has
                x = sp.x;
                y += 1;
                loc = (x) + (y) * width;
                if(loc >= width * height){
                    return;
                    //This case should probably be handled differently, but I don't know how.
                }
            }
            if(loc >= width * height){
                return;
            }
            instance[loc] = sp.text[i];
        }
    }

    public void draw() {
        Arrays.fill(instance, (byte) ' '); //Reset the map on every iteration
        this.drawBox();
        for(int i=0; i<this.textsprites.size(); i++){
            drawTextSprite(this.textsprites.get(i));
        }
        for(int i=0; i<this.sprites.size(); i++){
            drawSprite(this.sprites.get(i));
        } //Sprites render on top of the text

        try {
            for(int i=0; i<height; i++) {

                for (int j = 0; j < width; j++) {
                    this.buffer.write(instance[i * width + j]); //Throws the byte array into a BufferedOutput, basically lets me print the entire string at once a lot faster
                }
                this.buffer.write("\n".getBytes());
            }

            System.out.println("\033\143"); //just don't run this thing outside of linux to be honest
            this.buffer.flush(); //Outputs the string
            //this.buffer.close(); This line will stop the entire program after printing, do not uncomment unless you need it for debugging.
            }catch(IOException ignored){}
        }
    @Override
    public void run(){ //This will make the rendering script run on a separate thread
        while(true) {
            long time = System.nanoTime();
            draw();
            try {
                long time2 = System.nanoTime();
                System.out.println(((time2 - time) / 1_000_000) + "ms | " + (1_000_000_000 / (time2 - time)) + " FPS"); //Doesn't actually run at this much fps, but it can run at this much fps if the sleep statement below gets made lower
                TimeUnit.MILLISECONDS.sleep(100); //Controls refresh rate
                //System.out.println(sprites.toString());

            } catch (InterruptedException ignored) {
            }
        }
    }





}

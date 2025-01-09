package Render;

import Util.Util;
import Util.Sprite;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Screen implements Runnable {
    private static int width, height; //having more than one would not be useful, so this will be static.
    private OutputStream buffer;
    private byte[] instance; //rendering is done by iterating over every byte in this array and mapping it to a corresponding character. If you need a character that isn't in the byte range you have bigger problems.
    private ArrayList<Sprite> sprites = new ArrayList<>();


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
    public void removeSprite(Sprite sp){
        sprites.remove(sp);
    }
	private void drawSprite(Sprite sp){

		//offset SHOULD be y * this.width + x. If it is not that then I messed up in drawBox().
        //char[] s = this.buffer.toString().toCharArray();
		for(int i=0; i<sp.map.length; i++){
			//this will iterate through the sprite's map and add the corresponding element of texture to it.
            int loc = (sp.x + sp.map[i][0]) + (sp.y + sp.map[i][1]) * width;
            if(loc > width * height){
                loc = loc%(width * height);
            }
            this.instance[loc] = (sp.texture[i]);
        }
	}

    public void draw() {
        Arrays.fill(this.instance, (byte) ' '); //Reset the map on every iteration
        this.drawBox();
        for(int i=0; i<this.sprites.size(); i++){
            drawSprite(this.sprites.get(i));
        }
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
            draw();
            try {
                TimeUnit.MILLISECONDS.sleep(400); //The game runs at 2.5fps but that can be easily changed by editing this line. I severely doubt that processing time will be a major factor
            } catch (InterruptedException ignored) {
            }
        }
    }





}

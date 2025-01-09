package Render;

import Util.Util;

import java.io.*;

public class Screen {
    private static int width, height; //having more than one would not be useful, so this will be static.
    private OutputStream buffer;



    public Screen(int width, int height, int outputtype) {//Using outputtype because if I have the time to do this then later on I want to try adding LAN multiplayer
        this.width = width;
        this.height = height;
        if(width < 3 || height < 3) {
            System.err.println("WARNING: Horrible width and height choice, proceeding is not recommended.");
        }
        if(outputtype == 1) {
            buffer = new BufferedOutputStream(System.out);
        }

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
        try {
            this.buffer.write(Util.multstring("=", this.width).getBytes());
            for(int i=0; i<this.height; i++){
                this.buffer.write(("\n" + "=" + Util.multstring(" ", this.width-2) + "=").getBytes());
            }
            this.buffer.write(("\n" + Util.multstring("=", this.width)).getBytes());
        }catch(IOException ignored){}
    }

	public void drawSprite(Sprite sp, int x, int y){
		//offset SHOULD be y * this.width + x. If it is not that then I messed up in drawBox().
		for(int i=0; i<sp.map.length; sp++){
			//this will iterate through the sprite's map and add the corresponding element of texture to it.
			this.buffer.write(sp.texture[i].getBytes(), (x + sp.map[i][0]) + (y + sp.map[i][1]) * this.width);
		}
	}

    public void draw() {
        this.drawBox();
        try {
            System.out.println("\033\143"); //just don't run this thing outside of linux to be honest
            this.buffer.flush();
        }catch(IOException ignored){}

    }
}

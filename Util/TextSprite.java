package Util;

public class TextSprite {
    //I REALLY don't want to use sprites for basic text.
    //This thing is going to be displayed differently in code, so I would rather not have it be related to normal Sprite in any way shape or form.
    public byte[] text;
    public int x, y;

    public TextSprite(byte[] text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;
    }
    public TextSprite(byte[] text){
        this(text, 0, 0);
    }
    public TextSprite(String text, int x, int y){
        this(text.getBytes(), x, y);
    }
    public TextSprite(String text){
        this(text.getBytes(), 0, 0);
    }

    public void move(int x, int y){
        this.x+=x;
        this.y+=y;
    }
    public void teleport(int x, int y){
        this.x=x;
        this.y=y;
    }
}

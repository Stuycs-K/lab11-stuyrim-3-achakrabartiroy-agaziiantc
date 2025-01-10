package Util;

import java.util.Arrays;
import java.util.Objects;

public class Sprite {
	public int[][] map;
	public byte[] texture;
	public String[] colors;
	public int x, y = 0;
	public Sprite(int[][] n, byte[] s, String[] colors){
		this.map = n;
		this.texture = s;
		this.colors = colors; //definitely not the greatest way of going about it but oh well
	}

	public Sprite clone(){
		Sprite out = new Sprite(this.map, this.texture, this.colors);
		out.move(x, y);
		return out;
	}

	public void move(int x, int y){
		this.x+=x;
		this.y+=y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Sprite sprite = (Sprite) o;
		return x == sprite.x && y == sprite.y && Objects.deepEquals(map, sprite.map) && Objects.deepEquals(texture, sprite.texture);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Arrays.deepHashCode(map), Arrays.hashCode(texture), x, y);
	}
}

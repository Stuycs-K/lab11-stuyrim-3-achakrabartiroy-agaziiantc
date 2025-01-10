package Util;


public class spriteSheet {
	
	public static final Sprite testSprite = new Sprite(
		new int[][] {
			{0, 0},
			{0, 1},
			{1, 0},
			{1, 1}
		},

		 new byte[]{
		 	't', 'e', 's', 't'
		 }
		 
	);

	public static final Sprite dvd = new Sprite(
			new int[][]{
					{0,0}, {1, 0}, {2, 0}, {3, 0}, {4, 0},
					{0,1}, {1, 1}, {2, 1}, {3, 1}, {4, 1},
					{0,2}, {1, 2}, {2, 2}, {3, 2}, {4, 2}
			},
			new byte[]{
					'/', '-', '-', '-', 92, //92 is a backslash '\'
					'|', 'D', 'V', 'D', '|',
					92, '-', '-', '-', '/'
			}
		);




}

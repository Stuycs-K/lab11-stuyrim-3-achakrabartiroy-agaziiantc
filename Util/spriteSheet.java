package Util;


import Render.Text;

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
		 },
		new String[]{
			Util.clr(Text.RED), Util.clr(Text.WHITE), Util.clr(Text.BLUE), Util.clr(Text.YELLOW)
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
			},
			new String[]{
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.GREEN), Util.clr(Text.GREEN), Util.clr(Text.GREEN), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE)
			}
		);

	public static final Sprite K = new Sprite(
			new int[][]{
					{0,0}, {1, 0}, {2, 0}, {3, 0},
					{0,1}, {1, 1}, {2, 1}, {3, 1},
					{0,2}, {1, 2}, {2, 2},
					{0,3}, {1, 3}, {2, 3}, {3, 3},
					{0,4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}
			},
			new byte[]{
					'_',' ', '_', '_',
					'|', '|', '/', '/',
					'|', 39, '/',
					'|', '.', 92, '_',
					'|', '_', '|', '_', 92
			},
			new String[]{
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE),
					Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE), Util.clr(Text.WHITE)
			});




}

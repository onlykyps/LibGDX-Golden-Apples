package com.rowg.goldenapples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rowg.goldenapples.Scenes.MainMenu;
import com.rowg.goldenapples.Scenes.Options;
import com.rowg.goldenapples.Screens.LooseScreen;
import com.rowg.goldenapples.Screens.PlayScreen;
import com.rowg.goldenapples.Screens.WinScreen;

import org.omg.CORBA.PUBLIC_MEMBER;

public class GameMain extends Game {

	public SpriteBatch batch;
	public SpriteBatch getBatch()
	{
		return batch;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
//		setScreen(new PlayScreen(this));
//		setScreen(new Options(this));
//		setScreen(new WinScreen(this));
//		setScreen(new LooseScreen(this));

	}

	@Override
	public void render () {	super.render();	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}




	public static final int V_WIDTH = 400; //400
	public static final int V_HEIGHT = 208; //208

	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short THROWING_ROCK_BIT = 4;
	public static final short COLLECTIBLE_BIT = 8;
	public static final short LOGS_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short CM_BIT = 128;
	public static final short FM_BIT = 256;
	public static final short WIN_GAME_BIT = 512;
	public static final short LOOSE_GAME_BIT = 1024;
	public static final short NOTHING_BIT = 0;


}

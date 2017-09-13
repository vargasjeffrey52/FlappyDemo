package com.jvargas.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvargas.game.States.GameStateManager;
import com.jvargas.game.States.MenuState;
//import com.sun.org.apache.xpath.internal.operations.String;
import java.lang.String;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;  // width of game window
	public static final int HEIGHT = 800; // height of game window

	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;

	//SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		// intantiates a game state manager
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1); // wipes the screen clean so that sprite batch redraws everythin
		gsm.push(new MenuState( gsm));

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// updates current game state by using the difference between render times ie Gdx.graphic.getDeltaTime()
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch); // we give it our bathc file

//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}

}

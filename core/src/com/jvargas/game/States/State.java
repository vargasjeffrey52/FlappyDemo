package com.jvargas.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * State class describes the current state of the game
 * Created by varga_000 on 9/6/2017.
 */
// abstract since we don't want to instantiate any instances of state
public abstract class State {

   protected OrthographicCamera cam; // used to intiate game world window view
   protected Vector3 mouse; // XYZ coordinate system
   protected GameStateManager gsm;

   protected State(GameStateManager gsm){
       this.gsm = gsm;
       cam = new OrthographicCamera();
       mouse = new Vector3();
   }

    protected abstract void handleInput();
    public  abstract void update(float dt);
    public abstract void render(SpriteBatch sb); // spritebatch is a container for everything we need to render
    // must have a dispose method to get rid of images and prevent memory leak
    public abstract void dispose();
}

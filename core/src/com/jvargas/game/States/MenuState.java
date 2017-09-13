package com.jvargas.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvargas.game.FlappyDemo;
//import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

/**
 * Created by varga_000 on 9/7/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        //background = new Texture("bg.png");
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        // if user touched screen
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }


    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        // background --> img, (0,0) --> where to start drawing bottom left hand corner
        // FlappyDemo.WIDTH --> width of screen, FlappyDemo.HEIGHT --> Height of screen
        sb.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        sb.draw(playBtn, (FlappyDemo.WIDTH/2 - playBtn.getWidth()/2), FlappyDemo.HEIGHT/2 );
        sb.end();
    }
    // must have a dispose method to get rid of images and prevent memory leak
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Dispose");
    }

}

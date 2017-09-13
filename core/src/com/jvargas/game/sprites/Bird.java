package com.jvargas.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by varga_000 on 9/9/2017.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        //bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3,texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        // if statement only add gravity if bird isnt touching ground
        if (position.y > 0)
            velocity.add(0,GRAVITY, 0); // everytime bird updates we want to add gravity to its velocity.
        // below what we do is the following --> Velocity*dt = position
        velocity.scl(dt); // we also want to scale the velocity by the change in time (multiplies everthing by dt)
        position.add(MOVEMENT * dt, velocity.y,0); // we want to change the position so we add velocity in y direction
        // if bird in bird is position is below the ground the bird position gets placed in ground
        if (position.y < 0)
            position.y = 0;
        velocity.scl(1/dt);// (NOTE: above we had converted velocity to position)convert position to velocity (position/dt = Velocity)
        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.5f);
    }

    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        texture.dispose();
        flap.dispose();
    }

}

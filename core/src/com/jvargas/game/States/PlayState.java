package com.jvargas.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jvargas.game.FlappyDemo;
import com.jvargas.game.sprites.Bird;
import com.jvargas.game.sprites.Tube;

/**
 * Created by varga_000 on 9/8/2017.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;


    //private Texture bird;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        //bird = new Texture("bird.png");
        bird = new Bird(50,300);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 , GROUND_Y_OFFSET );
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth /2 ) + ground.getWidth(), GROUND_Y_OFFSET  );

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i ++ ) {
            tubes.add((new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH ))));
        }

        /* since birg image is small we want to make it bigger
        what we can do is use the setToOrtho to only view a piece of our window
         and therefore it will appear as if we zoomed into the image.
         */
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput(); // always will check input to see if users done anything.
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i += 1) {
            Tube tube = tubes.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm ));
            }
        }
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        // adjust sb so that it know the coordinate system we are working
        // with in relation to our camera.
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); //begins a sprite batch drawing
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0 );
        sb.draw(bird.getTexture(),bird.getPosition().x, bird.getPosition().y); // draws the bird
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            //nothing
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end(); // ends the sprite batch drawing
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        System.out.println("Play State Disposed ");
    }

    private void updateGround(){
        if (cam.position.x - (cam.viewportWidth /2 ) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if (cam.position.x - (cam.viewportWidth /2 ) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }
}

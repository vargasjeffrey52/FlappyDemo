package com.jvargas.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by varga_000 on 9/6/2017.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();

    }
    // pushes a state to the front of a Stack. a stack extends Deque
    public void push(State state){
        states.push(state);
    }

    // removes an item from the top of the stack following LIFO (Last in First Out)
    public void pop(){
        states.pop().dispose();
    }

    // remves previous state and sets a specified state
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    // peek Looks at the first item in the top stack without removing it
    public void update(float dt){
        states.peek().update(dt);
    }

    // a sprite batch creates a container for everything we need to render
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}

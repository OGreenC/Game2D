package com.company.GameStates;

import com.company.Utils.KeyHandler;
import com.company.Utils.MouseHandler;
import com.company.Utils.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    public static final int INIT = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;


    public GameStateManager() {

        states = new ArrayList<GameState>();

        states.add(new PlayState(this));

    }

    public void pop(int state) {
        states.remove(state);
    }
    public void add(int state) {
        if(state == PLAY) {
            states.add(new PlayState(this));
        }
        if(state == PAUSE) {
            states.add(new PauseState(this));
        }
    }

    public void addAndpop(int state) {
        states.remove(0);
        add(state);
    }

    public void update() {
        for(int i = 0; i < states.size(); i++) {
            states.get(i).update();
        }
    }

    public void input(KeyHandler key, MouseHandler mouse) {
        for(int i = 0; i < states.size(); i++) {
            states.get(i).input(key, mouse);
        }
    }

    public void render(Graphics2D g, Vector2f frameSize) {
        for(int i = 0; i < states.size(); i++) {
            states.get(i).render(g, frameSize);
        }
    }


}

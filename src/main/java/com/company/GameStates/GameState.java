package com.company.GameStates;

import com.company.Utils.KeyHandler;
import com.company.Utils.MouseHandler;
import com.company.Utils.Vector2f;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(KeyHandler key, MouseHandler mouse);
    public abstract void render(Graphics2D g, Vector2f frameSize);

}

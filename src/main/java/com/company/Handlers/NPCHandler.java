package com.company.Handlers;

import com.company.Graphics.Assets;
import com.company.Graphics.Sprite;
import com.company.Utils.Vector2f;

import java.awt.image.BufferedImage;

import static java.lang.Math.floor;

public class NPCHandler {

    private Vector2f loc;
    private Sprite sprite;

    private Direction direction;
    private Boolean walking;

    private int counter;

    private int APS; //Animations Per Second


    public NPCHandler(Assets a, int x, int y) {
        this.loc = new Vector2f(x,y);
        this.sprite = a.getSprite();
        this.direction = Direction.UP;
        this.walking = false;
        this.APS = 5;
    }

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Vector2f getLoc() { return loc; }

    public Sprite getSprite() { return sprite; }
    public void setDirectionDOWN() { this.direction = Direction.DOWN; }
    public void setDirectionUP() { this.direction = Direction.UP; }
    public void setDirectionLEFT() { this.direction = Direction.LEFT; }
    public void setDirectionRIGHT() { this.direction = Direction.RIGHT; }
    public void setDirection(Direction direction) { this.direction = direction; }
    public void setSprite(Sprite s) { this.sprite = s; }

    public BufferedImage getImg() {

        int animationSteps = 4;
        counter = (counter + 1) % ( (int) (60 * ( animationSteps/ (double) APS)));

        BufferedImage s;

        int x = 0;
        if(walking) {
            x = (int) floor(counter/(60/APS));
        }

        if(direction == Direction.DOWN) {
            s = sprite.getSprite(x,0);
        } else if(direction == Direction.UP) {
            s = sprite.getSprite(x,3);
        } else if(direction == Direction.RIGHT) {
            s =  sprite.getSprite(x,2);
        } else if(direction == Direction.LEFT) {
            s = sprite.getSprite(x,1);
        } else {
            //Not relevant.
            s = sprite.getSprite(0,0);
        }
        return s;
    }

    public Boolean getWalking() {
        return walking;
    }

    public void setWalking(Boolean walking) {
        this.walking = walking;
    }

    public int getAPS() {
        return APS;
    }

    public void setAPS(int APS) {
        this.APS = APS;
    }

}

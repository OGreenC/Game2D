package com.company.Handlers;

import com.company.Graphics.Assets;
import com.company.Utils.KeyHandler;
import com.company.Utils.MouseHandler;

public class CharacterHandler extends NPCHandler {

    private int spriteWidth = 96;
    private int spriteHeight = 128;

    public CharacterHandler(Assets a, int x, int y) {
        super(a, x, y);
    }

    public void updateCharacter(KeyHandler key, MouseHandler mouse) {

        /* Redundant when setting direction in movement (and thus prioritize left/right looking)
        //Determine what way to look
        if(key.getPressedKeys().size() > 0) {
            KeyHandler.Key latest = key.getPressedKeys().get(key.getPressedKeys().size()-1);
            if(latest == key.up) { setDirectionUP(); }
            if(latest == key.down) { setDirectionDOWN(); }
            if(latest == key.left) { setDirectionLEFT(); }
            if(latest == key.right) { setDirectionRIGHT(); }
        }
        */

        setWalking(false);

        //Set walking speed / Check if running, and set speed accordingly.
        double speed = 1.6/16;
        if(key.shift.down) {
            setAPS(10);
            speed = 2.6/16;
        } else {
            setAPS(6);
        }
        //Adjust oblique speed.
        if((key.up.down || key.down.down) && (key.left.down || key.right.down)) {
            if(key.shift.down) {
                speed = 2.1/16;
            } else {
                speed = 1.4/16;
            }
        }

        //Check movement keys.
        if(key.up.down){
            setDirection(Direction.UP);
            getLoc().addY((float) -speed);
            setWalking(true);
        }
        if(key.down.down) {
            setDirection(Direction.DOWN);
            getLoc().addY((float) speed);
            setWalking(true);
        }
        if(key.left.down) {
            setDirection(Direction.LEFT);
            getLoc().addX((float) -speed);
            setWalking(true);
        }
        if(key.right.down) {
            setDirection(Direction.RIGHT);
            getLoc().addX((float) speed);
            setWalking(true);
        }

    }

    public int getSpriteWidth() { return spriteWidth; }
    public int getSpriteHeight() { return spriteHeight; }
}

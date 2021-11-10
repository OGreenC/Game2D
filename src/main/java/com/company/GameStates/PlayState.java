package com.company.GameStates;

import com.company.Graphics.*;
import com.company.Handlers.CharacterHandler;
import com.company.Handlers.MapHandler;
import com.company.Utils.Camera;
import com.company.Utils.KeyHandler;
import com.company.Utils.MouseHandler;
import com.company.Utils.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Camera camera;
    private MapHandler map;
    private CharacterHandler character;


    public PlayState(GameStateManager gsm){
        super(gsm);

        map = new MapHandler();
        character = new CharacterHandler(Assets.Character13,0,0);

        camera = new Camera(map, character);

    }

    public void update() {
        final int MAXDISTANCE;
        camera.setViewLoc(character.getLoc().x, character.getLoc().y);

    }

    public void input(KeyHandler key, MouseHandler mouse) {

        character.updateCharacter(key, mouse);

    }
    public void render(Graphics2D g, Vector2f frameSize) {

        camera.paintScreen(g, frameSize);

        //Coordinates in top left corner.
        Sprite.drawArray(g, Assets.ZeldaFont.getFonts(), "X " + String.format("%.2f", (double) character.getLoc().x), new Vector2f(1100, 10), 32, 32, 12, 0);
        Sprite.drawArray(g, Assets.ZeldaFont.getFonts(), "Y " + String.format("%.2f", (double) character.getLoc().y), new Vector2f(1100, 50), 32, 32, 12, 0);


        /*
         * TODO. Make Camera object that sources data from MapHandler & Character and puts it together.
         *       This method should also move the camera along with the player, leaving the tiles and
         *       objects behind.
         *       The Camera should have the following main Methods:
         *       * UpdateCamera - Source new information from player and map, and store the nessecary data.
         *       * GetTileView - Returns a 2dArray of all tiles.
         *       GetObjectView - Returns a list of all Objects to be displayed.
         *       Get NPCView - Returns a list of all NPC's to be viewed.
         *       * Get PlayerView - Returns a list of all NPC's to be viewed.
         *       -
         *       The ones marked with a star is sufficient for now.
         *       Last but not least FPS should be moved here, and drawing should be moved into Camera
         *       to a method called renderCamera();
         *       -
         *       When this is done, make editor mode, and make a better map.... :]
         */


    }
}

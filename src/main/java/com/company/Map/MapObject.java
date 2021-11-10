package com.company.Map;

import com.company.Graphics.Sprite;
import com.company.Utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapObject {

    private Vector2f loc;
    private int imgWidth;
    private int imgHeight;
    private BufferedImage img;

    public MapObject() {
        this.loc = new Vector2f(0,0); //World loc. Can be any decimal number. Chunk loc can be calculated.
        this.imgWidth = 32; //TILESIZE
        this.imgHeight = 32; //TILESIZE
        this.img = null;
    }

    public MapObject(Vector2f loc, BufferedImage img) {
        setLoc(loc);
        this.imgWidth = 32; //TILESIZE
        this.imgHeight = 32; //TILESIZE
        this.img = img;
    }
    public MapObject(Vector2f loc, BufferedImage img, int imgWidth, int imgHeight) {
        setLoc(loc);
        this.imgWidth = imgWidth; //TILESIZE
        this.imgHeight = imgHeight; //TILESIZE
        this.img = img;
    }

    public void setImgDimensions(int newWidth, int newHeight) {
        this.imgWidth = newWidth;
        this.imgHeight = newHeight;
    }

    public void setLoc(Vector2f newLoc) {
        this.loc.setVector(newLoc);
    }

    public void drawObject(Graphics2D g, Vector2f loc, int size) {
        Sprite.drawSprite(g, img, loc, imgWidth * size, imgHeight * size);
    }

}

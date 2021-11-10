package com.company.Graphics;


import com.company.Utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

    private BufferedImage spriteSheet = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wSprite;
    private int hSprite;

    public Sprite(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        spriteSheet = loadSprite(file);

        wSprite = spriteSheet.getWidth() / w;
        hSprite = spriteSheet.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        spriteSheet = loadSprite(file);

        wSprite = spriteSheet.getWidth() / w;
        hSprite = spriteSheet.getHeight() / h;

        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wSprite = spriteSheet.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hSprite = spriteSheet.getWidth() / h;
    }

    public int getWidth() { return w; }
    public int getheight() { return h; }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[wSprite][hSprite];

        for(int x = 0; x < wSprite; x++) {
            for(int y = 0; y < hSprite; y++) {
                spriteArray[x][y] = getSprite(x,y);
            }
        }
    }
    public BufferedImage getspriteSheet(){ return spriteSheet; }

    public BufferedImage getSprite(int x, int y){
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2() {
        return spriteArray;
    }

    public static void drawSprite(Graphics2D g, BufferedImage img, Vector2f pos, int width, int height) {
        float x = pos.x;
        float y = pos.y;

        g.drawImage(img, (int) x, (int) y, width, height, null);
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for(int i= 0; i < img.size(); i++){
            if(img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, Fonts f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for(int i= 0; i < word.length(); i++){
            if(word.charAt(i) != 32){
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }





}


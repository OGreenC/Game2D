package com.company.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Fonts {

    private BufferedImage fontSheet = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wLetter;
    private int hLetter;

    public Fonts(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        fontSheet = loadFont(file);

        wLetter = fontSheet.getWidth() / w;
        hLetter = fontSheet.getWidth() / h;
        loadFontArray();
    }

    public Fonts(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        fontSheet = loadFont(file);

        wLetter = fontSheet.getWidth() / w;
        hLetter = fontSheet.getHeight() / h;
        loadFontArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wLetter = fontSheet.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hLetter = fontSheet.getWidth() / h;
    }

    public int getWidth() { return w; }
    public int getheight() { return h; }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[wLetter][hLetter];

        for(int x = 0; x < wLetter; x++) {
            for(int y = 0; y < hLetter; y++) {
                spriteArray[x][y] = getLetter(x,y);
            }
        }
    }
    public BufferedImage getFontSheet(){ return fontSheet; }

    public BufferedImage getLetter(int x, int y) {
        BufferedImage img = fontSheet.getSubimage(x * w, y * h, w, h);
        return img;
    }

    public BufferedImage getFont(char letter) {
        int x = 1;
        int y = 3;
        if(Character.isLetter(letter)) {
            int value = letter - 65;

            x = value % wLetter;
            y = value / wLetter;

        } else if(Character.isDigit(letter)) {
            x = Character.getNumericValue(letter);
            y = 6;
        } else if(letter == '-') {
            x = 6;
            y = 2;
        } else if(letter == '.') {
            x = 7;
            y = 2;
        } else if(letter == ',') {
            x = 8;
            y = 2;
        }
        return getLetter(x,y);
    }
}
package com.company.Map;

import com.company.Graphics.Assets;
import com.company.Graphics.Sprite;

import java.awt.image.BufferedImage;

public enum MapItemType {
    TREE(0, Assets.Outdoor, 0, 2, 7, 9),
    Smtels(1, Assets.Outdoor, 0, 0);

    MapItemType(int id, Assets a, int sheetX, int sheetY) {
        this.id = id;
        this.sprite = a.getSprite();
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.sheetX2 = sheetX;
        this.sheetY2 = sheetY;
        this.img = new BufferedImage[1][1];
        this.img[0][0] = this.sprite.getSprite(this.sheetX,this.sheetY);
    }

    MapItemType(int id, Assets a, int sheetX, int sheetX2, int sheetY, int sheetY2) {
        this.id = id;
        this.sprite = a.getSprite();
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.sheetX2 = sheetX2;
        this.sheetY2 = sheetY2;
        this.img = new BufferedImage[sheetY2-sheetY+1][sheetX2-sheetX+1];
        for(int r = 0; sheetY + r <= sheetY2; r++) {
            for(int c = 0; sheetX + c <= sheetX2; c++) {
                this.img[r][c] = this.sprite.getSprite(sheetX + c,sheetY + r);
            }
        }
    }

    private int id;
    private int sheetX;
    private int sheetY;
    private int sheetX2;
    private int sheetY2;
    private Sprite sprite;
    private BufferedImage[][] img;

    public BufferedImage[][] getImgMap() {
        return img;
    }

    public int getId() {
        return id;
    }

    public int getSheetX() {
        return sheetX;
    }
    public int getSheetY() {
        return sheetY;
    }
    public int getSheetX2() {
        return sheetX2;
    }
    public int getSheetY2() {
        return sheetY2;
    }


    public Sprite getSprite() {
        return sprite;
    }

    public MapItemType getTileFromID(int id) {
        return MapItemType.values()[id];
    }


}

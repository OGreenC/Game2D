package com.company.Map;

import com.company.Graphics.Assets;
import com.company.Graphics.Sprite;

import java.awt.image.BufferedImage;

public enum TileType {
    VOID(0, Assets.Tiles, 0, 0),
    GRASS(1, Assets.Ground, 0, 0),
    STONE(2, Assets.Ground, 11,0),
    SOMETHINGELSE(3, Assets.Ground,5,0);

    TileType(int id, Assets a, int sheetX, int sheetY) {
        this.id = id;
        this.sprite = a.getSprite();
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.img = this.sprite.getSprite(this.sheetX,this.sheetY);
    }

    private int id;

    private int sheetX;
    private int sheetY;
    private Sprite sprite;
    private BufferedImage img;

    public BufferedImage getImg() {
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

    public Sprite getSprite() {
        return sprite;
    }

    public TileType getTileFromID(int id) {
        return TileType.values()[id];
    }


}

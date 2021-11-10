package com.company.Graphics;

public enum Assets {

    ZeldaFont(0,16,16,"Assets/Fonts/ZeldaFont.png","Fonts"),
    Character12(1,48,64,"Assets/characters/12.png","Sprite"),
    Character13(2,48,64,"Assets/characters/13.png","Sprite"),
    Tiles(3,32,32,"Assets/Tiles/A5_Tiles.png","Sprite"),
    Ground(4,32,32,"Assets/Tiles/A2_Ground.png","Sprite"),
    Outdoor(4,32,32,"Assets/Tiles/D_OutDoor.png","Sprite");

    Assets(int id, int w, int h, String file, String assetType) {
        this.id = id;
        this.width = w;
        this.height = h;
        if(assetType == "Sprite") {
            this.sprite = new Sprite(file,w,h);
            this.fonts = null;
        } if(assetType == "Fonts") {
            this.fonts = new Fonts(file, 16, 16);
            this.sprite = null;
        }
    }

    private int id;
    private int width;
    private int height;
    private String file;
    private String assetType;
    private Sprite sprite;
    private Fonts fonts;

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFile() {
        return file;
    }

    public String getAssetType() {
        return assetType;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Fonts getFonts() {
        return fonts;
    }

    static public Assets getAssetByID(int id) {
        return Assets.values()[id];
    }

}


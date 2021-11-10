package com.company.Utils;

import com.company.Graphics.Sprite;
import com.company.Handlers.CharacterHandler;
import com.company.Handlers.MapHandler;
import com.company.Map.Chunk;
import com.company.Map.MapItem;
import com.company.Map.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Camera {

    private int RENDERDISTANCE = 32; //Tiles in each direction (Top is mitigated)
    private MapHandler map;
    private CharacterHandler character;
    private Vector2f viewLoc = new Vector2f();
    private int TILESIZE = 48;

    public Camera(MapHandler map, CharacterHandler c) {
        this.map = map;
        this.character = c;
        this.viewLoc.setVector(0,0); //Real coordinates of middle of screen.
    }

    public void paintScreen(Graphics2D g, Vector2f frameSize) {
        Chunk[][] chunkMap = getActiveChunks(viewLoc.x/TILESIZE, viewLoc.y/TILESIZE, RENDERDISTANCE);

        paintTileView(g, chunkMap, frameSize);
        paintObjects(g, chunkMap, frameSize);

        paintCharacter(g, frameSize);

    }


    public void paintTileView(Graphics2D g, Chunk[][] chunkMap ,Vector2f frameSize) {
        for(int chunkRow = 0; chunkRow < chunkMap.length; chunkRow++) {
            for(int chunkCol = 0; chunkCol < chunkMap[chunkRow].length; chunkCol++) {
                //Looping through tilemap for each chunk, and painting each tile.
                Vector2f chunkLoc = chunkMap[chunkRow][chunkCol].getLoc();
                Tile[][] chunkTM = chunkMap[chunkRow][chunkCol].getTilemap();
                for(int tileRow = 0; tileRow < chunkTM.length; tileRow++) {
                    for(int tileCol = 0; tileCol < chunkTM[tileRow].length; tileCol++) {
                        //Getting tile and absolute / relative to camera coordinates:
                        Tile t = chunkTM[tileRow][tileCol];
                        Vector2f absLoc = new Vector2f(((chunkLoc.x * 16) + t.getLoc().x) * TILESIZE, ((chunkLoc.y * 16) + t.getLoc().y) * TILESIZE);
                        Vector2f relLoc = new Vector2f(absLoc.x - viewLoc.x, absLoc.y - viewLoc.y);
                        //Drawing tile relative to camera - Adjusted for centering in the middle.
                        Sprite.drawSprite(g, t.getTileType().getImg(), new Vector2f(relLoc.x + frameSize.x/2, relLoc.y + frameSize.y/2), TILESIZE, TILESIZE);
                    }
                }
            }
        }
    }

    public void paintObjects(Graphics2D g, Chunk[][] chunkMap ,Vector2f frameSize) {
        for(int chunkRow = 0; chunkRow < chunkMap.length; chunkRow++) {
            for (int chunkCol = 0; chunkCol < chunkMap[chunkRow].length; chunkCol++) {
                Vector2f chunkLoc = chunkMap[chunkRow][chunkCol].getLoc();
                ArrayList<MapItem> objectList = chunkMap[chunkRow][chunkCol].getObjectMap();
                for (MapItem o: objectList) {
                    //Objects can exists of multiple images (tho a square):
                    BufferedImage[][] imgs = o.getObjectType().getImgMap();

                    //Get location:
                    Vector2f absLoc = new Vector2f(((chunkLoc.x * 16) + o.getLoc().x) * TILESIZE, ((chunkLoc.y * 16) + o.getLoc().y) * TILESIZE);
                    Vector2f relLoc = new Vector2f(absLoc.x - viewLoc.x, absLoc.y - viewLoc.y);
                    for(int r = 0; r < imgs.length; r++) {
                        for(int c = 0; c < imgs[r].length; c++) {
                            Sprite.drawSprite(g, imgs[r][c], new Vector2f(relLoc.x + frameSize.x/2 + (c * TILESIZE * o.getSize()), relLoc.y + frameSize.y/2 + (r * TILESIZE * o.getSize())), TILESIZE * o.getSize(),TILESIZE * o.getSize());
                        }
                    }
                }
            }
        }
    }





    public void paintCharacter(Graphics2D g, Vector2f frameSize) {
        final int WIDTH = 96;
        final int HEIGHT = 128;

        Vector2f relLoc = new Vector2f((character.getLoc().x * TILESIZE) - viewLoc.x, (character.getLoc().y * TILESIZE) - viewLoc.y);
        Sprite.drawSprite(g, character.getImg(), new Vector2f(relLoc.x + frameSize.x/2 - WIDTH/2, relLoc.y + frameSize.y/2 - HEIGHT/2),character.getSpriteWidth(), character.getSpriteHeight());


        //Debugging
        g.setColor(Color.red);
        g.drawRect((int) (relLoc.x + frameSize.x/2 - WIDTH/2), (int) (relLoc.y + frameSize.y/2- HEIGHT/2),character.getSpriteWidth(), character.getSpriteHeight());
        g.setColor(Color.blue);
        g.fillRect((int) (relLoc.x + frameSize.x/2 - WIDTH/2), (int) (relLoc.y + frameSize.y/2- HEIGHT/2),5,5);
    }

    /*


     */

    private Chunk[][] getActiveChunks(float x, float y, int viewDistance){
        //Chunk Boundries:
        int minChunkX = (int) Math.floor((x - viewDistance - 16) / 16);
        int maxChunkX = (int) Math.ceil((x + viewDistance + 16) / 16);
        int minChunkY = (int) Math.floor((y - viewDistance - 16) / 16);
        int maxChunkY = (int) Math.ceil((y + viewDistance + 16) / 16);
        //System.out.println("minChunkX: " + minChunkX + ", maxChunkX: " + maxChunkX + ", minChunkY: " + minChunkY + ", maxChunkY: " + maxChunkY);

        //Get chunks in chunkMap:
        Chunk[][] chunkMap = new Chunk[Math.abs(maxChunkY-minChunkY)][Math.abs(maxChunkX-minChunkX)];
        for(int row = minChunkY; row < maxChunkY; row++) {
            for(int col = minChunkX; col < maxChunkX; col++) {
                chunkMap[row-minChunkY][col-minChunkX] = map.getChunk(col, row);
            }
        }
        return chunkMap;
    }


    public void setViewLoc(float x, float y) {
        this.viewLoc.setVector(x * TILESIZE,y * TILESIZE);
    }



}

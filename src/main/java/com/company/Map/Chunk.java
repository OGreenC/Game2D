package com.company.Map;

import com.company.Handlers.NPCHandler;
import com.company.Utils.Vector2f;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/*
 * Chunk instance class.
 * Each and every chunk on the map is stored in chunk instances, or can be loaded from file.
 */

public class Chunk {

    private Tile[][] tilemap;
    private ArrayList<MapItem> objectMap;
    private NPCHandler[] NPCs;
    private Vector2f absoluteLoc;

    public Chunk(int x, int y) {

        this.tilemap = new Tile[16][16];
        this.objectMap = new ArrayList<MapItem>();
        this.NPCs = new NPCHandler[1];
        this.absoluteLoc = new Vector2f(x,y);

        loadChunk(x + ";" + y);
    }

    public void loadChunk(String chunkName) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/Data/Worlds/Overworld/" + chunkName + ".yml"));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Couldn't find chunk " + chunkName + ".. Generating new empty chunk..");
            generateChunk();
            return;
        }
        Yaml yaml = new Yaml();
        Map<String, java.lang.Object> fileData = yaml.load(inputStream);
        Map<String, java.lang.Object> chunkData = (Map)fileData.get("chunkData");

        Map<String, java.lang.Object> tileData = (Map)chunkData.get("tiles");
        for(java.lang.Object data : tileData.values()) {
            Tile tile = new Tile(data);
            this.tilemap[(int)tile.getLoc().y][(int)tile.getLoc().x] = tile;
        }
        fillTileMapWith("GRASS");

        Map<String, java.lang.Object> objectData = (Map)chunkData.get("objects");
        for(java.lang.Object data : objectData.values()) {
            MapItem object = new MapItem(data);
            this.objectMap.add(object);
        }
    }


    /*
     * TODO. for now generate a empty chunk, to avoid errors. Add world generation later.
     */
    public void generateChunk() {
        fillTileMapWithBorder();
    }

    public void fillTileMapWith(String ID) {
        for (var y = 0; y < 16; y++) {
            for (var x = 0; x < 16; x++) {
                if(this.tilemap[y][x] == null) {
                    this.tilemap[y][x] = new Tile(ID,x,y);
                }
            }
        }
    }
    public void fillTileMapWithBorder() {
        for (var y = 0; y < 16; y++) {
            for (var x = 0; x < 16; x++) {
                if(y == 0 || y == 15) {
                    this.tilemap[y][x] = new Tile("STONE",x,y);
                } else if (x == 0 || x == 15) {
                    this.tilemap[y][x] = new Tile("STONE",x,y);
                }
            }
        }
        fillTileMapWith("VOID");
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }
    public ArrayList<MapItem> getObjectMap() { return objectMap; }
    public Vector2f getLoc() { return absoluteLoc; }
}

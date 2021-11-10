package com.company.Map;

import com.company.Utils.Vector2f;

import java.util.Map;

/*
 * Instance Class of a tile.
 */

public class Tile {
    private String id;
    private Vector2f loc;
    private TileType tileType;

    public Tile(String id, int x, int y) {
        this.id = id;
        this.loc = new Vector2f(x,y);
        this.tileType = TileType.valueOf(id);
    }

    public Tile(Object data) {
        Map<String, Object> tileData = (Map) data;
        this.id = (String) tileData.get("type");
        this.tileType = TileType.valueOf(this.id);
        Map<String, Object> locData = (Map) tileData.get("loc");
        this.loc = new Vector2f((int)locData.get("x"), (int)locData.get("y"));
    }

    public Tile(String id, Vector2f loc) {
        this.id = id;
        this.loc = loc;
        this.tileType = TileType.valueOf(id);
    }



    public String getId() {
        return id;
    }

    public Vector2f getLoc() {
        return loc;
    }

    public TileType getTileType() {
        return tileType;
    }


}

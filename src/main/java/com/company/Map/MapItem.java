package com.company.Map;

import com.company.Utils.Vector2f;

import java.util.Map;

/*
 * Instance Class of a MapObject.
 * Objects are more complex objects on the map, that can exists
 * of multiple tile sprites, or even 1 bigger sprite (bigger image).
 * Map objects should also be interactable - Be clickable etc.
 */

public class MapItem {

    private MapItemType mapItemType;
    private String id;
    private Vector2f loc;
    private int width;
    private int height;
    private int size;

    public MapItem(Object data) {
        Map<String, Object> objectData = (Map) data;
        this.id = (String) objectData.get("type");
        this.mapItemType = MapItemType.valueOf(this.id);
        Map<String, Object> locData = (Map) objectData.get("loc");
        this.loc = new Vector2f((int)locData.get("x"), (int)locData.get("y"));
        this.size = (int) objectData.get("size");
    }

    public Vector2f getLoc() {
        return loc;
    }

    public int getSize() {
        return size;
    }

    public MapItemType getObjectType() {
        return mapItemType;
    }
}

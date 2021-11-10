package com.company.Handlers;

import com.company.Map.Chunk;
import java.util.*;

public class MapHandler {

    HashMap<String, Chunk> chunks;

    public MapHandler() {
        this.chunks = new HashMap<String, Chunk>();
    }

    public void loadChunk(int x, int y) {
        String chunkName = x + ";" + y;
        if(chunks.containsKey(chunkName)) {
            //Chunk already loaded.
        } else {
            System.out.println("Loading new chunk " + chunkName);
            chunks.put(chunkName, new Chunk(x,y));
            System.out.println("Chunk " + chunkName + " loaded..");
        }
    }

    public Chunk getChunk(int x, int y) {
        String chunkName = x + ";" + y;
        if(!chunks.containsKey(chunkName)) {
            loadChunk(x,y);
        }
        return chunks.get(chunkName);
    }


}

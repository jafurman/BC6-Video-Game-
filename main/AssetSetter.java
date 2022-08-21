package main;

import objects.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp ) {
        this.gp = gp;
    }

    public void setObject() {   //for when I want to place a new object around the world map

        //CHANGE OBJECT AND ADD OBJECT LOCATIONS

        gp.obj[0] = new OBJ_Map(gp);          //Key to door
        gp.obj[0].worldX = 23 * gp.tileSize;  //X COORD
        gp.obj[0].worldY = 1 * gp.tileSize;   //Y COORD

        gp.obj[1] = new OBJ_Map(gp);          //Key to door
        gp.obj[1].worldX = 12 * gp.tileSize;
        gp.obj[1].worldY = 16 * gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);         //Door to Space
        gp.obj[2].worldX = 24 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);         //Door to chess
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 24 * gp.tileSize;

        gp.obj[4] = new OBJ_Message(gp);      //record in space
        gp.obj[4].worldX = 15 * gp.tileSize;
        gp.obj[4].worldY = 38 * gp.tileSize;

        gp.obj[5] = new OBJ_Message(gp);      //record in volcano
        gp.obj[5].worldX = 15 * gp.tileSize;
        gp.obj[5].worldY = 12 * gp.tileSize;

        gp.obj[6] = new OBJ_Message(gp);       //record in chess board
        gp.obj[6].worldX = 36 * gp.tileSize;
        gp.obj[6].worldY = 12 * gp.tileSize;

        gp.obj[7] = new OBJ_RecordPlayer(gp);  //Record Player in space
        gp.obj[7].worldX = 16 * gp.tileSize;
        gp.obj[7].worldY = 37 * gp.tileSize;

        gp.obj[8] = new OBJ_RecordPlayer(gp);  // Record Player in chess
        gp.obj[8].worldX = 37 * gp.tileSize;
        gp.obj[8].worldY = 11 * gp.tileSize;

        gp.obj[9] = new OBJ_RecordPlayer(gp);  // Record Player in volcano
        gp.obj[9].worldX = 16 * gp.tileSize;
        gp.obj[9].worldY = 12 * gp.tileSize;

        gp.obj[10] = new OBJ_Boots(gp);  // plug for speed
        gp.obj[10].worldX = 2 * gp.tileSize;
        gp.obj[10].worldY = 12 * gp.tileSize;



    }
}

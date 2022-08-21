package objects;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = true;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;  //if player position is 500 500 these values here help the player
        int screenY = worldY - gp.player.worldY + gp.player.screenY;  //values and screen values stay in conjunction with each other

        if ( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&    //This helps with rendering. This allows only tiles to be drawn when the player is near.
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}

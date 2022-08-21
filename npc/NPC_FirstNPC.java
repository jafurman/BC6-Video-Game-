package npc;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class NPC_FirstNPC extends Entity {

    public NPC_FirstNPC(GamePanel gp) {

        super(gp);

        direction = "down";
        speed = 1;

    }
    public void getPlayerImage() {

        up1 = setup("/npc/up1");
        up2 = setup("/npc/up2");
        down1 = setup("/npc/down1");
        down2 = setup("/npc/down2");
        left1 = setup("/npc/left1");
        left2 = setup("/npc/left2");
        right1 = setup("/npc/right1");
        right2 = setup("/npc/right2");
    }


}

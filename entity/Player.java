package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasMap = 0;
    public int hasMessage = 0;
    public int numOfMessages = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 12;    //where the character spawns
        speed = 3;
        direction = "down";

    }

    public void getPlayerImage() {

        up1 = setup("/player/up1");
        up2 = setup("/player/up2");
        down1 = setup("/player/down1");
        down2 = setup("/player/down2");
        left1 = setup("/player/left1");
        left2 = setup("/player/left2");
        right1 = setup("/player/right1");
        right2 = setup("/player/right2");
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {


            if(keyH.upPressed) {
                direction = "up";

            }
            else if (keyH.downPressed) {
                direction = "down";

            }
            else if (keyH.leftPressed) {
                direction = "left";

            }
            else if (keyH.rightPressed) {
                direction = "right";

            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 8) {
                if(spriteNum == 1 ) {
                    spriteNum = 2;
                } else if ( spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }
    public void pickUpObject( int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "map":
                    hasMap++;
                    gp.obj[i] = null;
                    gp.playSE(1);
                    gp.ui.showMessage("You've picked up a MAP!");
                    break;
                case "door":
                    if ( hasMap > 0 ) {
                        gp.obj[i] = null;
                        hasMap--;
                        gp.ui.showMessage("You opened a new area!");
                        gp.playSE(2);
                    } else {
                        gp.ui.showMessage("You need a MAP!");
                    }
                    break;
                case "message":
                    hasMessage++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You picked up a RECORD!");
                    gp.playSE(3);
                    break;
                case "record player":
                    if (hasMessage > 0) {
                        numOfMessages++;
                        gp.ui.currentDialogueCounter++;
                    }
                    hasMessage--;


                    if (hasMessage < 0 ) {
                        hasMessage = 0;
                    }
                    if ( hasMessage >= 1) {  //now sound effect will only play once and the sound system is better
                        gp.playSE(4);
                    }
                        gp.stopMusic();
                          //new track

                    if (numOfMessages == 1) {
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.playMusic(0); //new track
                        gp.gameState = gp.dialogueState;


                    }
                    if (numOfMessages == 2) {
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.playMusic(6); //new track
                        gp.gameState = gp.dialogueState;

                    }
                    if (numOfMessages == 3) {
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.playMusic(7); //final track
                        gp.gameState = gp.dialogueState;


                    }
                    System.out.println(numOfMessages);
                    break;
                case "boots":
                    gp.obj[i] = null;
                    speed += 1;
                    gp.playSE(5);
                    gp.ui.showMessage("Player speed boost applied.");
                    break;
                case "end":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    break;

                    }
            }
        }
    public void draw(Graphics2D g2) {

       // g2.setColor(Color.yellow);

       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                    break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }

        g2.drawImage(image, screenX, screenY, null);
    }


}

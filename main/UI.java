package main;

import objects.OBJ_Map;
import objects.OBJ_Message;
import objects.OBJ_RecordPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_20, arial_80B, arial_5;
    BufferedImage mapImage;
    BufferedImage recordImage;
    BufferedImage recordPImage;
    public boolean messageOn = false;
    public boolean gameMessageOn = false;
    public String message = "";
    public String gameMessage = "";
    int messageCounter = 0;
    int gameMessageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;
    public int currentDialogueCounter = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial_80B = new Font("Arial", Font.BOLD, 80);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        arial_5 = new Font("Arial", Font.PLAIN,5);
        OBJ_Map map = new OBJ_Map(gp);
        mapImage = map.image;
        OBJ_Message record = new OBJ_Message(gp);
        recordImage = record.image;
        OBJ_RecordPlayer recordP = new OBJ_RecordPlayer(gp);
        recordPImage = recordP.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void showGameMessage(String text) {
        gameMessage = text;
        gameMessageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);


            String text;
            int textLength;
            int x;
            int y;

            text = "Congratulations, BC6. You made your choice. I hope you gained something along the way.";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();  //returns the length of the text

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {

            this.g2 = g2;
            if ( gp.gameState == gp.playState) {
                //DO PLAYSTATE LATER
            }
            if ( gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
            g2.setFont(arial_20);
            g2.setColor(Color.white);
            g2.drawImage(mapImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasMap, 74, 50);

            g2.drawImage(recordImage, gp.tileSize / 2, gp.tileSize / 2 * 3, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasMessage, 74, 100);

            g2.drawImage(recordPImage, gp.tileSize / 2, gp.tileSize / 2 * 5, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.numOfMessages, 74, 150);


            //MESSAGE

            if (messageOn) {
                int x = getXCenter(message);
                int y = gp.screenHeight/2;
                drawSubWindow(x -270, y+ 155, (gp.screenWidth/2) + 60 , gp.tileSize + 10);
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 10);


                messageCounter++;

                if (messageCounter > 120) {   //120 frames means 2 seconds. So this message will display for 2 seconds.
                    messageCounter = 0;
                    messageOn = false;
                }
            }


            if (gp.gameState == gp.dialogueState) {
                drawDialogueScreen();

            }

        }


    }
    public void currentDialogueSetter() {

        if ( currentDialogueCounter == 1) {
            currentDialogue = "Hello there, BC6. Welcome to the world. I know it all \nseems confusing " +
                    "right now but I am here to guide you! \nI am your creator, someone who wanted you in this world.\n" +
                    "I apologize for you not having a say in this. Before you \nis a multitude of areas I would like for your to explore. \n" +
                    "I ask you, BC6, to please process how you feel through-\nout this experiment. " +
                    "Don't log anything in your data-\nbase, just simply explore. Get lost in the wonders of \nyour mind and its surroundings." +
                    " Around the area are some\nrecords and maps. Unlock new areas and fill your heart. \nClick (ENTER) to start your path with finding space. ";

        }
        if (currentDialogueCounter == 2) {
            currentDialogue = "Beautiful isn't it? There's something so special about being\nalone." +
                    " Nothing around us is frightening but comforting at \nthe same time. My personal take " +
                    "on it is that it makes \nno sense, how is it possible to have two opposing feel-\nings on solitude? " +
                    "Yet that is exactly what makes life \nbeautiful. Two different people can experience \n" +
                    "the exact same moment in two entirely different ways. \nIt's important to remember that not everything needs to be \nlike someone else's." +
                    " You can appreciate YOUR moment. \nYou CAN appreciate YOUR value. (ENTER)";
        }
        if (currentDialogueCounter == 3) {
            currentDialogue = "bruh  ";
        }

    }
    public void drawPauseScreen() {
        String text = "THIS MEANS YOU PAUSED THE GAME. TAKE ALL THE TIME YOU NEED :) ";

        int x = getXCenter(text);
        int y = gp.screenHeight / 2;

        drawSubWindow(x -20, y - 27, (gp.screenWidth/2) + 100, gp.tileSize);
        g2.drawString(text, x, y);
    }
    public int getXCenter(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize*2 + 10;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*10;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize - 12;
        y += gp.tileSize;

        currentDialogueSetter();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke( new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10, height-10, 25,25);
    }



}

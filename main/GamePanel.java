package main;

import entity.Player;
import objects.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 size
    final int scale = 3;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;   //change these numbers if we increase or change the size of the map

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; //the constant time concept of a 2d game is used by THREAD commands


    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[20];

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame() {

        aSetter.setObject();

        // ui.showGameMessage("Hello world");

        playMusic(0);
        stopMusic();
     //   ui.drawDialogueScreen();
        gameState = playState;


    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {  //gameLoop

        double drawInterval = 1000000000/FPS;  //one million nanoseconds divided by 60 is .016 seconds/drawInterval is how often the screen is drawn
        double nextDrawTime = System.nanoTime() + drawInterval;



        while(gameThread != null) {

          //  System.out.println("The game loop is executing.");
            //1. update information such as character position
            update();
            //2. draw the screen with the updated information
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //divide nano time by 1 mill in order to convert to milliseconds for the sleep thread
                if (remainingTime < 0 ) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update() {

        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            //nothing for now
        }

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //debug stuff
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //TILE
        tileM.draw(g2); //make sure that the tile draw is before the player because if not it will layer the player behind the tiles

        //OBJECT
        for ( int i = 0; i < obj.length; i++ )  {

            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }


        g2.dispose();  //dispose of the graphics and release
    }
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic() {

        music.stop();
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }

}

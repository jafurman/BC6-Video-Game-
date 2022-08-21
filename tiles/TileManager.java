package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;



public class TileManager {

    GamePanel gp;
    public Tiles[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tiles[100];  //change this number depending on how many tiles we want to add into the game
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/gameBoard.txt");  //change world map in here
    }

    public void getTileImage() {  ///add new images that you want to create here

        setup(0,"water", true);
        setup(1,"path", false);
        setup(2,"rose", false);
        setup(3,"wall", true);
        setup(4,"grass", false);
        setup(5,"tree", true);
        setup(6,"space", false);
        setup(7,"lava", true);
        setup(8,"planetRing", true);
        setup(9,"planetTopRight", true);
        setup(10,"planetTopLeft", true);
        setup(11,"planetBottomLeft", true);
        setup(12,"planetBottomRight", true);
        setup(13,"planetMid", true);
        setup(14,"leftTopCornerPath", false);
        setup(15,"topPath", false);
        setup(16,"rightTopCornerPath", false);
        setup(17,"leftPath", false);
        setup(18,"rightPath", false);
        setup(19,"leftBottomCornerPath", false);
        setup(20,"bottomPath", false);
        setup(21,"rightBottomCornerPath", false);
        setup(22,"topLeftCornerWater", true);
        setup(23,"topWater", true);
        setup(24,"topRightCornerWater", true);
        setup(25,"leftWater", true);
        setup(26,"rightWater", true);
        setup(27,"bottomLeftCornerWater", true);
        setup(28,"bottomWater", true);
        setup(29,"bottomRightCornerWater", true);
        setup(30,"chessDark", false);
        setup(31,"chessLight", false);
        setup(32,"chessDarkWhiteRook", false);
        setup(33,"chessDarkWhiteKnight", false);
        setup(34,"chessDarkWhiteBishop", false);
        setup(35,"chessDarkWhiteQueen", false);
        setup(36,"chessDarkWhiteKing", false);
        setup(37,"chessLightWhiteRook", false);
        setup(38,"chessLightWhiteKnight", false);
        setup(39,"chessLightWhiteBishop", false);
        setup(38,"chessLightWhiteQueen", false);
        setup(38,"chessLightWhiteKing", false);
        setup(39,"chessLightWhitePawn", false);
        setup(40,"chessDarkWhitePawn", false);
        setup(41,"chessDarkBluePawn", false);
        setup(42,"chessDarkBlueRook", false);
        setup(43,"chessDarkBlueKnight", false);
        setup(44,"chessDarkBlueBishop", false);
        setup(45,"chessDarkBlueKing", false);
        setup(46,"chessDarkBlueQueen", false);
        setup(47,"chessLightBluePawn", false);
        setup(48,"chessLightBlueRook", false);
        setup(49,"chessLightBlueKnight", false);
        setup(50,"chessLightBlueBishop", false);
        setup(51,"chessLightBlueKing", false);
        setup(52,"chessLightBlueQueen", false);
        setup(53,"bluePlanetTopRight", true);
        setup(54,"bluePlanetTopLeft", true);
        setup(55,"bluePlanetBottomLeft", true);
        setup(56,"bluePlanetBottomRight", true);
        setup(57,"milky", true);
        setup(58,"milkyTopLeft", true);
        setup(59,"milkyTopRight", true);
        setup(60,"milkyBottomLeft", true);
        setup(61,"milkyBottomRight", true);



    }
        public void setup (int index, String imageName, boolean collision) {

            UtilityTool uTool = new UtilityTool();

            try {
                tile[index] = new Tiles();
                tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
                tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
                tile[index].collision = collision;

            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
        public void loadMap(String filePath){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
            br.close();
        } catch (Exception e) {

        }
        }
        public void draw(Graphics2D g2) {  //once the tile is in the getTileImage class we can bring the tile into here and draw it on the board.

            int worldCol = 0;
            int worldRow = 0;


            while ( worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;  //if player position is 500 500 these values here help the player
                int screenY = worldY - gp.player.worldY + gp.player.screenY;  //values and screen values stay in conjunction with each other

                if ( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&    //This helps with rendering. This allows only tiles to be drawn when the player is near.
                     worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                     worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                     worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);

                }
                worldCol++;
                if ( worldCol == gp.maxWorldCol) {
                    worldCol = 0;

                    worldRow++;

                }
            }
        }
    }

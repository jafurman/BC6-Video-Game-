package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Map extends SuperObject{

    GamePanel gp;

    public OBJ_Map (GamePanel gp) {

        this.gp = gp;

        name = "map";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/map.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

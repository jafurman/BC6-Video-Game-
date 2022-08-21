package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_RecordPlayer extends SuperObject{

    GamePanel gp;

    public OBJ_RecordPlayer (GamePanel gp ) {

        this.gp = gp;

        name = "record player";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/recordPlayer.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }

}

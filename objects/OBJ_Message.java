package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Message extends SuperObject{

    GamePanel gp;

    public OBJ_Message (GamePanel gp) {

        this.gp = gp;

        name = "message";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/message.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package SceneDataModule;

import java.awt.*;

/**
 * Created by Zheng-Yuan on 12/14/2015.
 */
public interface SceneObjectInterface {

    int getType();
    void draw(Graphics2D g, int x, int y, int dx, int dy) throws Exception;

}

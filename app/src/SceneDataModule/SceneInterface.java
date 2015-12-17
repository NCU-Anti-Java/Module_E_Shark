package SceneDataModule;

/**
 * Created by Zheng-Yuan on 12/14/2015.
 */
public interface SceneInterface {

    int BLOCK_SIZE = 100;
    SceneObjectInterface getMap(int row, int col);
    double getWidth();
    double getHeight();
    int getNrow();
    int getNcol();

}

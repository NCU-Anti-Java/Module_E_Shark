package SceneDataModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class Block implements SceneObjectInterface {

    /**
     * Prairie, Grass, Mountain, Forest, Water
     */
    private int type;

    private static BufferedImage[] image;
    private static final int MAX_TYPE = 5;
    private static final String BLOCKS_FILE_NAME = "/block.png";
    private static final int BLOCK_IMAGE_SIZE = 16;

    static{

        image = new BufferedImage[MAX_TYPE];
        try{
            BufferedImage temp = ImageIO.read(Block.class.getResource(BLOCKS_FILE_NAME));
            int numberOfRow = temp.getHeight() / BLOCK_IMAGE_SIZE;
            int numberOfCol = temp.getWidth() / BLOCK_IMAGE_SIZE;
            for(int row = 0; row < numberOfRow; row++) {
                for(int col = 0; col < numberOfCol; col++){
                    int id = row * numberOfCol + col;
                    image[id] = temp.getSubimage(col * BLOCK_IMAGE_SIZE, row * BLOCK_IMAGE_SIZE, BLOCK_IMAGE_SIZE, BLOCK_IMAGE_SIZE);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public Block() throws Exception{ setType(0); }

    public Block(int type) throws Exception{
        setType(type);
    }

    public int getType(){ return type; }

    public void setType(int type) throws Exception {

        if(type < 0 || type >= MAX_TYPE)
            throw new IllegalArgumentException("[Block] The block type must be between 0 ~ "  + MAX_TYPE + ", but receive " + type);

        this.type = type;

    }

    public void draw(Graphics2D g, int x, int y, int dx, int dy) throws Exception{

        if(image == null || image[type] == null)
            throw new NullPointerException("[Block.draw] The block image is null");

        g.drawImage(image[type], x, y, dx, dy, null);

    }

}

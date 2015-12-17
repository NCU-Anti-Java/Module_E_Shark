package SceneDataModule;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class Scene implements SceneInterface {

    private double width;
    private double height;

    private int nrow;
    private int ncol;

    private int[][] types;
    private SceneObjectInterface[][] map;

    public Scene(){}

    public Scene(String filename) throws Exception { loadMapFile(filename); }

    public void loadMapFile(String filename) throws Exception {

        File file = new File(Scene.class.getResource(filename).toURI());
        Scanner input = new Scanner(file);
        setNrow(input.nextInt());
        height = nrow * BLOCK_SIZE;
        setNcol(input.nextInt());
        width = ncol * BLOCK_SIZE;

        types = new int[nrow][ncol];
        for (int row = 0; row < nrow; row++) {
            for (int col = 0; col < ncol; col++) {
                types[row][col] = input.nextInt();
            }
        }

    }

    public void buildMap() throws Exception {

        if(types == null)
            throw new NullPointerException("[Scene.buildMap] types should not be null");
        if(nrow <= 0)
            throw new IllegalArgumentException("[Scene.buildMap] nrow should be greater than zero");
        if(ncol <= 0)
            throw new IllegalArgumentException("[Scene.buildMap] ncol should be greater than zero");

        map = new Block[nrow][ncol];
        for (int row = 0; row < nrow; row++) {
            for (int col = 0; col < ncol; col++) {
                map[row][col] = new Block(types[row][col]);
            }
        }

    }

    public int getNrow(){ return nrow; }

    public void setNrow(int nrow) {

        if(nrow <= 0)
            throw new IllegalArgumentException("[Scene.setNrow] nrow should be greater than zero, but receive " + nrow);

        this.nrow = nrow;

    }

    public int getNcol() {
        return ncol;
    }

    public void setNcol(int ncol) {

        if(ncol <= 0)
            throw new IllegalArgumentException("[Scene.setNcol] ncol should be greater than zero, but receive " + ncol);

        this.ncol = ncol;

    }

    public SceneObjectInterface getMap(int row, int col) {

        if(row < 0 || row >= nrow)
            throw new IllegalArgumentException("[Scene.getMap] row is out of range, but receive " + row);
        if(col < 0 || col >= ncol)
            throw new IllegalArgumentException("[Scene.getMap] col is out of range, but receive " + col);

        return map[row][col];

    }

    public void setMap(int row, int col, SceneObjectInterface block) {

        if(row < 0 || row >= nrow)
            throw new IllegalArgumentException("[Scene.setMap] row is out of range, but receive " + row);
        if(col < 0 || col >= ncol)
            throw new IllegalArgumentException("[Scene.setMap] col is out of range, but receive " + col);

        map[row][col] = block;

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getTypes(int row, int col){

        if(row < 0 || row >= nrow)
            throw new IllegalArgumentException("[Scene.getTypes] row is out of range, but receive " + row);
        if(col < 0 || col >= ncol)
            throw new IllegalArgumentException("[Scene.getTypes] col is out of range, but receive " + col);

        return types[row][col];

    }

    public void setTypes(int row, int col, int type){

        if(row < 0 || row >= nrow)
            throw new IllegalArgumentException("[Scene.setTypes] row is out of range, but receive " + row);
        if(col < 0 || col >= ncol)
            throw new IllegalArgumentException("[Scene.setTypes] col is out of range, but receive " + col);

        types[row][col] = type;

    }

    public void setTypes(int[][] types){
        this.types = types;
    }

}

package SceneRenderEngine;

import SceneDataModule.*;
import com.sun.glass.ui.Size;
import com.sun.java.browser.plugin2.DOM;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Zheng-Yuan on 12/13/2015.
 */
public class SceneRenderEngine {

    // GamePanel width and height
    private double width;
    private double height;

    private Graphics2D g;

    private SceneInterface scene;
    private DOMInterface observer;

    public SceneRenderEngine(Graphics2D g, double width, double height){
        this.g = g;
        this.width = width;
        this.height = height;
    }

    public void renderScene() throws Exception {

        if(observer == null)
            throw new NullPointerException("[SceneRenderEngine.renderScene] observer should not be null");
        if(scene == null)
            throw new NullPointerException("[SceneRenderEngine.renderScene] scene should not be null");

        Point2D viewPoint = observer.getVirtualCharacterXY();
        Rectangle2D.Double window = getViewWindow(viewPoint);

        int windowGridX = (int)Math.floor(window.getX() / scene.BLOCK_SIZE);
        int windowGridY = (int)Math.floor(window.getY() / scene.BLOCK_SIZE);
        int maxWindowGridX = (int)Math.floor(window.getMaxX() / scene.BLOCK_SIZE);
        int maxWindowGridY = (int)Math.floor(window.getMaxY() / scene.BLOCK_SIZE);
        double offsetX = window.getX() - (int)Math.floor(window.getX() / scene.BLOCK_SIZE) * scene.BLOCK_SIZE;
        double offsetY = window.getY() - (int)Math.floor(window.getY() / scene.BLOCK_SIZE) * scene.BLOCK_SIZE;
        if(Math.abs(offsetX) < 1e-6)
            maxWindowGridX--;
        if(Math.abs(offsetY) < 1e-6)
            maxWindowGridY--;

        for(int row = windowGridY; row <= maxWindowGridY; row++){
            for(int col = windowGridX; col <= maxWindowGridX; col++){
                scene.getMap(row, col).draw(g, (int)((col - windowGridX) * scene.BLOCK_SIZE - offsetX), (int)((row - windowGridY) * scene.BLOCK_SIZE - offsetY), scene.BLOCK_SIZE, scene.BLOCK_SIZE);
            }
        }

    }

    public Rectangle2D.Double getViewWindow(Point2D viewPoint) throws Exception{

        if(viewPoint.getX() < 0 || viewPoint.getX() > scene.getWidth())
            throw new IllegalArgumentException("[Scene.getViewWindow] Parameter viewPoint x should resides in scene, but receive " + viewPoint.getX());
        if(viewPoint.getY() < 0 || viewPoint.getY() > scene.getHeight())
            throw new IllegalArgumentException("[Scene.getViewWindow] Parameter viewPoint y should resides in scene, but receive " + viewPoint.getY());

        double windowX, windowY;

        if(viewPoint.getX() < width / 2.0f) {
            windowX = 0;
        }
        else if(viewPoint.getX() > scene.getWidth() - width / 2.0f){
            windowX = scene.getWidth() - width;
        }
        else{
            windowX = viewPoint.getX() - width / 2.0f;
        }

        if(viewPoint.getY() < height / 2.0f) {
            windowY = 0;
        }
        else if(viewPoint.getY() > scene.getHeight() - height / 2.0f){
            windowY = scene.getHeight() - height;
        }
        else{
            windowY = viewPoint.getY() - height / 2.0f;
        }

        return new Rectangle2D.Double(windowX, windowY, width, height);

    }

    public void setScene(SceneInterface scene){
        this.scene = scene;
    }

    public void setObserver(DOMInterface observer){
        this.observer = observer;
    }

}

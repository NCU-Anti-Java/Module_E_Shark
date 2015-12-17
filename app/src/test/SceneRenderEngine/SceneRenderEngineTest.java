package SceneRenderEngine;

import SceneDataModule.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Zheng-Yuan on 12/13/2015.
 */
public class SceneRenderEngineTest {

    public SceneRenderEngine sre;
    public final int GAME_PANEL_WIDTH = 500;
    public final int GAME_PANEL_HEIGHT = 400;
    public final int BLOCKSIZE = 100;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){

        BufferedImage canvas = new BufferedImage(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
        sre = new SceneRenderEngine((Graphics2D)canvas.getGraphics(), GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
        sre.setScene(new SceneInterface() {
            @Override
            public SceneObjectInterface getMap(int row, int col) {
                return new SceneObjectInterface() {
                    @Override
                    public int getType(){
                        return 0;
                    }

                    @Override
                    public void draw(Graphics2D g, int x, int y, int dx, int dy) {
                        System.out.println("Draw map (" + row + ", " + col + ") at (" + x +", " + y + "), Size (" + dx + ", " + dy + ")");
                    }
                };
            }

            @Override
            public double getWidth() {
                return 5000;
            }

            @Override
            public double getHeight() {
                return 2000;
            }

            @Override
            public int getNrow() {
                return 50;
            }

            @Override
            public int getNcol() {
                return 20;
            }
        });

    }

    @After
    public void clearUpStreams(){
        System.setOut(null);
    }

    @Test
    public void testRenderScene1() throws Exception {

        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("[SceneRenderEngine.renderScene] scene should not be null");
        sre.setScene(null); // in setUp(), we set default scene().
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return null;
            }
        });
        sre.renderScene();

    }

    @Test
    public void testRenderScene2() throws Exception {

        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("[SceneRenderEngine.renderScene] observer should not be null");
        sre.renderScene();

    }

    @Test
    public void testRenderScene3() throws Exception {

        System.setOut(new PrintStream(outContent));
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return new Point2D.Double(500, 300);
            }
        });

        sre.renderScene();
        int offsetX = 50;
        int offsetY = 0;
        int stRow = 1;
        int stCol = 2;
        int endRow = 4;
        int endCol = 7;
        for(int row = stRow; row <= endRow; row++){
            for(int col = stCol; col <= endCol; col++){
                String msg = "Should get map (" + row + ", " + col + ") draw at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + ") Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                String expected = "Draw map (" + row + ", " + col + ") at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + "), Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                assertTrue(msg, outContent.toString().contains(expected));
            }
        }

    }

    @Test
    public void testRenderScene4() throws Exception {

        System.setOut(new PrintStream(outContent));
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return new Point2D.Double(95, 87);
            }
        });

        sre.renderScene();
        int offsetX = 0;
        int offsetY = 0;
        int stRow = 0;
        int stCol = 0;
        int endRow = 3;
        int endCol = 4;
        for(int row = stRow; row <= endRow; row++){
            for(int col = stCol; col <= endCol; col++){
                String msg = "Should get map (" + row + ", " + col + ") draw at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + ") Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                String expected = "Draw map (" + row + ", " + col + ") at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + "), Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                assertTrue(msg, outContent.toString().contains(expected));
            }
        }

    }

    @Test
    public void testRenderScene5() throws Exception {

        System.setOut(new PrintStream(outContent));
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return new Point2D.Double(4900, 450);
            }
        });

        sre.renderScene();
        int offsetX = 0;
        int offsetY = 50;
        int stRow = 2;
        int stCol = 45;
        int endRow = 6;
        int endCol = 49;
        for(int row = stRow; row <= endRow; row++){
            for(int col = stCol; col <= endCol; col++){
                String msg = "Should get map (" + row + ", " + col + ") draw at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + ") Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                String expected = "Draw map (" + row + ", " + col + ") at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + "), Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                assertTrue(msg, outContent.toString().contains(expected));
            }
        }

    }

    @Test
    public void testRenderScene6() throws Exception {

        System.setOut(new PrintStream(outContent));
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return new Point2D.Double(735, 1955);
            }
        });

        sre.renderScene();
        int offsetX = 85;
        int offsetY = 0;
        int stRow = 16;
        int stCol = 4;
        int endRow = 19;
        int endCol = 9;
        for(int row = stRow; row <= endRow; row++){
            for(int col = stCol; col <= endCol; col++){
                String msg = "Should get map (" + row + ", " + col + ") draw at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + ") Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                String expected = "Draw map (" + row + ", " + col + ") at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + "), Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                assertTrue(msg, outContent.toString().contains(expected));
            }
        }

    }

    @Test
    public void testRenderScene7() throws Exception {

        System.setOut(new PrintStream(outContent));
        sre.setObserver(new DOMInterface() {
            @Override
            public Point2D getVirtualCharacterXY() {
                return new Point2D.Double(4885, 1925);
            }
        });

        sre.renderScene();
        int offsetX = 0;
        int offsetY = 0;
        int stRow = 16;
        int stCol = 45;
        int endRow = 19;
        int endCol = 49;
        for(int row = stRow; row <= endRow; row++){
            for(int col = stCol; col <= endCol; col++){
                String msg = "Should get map (" + row + ", " + col + ") draw at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + ") Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                String expected = "Draw map (" + row + ", " + col + ") at (" + ((col - stCol) * BLOCKSIZE - offsetX) + ", " + ((row - stRow) * BLOCKSIZE - offsetY) + "), Size (" + BLOCKSIZE + ", " + BLOCKSIZE + ")";
                assertTrue(msg, outContent.toString().contains(expected));
            }
        }

    }

    @Test
    public void testGetViewWindow1() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getViewWindow] Parameter viewPoint x should resides in scene, but receive -15");
        Rectangle2D.Double ret = sre.getViewWindow(new Point2D() {
            @Override
            public double getX() {
                return -15;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {
            }
        });

    }

    @Test
    public void testGetViewWindow2() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getViewWindow] Parameter viewPoint x should resides in scene, but receive 5005");
        Rectangle2D.Double ret = sre.getViewWindow(new Point2D() {
            @Override
            public double getX() {
                return 5005;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {
            }
        });

    }

    @Test
    public void testGetViewWindow3() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getViewWindow] Parameter viewPoint y should resides in scene, but receive -156");
        Rectangle2D.Double ret = sre.getViewWindow(new Point2D() {
            @Override
            public double getX() {
                return 156;
            }

            @Override
            public double getY() {
                return -156;
            }

            @Override
            public void setLocation(double x, double y) {
            }
        });

    }

    @Test
    public void testGetViewWindow4() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getViewWindow] Parameter viewPoint y should resides in scene, but receive 3205");
        Rectangle2D.Double ret = sre.getViewWindow(new Point2D() {
            @Override
            public double getX() {
                return 156;
            }

            @Override
            public double getY() {
                return 3205;
            }

            @Override
            public void setLocation(double x, double y) {
            }
        });

    }

    @Test
    public void testGetViewWindow5() throws Exception {

        Rectangle2D.Double rect = sre.getViewWindow(new Point2D.Double(300, 500));
        assertEquals("getViewWindow is not as same as expected",
                new Rectangle2D.Double(50, 300, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT),
                rect);

    }

    @Test
    public void testGetViewWindow6() throws Exception {

        Rectangle2D.Double rect = sre.getViewWindow(new Point2D.Double(100, 50));
        assertEquals("getViewWindow is not as same as expected",
                new Rectangle2D.Double(0, 0, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT),
                rect);

    }

    @Test
    public void testGetViewWindow7() throws Exception {

        Rectangle2D.Double rect = sre.getViewWindow(new Point2D.Double(4900, 305));
        assertEquals("getViewWindow is not as same as expected",
                new Rectangle2D.Double(4500, 105, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT),
                rect);

    }

    @Test
    public void testGetViewWindow8() throws Exception {

        Rectangle2D.Double rect = sre.getViewWindow(new Point2D.Double(2500, 1950));
        assertEquals("getViewWindow is not as same as expected",
                new Rectangle2D.Double(2250, 1600, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT),
                rect);

    }

    @Test
    public void testGetViewWindow9() throws Exception {

        Rectangle2D.Double rect = sre.getViewWindow(new Point2D.Double(4900, 1900));
        assertEquals("getViewWindow is not as same as expected",
                new Rectangle2D.Double(4500, 1600, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT),
                rect);

    }

}
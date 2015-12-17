package SceneDataModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Zheng-Yuan on 12/13/2015.
 */
public class SceneTest {

    public Scene scene;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        scene = new Scene();
    }

    @Test
    public void testSetNrow_OutOfRange1() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.setNrow] nrow should be greater than zero, but receive 0");
        scene.setNrow(0);

    }

    @Test
    public void testSetNrow_OutOfRange2() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.setNrow] nrow should be greater than zero, but receive -2462");
        scene.setNrow(-2462);

    }

    @Test
    public void testSetNrow() throws Exception{

        scene.setNrow(5);
        assertEquals("nrow should be 5", 5, scene.getNrow());

    }

    @Test
    public void testSetNcol_OutOfRange1() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.setNcol] ncol should be greater than zero, but receive 0");
        scene.setNcol(0);

    }

    @Test
    public void testSetNcol_OutOfRange2() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.setNcol] ncol should be greater than zero, but receive -2456");
        scene.setNcol(-2456);

    }

    @Test
    public void testSetNcol() throws Exception {

        scene.setNcol(6);
        assertEquals("ncol should be 6", 6, scene.getNcol());

    }

    @Test
    public void testGetMap_OutOfRange1() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getMap] row is out of range, but receive 5");
        scene.setNrow(3);
        scene.setNcol(2);
        scene.getMap(5, 2);

    }

    @Test
    public void testGetMap_OutOfRange2() throws Exception {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getMap] col is out of range, but receive 4");
        scene.setNrow(3);
        scene.setNcol(2);
        scene.getMap(1, 4);

    }

    @Test
    public void testGetType() throws Exception {

        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setNrow(2);
        scene.setNcol(3);
        scene.setTypes(types);
        assertEquals("getType (0, 0) should be 0", 0, scene.getTypes(0, 0));
        assertEquals("getType (0, 1) should be 0", 0, scene.getTypes(0, 1));
        assertEquals("getType (0, 2) should be 1", 1, scene.getTypes(0, 2));
        assertEquals("getType (1, 0) should be 2", 2, scene.getTypes(1, 0));
        assertEquals("getType (1, 1) should be 3", 3, scene.getTypes(1, 1));
        assertEquals("getType (1, 2) should be 4", 4, scene.getTypes(1, 2));

    }

    @Test
    public void testGetType_OutOfRange1() throws Exception{

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getTypes] row is out of range, but receive 3");
        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setNrow(2);
        scene.setNcol(3);
        scene.setTypes(types);
        scene.getTypes(3, 2);

    }

    @Test
    public void testGetType_OutOfRange2() throws Exception{

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.getTypes] col is out of range, but receive 5");
        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setNrow(2);
        scene.setNcol(3);
        scene.setTypes(types);
        scene.getTypes(1, 5);

    }

    @Test
    public void testLoadMap_FileNotExist() throws Exception {

        expectedEx.expect(NullPointerException.class);
        // This file dose not exist.
        scene.loadMapFile("/unknow.txt");

    }

    @Test
    public void testLoadMap_FileExist() throws Exception{

        scene.loadMapFile("/testMapFile1.txt");
        assertEquals("MapFile nrow should be 2", 2, scene.getNrow());
        assertEquals("MapFile ncol should be 3", 3, scene.getNcol());
        assertEquals("MapFile types (0, 0) should be 0", 0, scene.getTypes(0, 0));
        assertEquals("MapFile types (0, 1) should be 1", 1, scene.getTypes(0, 1));
        assertEquals("MapFile types (0, 2) should be 0", 0, scene.getTypes(0, 2));
        assertEquals("MapFile types (1, 0) should be 3", 3, scene.getTypes(1, 0));
        assertEquals("MapFile types (1, 1) should be 4", 4, scene.getTypes(1, 1));
        assertEquals("MapFile types (1, 2) should be 2", 2, scene.getTypes(1, 2));

    }

    @Test
    public void testBuildMap() throws Exception{

        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setNrow(2);
        scene.setNcol(3);
        scene.setTypes(types);

        scene.buildMap();

        assertEquals("Map Block Type (0, 0) should be 0", 0, scene.getMap(0, 0).getType());
        assertEquals("Map Block Type (0, 1) should be 0", 0, scene.getMap(0, 1).getType());
        assertEquals("Map Block Type (0, 2) should be 1", 1, scene.getMap(0, 2).getType());
        assertEquals("Map Block Type (1, 0) should be 2", 2, scene.getMap(1, 0).getType());
        assertEquals("Map Block Type (1, 1) should be 3", 3, scene.getMap(1, 1).getType());
        assertEquals("Map Block Type (1, 2) should be 4", 4, scene.getMap(1, 2).getType());

    }

    @Test
    public void testBuildMap_OutOfRange1() throws Exception{

        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("[Scene.buildMap] types should not be null");
        scene.buildMap();

    }

    @Test
    public void testBuildMap_OutOfRange2() throws Exception{

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.buildMap] nrow should be greater than zero");
        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setTypes(types);
        scene.buildMap();

    }

    @Test
    public void testBuildMap_OutOfRange3() throws Exception{

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("[Scene.buildMap] ncol should be greater than zero");
        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setTypes(types);
        scene.setNrow(3);
        scene.buildMap();

    }

    @Test
    public void testGetWidth() throws Exception{

        scene.loadMapFile("/testMapFile1.txt");
        assertEquals("Width should be 300", 300, (int)scene.getWidth());

    }

    @Test
    public void testGetHeight() throws Exception{

        scene.loadMapFile("/testMapFile1.txt");
        assertEquals("Height should be 200", 200, (int)scene.getHeight());

    }

    @Test
    public void testSetTypes() throws Exception {

        int[][] types = new int[][] {{ 0, 0, 1}, { 2, 3, 4}};
        scene.setTypes(types);
        scene.setNrow(2);
        scene.setNcol(3);
        scene.setTypes(1, 2, 4);
        assertEquals("setType should be 4", 4, scene.getTypes(1, 2));

    }

}
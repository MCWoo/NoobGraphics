package matt.noobgraphics;

import org.junit.Test;
import static org.junit.Assert.*;


import matt.noobgraphics.math.Vector3;

/**
 * Created by Matth on 7/13/2016.
 */
public class Vector3UnitTest {
    public static final double DELTA = 0.0000001;

    @Test
    public void testEquality() {
        Vector3 v1 = new Vector3();
        Vector3 v2 = new Vector3(1,0,0);
        Vector3 v3 = new Vector3(0,1,0);
        Vector3 v4 = new Vector3(0,0,1);

        assertTrue(v1.equals(v1));
        assertFalse(v1.equals(v2));
        assertFalse(v1.equals(v3));
        assertFalse(v1.equals(v4));

    }

    @Test
    public void testObjectEquality() {
        Vector3 v = new Vector3();
        Object o = new Object();

        assertTrue(v.equals((Object) v));
        assertFalse(v.equals(o));
        assertEquals(v,v);
    }

    @Test
    public void testDefaultConstructor() {
        Vector3 v = new Vector3();
        Vector3 result = new Vector3(0,0,0);
        assertEquals(v, result);
    }

    @Test
    public void testSet() {
        Vector3 u = new Vector3(1,2,3);
        Vector3 v = new Vector3();
        v.setV(0,1);
        v.setV(1,2);
        v.setV(2,3);
        assertEquals(u,v);
    }

    @Test
    public void testAccessors() {
        Vector3 u = new Vector3(1,2,3);
        assertEquals(1.0f, u.V(0), DELTA);
        assertEquals(2.0f, u.V(1), DELTA);
        assertEquals(3.0f, u.V(2), DELTA);

        assertEquals(1.0f, u.x(), DELTA);
        assertEquals(2.0f, u.y(), DELTA);
        assertEquals(3.0f, u.z(), DELTA);
    }

    @Test
    public void testAddition() {
        Vector3 a1 = new Vector3();
        Vector3 b1 = new Vector3(1,2,3);
        Vector3 r1 = new Vector3(1,2,3);
        assertTrue((a1.plus(b1)).equals(r1));
    }
}

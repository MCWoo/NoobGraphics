package matt.noobgraphics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import matt.noobgraphics.math.Matrix3;
import matt.noobgraphics.math.Matrix4;
import matt.noobgraphics.math.Vector3;

/**
 * Created by Matth on 7/27/2016.
 */
public class Matrix3UnitTest {
    public static final double DELTA = 0.0000001;

    @Test
    public void testObjectEquality() {
        Matrix3 m = new Matrix3();
        Matrix3 m2 = new Matrix3();
        Object o = new Object();

        assertNotEquals(m, o);
        assertEquals(m, m);
        assertEquals(m, m2);

        m2.m[0] = 1.0f;
        assertNotEquals(m, m2);
    }

    @Test
    public void testDefaultConstructor() {
        Matrix3 mat = new Matrix3();

        for (int i = 0; i < Matrix3.MATRIX_SIZE; i++)
            assertEquals(0.0f, mat.m[i], DELTA);
    }

    @Test
    public void test9Constructor() {
        float m00 = 1.0f;   float m10 = 4.0f;   float m20 = 7.0f;
        float m01 = 2.0f;   float m11 = 5.0f;   float m21 = 8.0f;
        float m02 = 3.0f;   float m12 = 6.0f;   float m22 = 9.0f;

        Matrix3 mat = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);

        assertEquals(m00, mat.m[0], DELTA);
        assertEquals(m01, mat.m[1], DELTA);
        assertEquals(m02, mat.m[2], DELTA);

        assertEquals(m10, mat.m[3], DELTA);
        assertEquals(m11, mat.m[4], DELTA);
        assertEquals(m12, mat.m[5], DELTA);

        assertEquals(m20, mat.m[6], DELTA);
        assertEquals(m21, mat.m[7], DELTA);
        assertEquals(m22, mat.m[8], DELTA);
    }

    @Test
    public void testVec3Constructor() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 z = new Vector3(7.0f, 8.0f, 9.0f);

        Matrix3 m = new Matrix3(x,y,z);

        assertEquals(x.x(), m.m[0], DELTA);
        assertEquals(x.y(), m.m[1], DELTA);
        assertEquals(x.z(), m.m[2], DELTA);

        assertEquals(y.x(), m.m[3], DELTA);
        assertEquals(y.y(), m.m[4], DELTA);
        assertEquals(y.z(), m.m[5], DELTA);

        assertEquals(z.x(), m.m[6], DELTA);
        assertEquals(z.y(), m.m[7], DELTA);
        assertEquals(z.z(), m.m[8], DELTA);
    }

    @Test
    public void testMat4Constructor() {
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;    float m30 = 13.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;   float m31 = 14.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;   float m32 = 15.0f;
        float m03 = 4.0f;   float m13 = 8.0f;   float m23 = 12.0f;   float m33 = 16.0f;

        Matrix4 m4 = new Matrix4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
        Matrix3 m = new Matrix3(m4);

        assertEquals(m00,  m.m[0], DELTA);
        assertEquals(m01,  m.m[1], DELTA);
        assertEquals(m02,  m.m[2], DELTA);

        assertEquals(m10,  m.m[3], DELTA);
        assertEquals(m11,  m.m[4], DELTA);
        assertEquals(m12,  m.m[5], DELTA);

        assertEquals(m20,  m.m[6], DELTA);
        assertEquals(m21,  m.m[7], DELTA);
        assertEquals(m22,  m.m[8], DELTA);
    }

    @Test
    public void testDeepCopyConstructor() {
        float m00 = 1.0f;   float m10 = 4.0f;   float m20 = 7.0f;
        float m01 = 2.0f;   float m11 = 5.0f;   float m21 = 8.0f;
        float m02 = 3.0f;   float m12 = 6.0f;   float m22 = 9.0f;

        Matrix3 mat = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
        Matrix3 mat2 = new Matrix3(mat);

        assertEquals(mat, mat2);

        mat2.m[0] = 0.0f;

        assertNotEquals(mat, mat2);
    }
}

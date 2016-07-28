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

        Matrix3 m = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);

        assertEquals(m00, m.m[0], DELTA);
        assertEquals(m01, m.m[1], DELTA);
        assertEquals(m02, m.m[2], DELTA);

        assertEquals(m10, m.m[3], DELTA);
        assertEquals(m11, m.m[4], DELTA);
        assertEquals(m12, m.m[5], DELTA);

        assertEquals(m20, m.m[6], DELTA);
        assertEquals(m21, m.m[7], DELTA);
        assertEquals(m22, m.m[8], DELTA);
    }

    @Test
    public void testVec3Constructor() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 z = new Vector3(7.0f, 8.0f, 9.0f);

        Matrix3 m = new Matrix3(x, y, z);

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
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;   float m30 = 13.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;  float m31 = 14.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;  float m32 = 15.0f;
        float m03 = 4.0f;   float m13 = 8.0f;   float m23 = 12.0f;  float m33 = 16.0f;

        Matrix4 m4 = new Matrix4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
        Matrix3 m = new Matrix3(m4);

        assertEquals(m00, m.m[0], DELTA);
        assertEquals(m01, m.m[1], DELTA);
        assertEquals(m02, m.m[2], DELTA);

        assertEquals(m10, m.m[3], DELTA);
        assertEquals(m11, m.m[4], DELTA);
        assertEquals(m12, m.m[5], DELTA);

        assertEquals(m20, m.m[6], DELTA);
        assertEquals(m21, m.m[7], DELTA);
        assertEquals(m22, m.m[8], DELTA);
    }

    @Test
    public void testDeepCopyConstructor() {
        float m00 = 1.0f;
        float m10 = 4.0f;
        float m20 = 7.0f;
        float m01 = 2.0f;
        float m11 = 5.0f;
        float m21 = 8.0f;
        float m02 = 3.0f;
        float m12 = 6.0f;
        float m22 = 9.0f;

        Matrix3 mat = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
        Matrix3 mat2 = new Matrix3(mat);

        assertEquals(mat, mat2);

        mat2.m[0] = 0.0f;

        assertNotEquals(mat, mat2);
    }

    @Test
    public void testGetter() {
        float m00 = 1.0f;
        float m10 = 4.0f;
        float m20 = 7.0f;
        float m01 = 2.0f;
        float m11 = 5.0f;
        float m21 = 8.0f;
        float m02 = 3.0f;
        float m12 = 6.0f;
        float m22 = 9.0f;

        Matrix3 m = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);

        assertEquals(m00, m.M(0, 0), DELTA);
        assertEquals(m01, m.M(0, 1), DELTA);
        assertEquals(m02, m.M(0, 2), DELTA);

        assertEquals(m10, m.M(1, 0), DELTA);
        assertEquals(m11, m.M(1, 1), DELTA);
        assertEquals(m12, m.M(1, 2), DELTA);

        assertEquals(m20, m.M(2, 0), DELTA);
        assertEquals(m21, m.M(2, 1), DELTA);
        assertEquals(m22, m.M(2, 2), DELTA);
    }

    @Test
    public void testSetter() {
        float m00 = 1.0f;
        float m10 = 4.0f;
        float m20 = 7.0f;
        float m01 = 2.0f;
        float m11 = 5.0f;
        float m21 = 8.0f;
        float m02 = 3.0f;
        float m12 = 6.0f;
        float m22 = 9.0f;

        Matrix3 m = new Matrix3();

        m.set(0, 0, m00);
        m.set(1, 0, m10);
        m.set(2, 0, m20);
        m.set(0, 1, m01);
        m.set(1, 1, m11);
        m.set(2, 1, m21);
        m.set(0, 2, m02);
        m.set(1, 2, m12);
        m.set(2, 2, m22);

        assertEquals(m00, m.m[0], DELTA);
        assertEquals(m01, m.m[1], DELTA);
        assertEquals(m02, m.m[2], DELTA);

        assertEquals(m10, m.m[3], DELTA);
        assertEquals(m11, m.m[4], DELTA);
        assertEquals(m12, m.m[5], DELTA);

        assertEquals(m20, m.m[6], DELTA);
        assertEquals(m21, m.m[7], DELTA);
        assertEquals(m22, m.m[8], DELTA);
    }

    @Test
    public void testMultiply() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 z = new Vector3(7.0f, 8.0f, 9.0f);

        Matrix3 m = new Matrix3(x,y,z);
        Matrix3 m2 = new Matrix3(z,x,y);
        Matrix3 identity = Matrix3.identity();

        Matrix3 rightIdentityResult = m.multiply(identity);
        Matrix3 leftIdentityResult = identity.multiply(m);

        Matrix3 mSquaredResult = m.multiply(m);
        Matrix3 mm2 = m.multiply(m2);
        Matrix3 m2m = m2.multiply(m);

        // Pre-calculated answers
        Matrix3 mSquaredAnswer = new Matrix3(30.0f, 36.0f, 42.0f, 66.0f, 81.0f, 96.0f, 102.0f, 126.0f, 150.0f);
        Matrix3 mm2Answer = new Matrix3(102.0f, 126.0f, 150.0f, 30.0f, 36.0f, 42.0f, 66.0f, 81.0f, 96.0f);
        Matrix3 m2mAnswer = new Matrix3(21.0f, 27.0f, 33.0f, 57.0f, 72.0f, 87.0f, 93.0f, 117.0f, 141.0f);

        assertEquals(m, rightIdentityResult);
        assertEquals(m, leftIdentityResult);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix3.MATRIX_SIZE; i++)
            assertEquals(mSquaredAnswer.m[i], mSquaredResult.m[i], DELTA);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix3.MATRIX_SIZE; i++)
            assertEquals(mm2Answer.m[i], mm2.m[i], DELTA);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix3.MATRIX_SIZE; i++)
            assertEquals(m2mAnswer.m[i], m2m.m[i], DELTA);
    }

    @Test
    public void testMultiplyVector() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 z = new Vector3(7.0f, 8.0f, 9.0f);

        Matrix3 m = new Matrix3(x,y,z);

        Vector3 mx = m.multiply(x);
        Vector3 my = m.multiply(y);
        Vector3 mz = m.multiply(z);

        Vector3 mxAnswer = new Vector3(30.0f, 36.0f, 42.0f);
        Vector3 myAnswer = new Vector3(66.0f, 81.0f, 96.0f);
        Vector3 mzAnswer = new Vector3(102.0f, 126.0f, 150.0f);

        assertEquals(mxAnswer, mx);
        assertEquals(myAnswer, my);
        assertEquals(mzAnswer, mz);
    }

    @Test
    public void testRotateX() {
        Vector3 x = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 y = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 z = new Vector3(0.0f, 0.0f, 1.0f);

        Matrix3 rotX = Matrix3.rotateX((float) (Math.PI / 2.0));

        Vector3 x2 = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 y2 = new Vector3(0.0f, 0.0f, 1.0f);
        Vector3 z2 = new Vector3(0.0f, -1.0f, 0.0f);

        Vector3 Tx = rotX.multiply(x);
        Vector3 Ty = rotX.multiply(y);
        Vector3 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }

    @Test
    public void testRotateY() {
        Vector3 x = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 y = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 z = new Vector3(0.0f, 0.0f, 1.0f);

        Matrix3 rotX = Matrix3.rotateY((float) (Math.PI / 2.0));

        Vector3 x2 = new Vector3(0.0f, 0.0f, -1.0f);
        Vector3 y2 = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 z2 = new Vector3(1.0f, 0.0f, 0.0f);

        Vector3 Tx = rotX.multiply(x);
        Vector3 Ty = rotX.multiply(y);
        Vector3 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }

    @Test
    public void testRotateZ() {
        Vector3 x = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 y = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 z = new Vector3(0.0f, 0.0f, 1.0f);

        Matrix3 rotX = Matrix3.rotateZ((float) (Math.PI / 2.0));

        Vector3 x2 = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 y2 = new Vector3(-1.0f, 0.0f, 0.0f);
        Vector3 z2 = new Vector3(0.0f, 0.0f, 1.0f);

        Vector3 Tx = rotX.multiply(x);
        Vector3 Ty = rotX.multiply(y);
        Vector3 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }

    @Test
    public void testTranspose() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 z = new Vector3(7.0f, 8.0f, 9.0f);

        Matrix3 m = new Matrix3(x,y,z);
        Matrix3 transpose = m.transpose();

        assertEquals(m.M(0,0), transpose.M(0,0), DELTA);
        assertEquals(m.M(0,1), transpose.M(1,0), DELTA);
        assertEquals(m.M(0,2), transpose.M(2,0), DELTA);

        assertEquals(m.M(1,0), transpose.M(0,1), DELTA);
        assertEquals(m.M(1,1), transpose.M(1,1), DELTA);
        assertEquals(m.M(1,2), transpose.M(2,1), DELTA);

        assertEquals(m.M(2,0), transpose.M(0,2), DELTA);
        assertEquals(m.M(2,1), transpose.M(1,2), DELTA);
        assertEquals(m.M(2,2), transpose.M(2,2), DELTA);
    }
}

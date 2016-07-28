package matt.noobgraphics;

import org.junit.Test;

import matt.noobgraphics.math.Matrix3;
import matt.noobgraphics.math.Matrix4;
import matt.noobgraphics.math.Vector3;
import matt.noobgraphics.math.Vector4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Matth on 7/13/2016.
 */
public class Matrix4UnitTest {
    public static final double DELTA = 0.0000001;

    @Test
    public void testObjectEquality() {
        Matrix4 m = new Matrix4();
        Matrix4 m2 = new Matrix4();
        Object o = new Object();

        assertNotEquals(m, o);
        assertEquals(m, m);
        assertEquals(m, m2);

        m2.m[0] = 1.0f;
        assertNotEquals(m, m2);
    }

    @Test
    public void testDefaultConstructor() {
        Matrix4 m = new Matrix4();

        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertEquals(0.0f, m.m[i], DELTA);
    }

    @Test
    public void test16Constructor() {
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;    float m30 = 13.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;   float m31 = 14.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;   float m32 = 15.0f;
        float m03 = 4.0f;   float m13 = 8.0f;   float m23 = 12.0f;   float m33 = 16.0f;

        Matrix4 m = new Matrix4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);

        assertEquals(m00, m.m[0], DELTA);
        assertEquals(m01, m.m[1], DELTA);
        assertEquals(m02, m.m[2], DELTA);
        assertEquals(m03, m.m[3], DELTA);

        assertEquals(m10, m.m[4], DELTA);
        assertEquals(m11, m.m[5], DELTA);
        assertEquals(m12, m.m[6], DELTA);
        assertEquals(m13, m.m[7], DELTA);

        assertEquals(m20, m.m[8], DELTA);
        assertEquals(m21, m.m[9], DELTA);
        assertEquals(m22, m.m[10], DELTA);
        assertEquals(m23, m.m[11], DELTA);

        assertEquals(m30, m.m[12], DELTA);
        assertEquals(m31, m.m[13], DELTA);
        assertEquals(m32, m.m[14], DELTA);
        assertEquals(m33, m.m[15], DELTA);
    }

    @Test
    public void testVec4Constructor() {
        Vector4 x = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 y = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 z = new Vector4(9.0f, 10.0f, 11.0f, 12.0f);
        Vector4 e = new Vector4(13.0f, 14.0f, 15.0f, 16.0f);

        Matrix4 m = new Matrix4(x,y,z,e);

        assertEquals(x.x(), m.M(0, 0), DELTA);
        assertEquals(x.y(), m.M(0, 1), DELTA);
        assertEquals(x.z(), m.M(0, 2), DELTA);
        assertEquals(x.w(), m.M(0, 3), DELTA);

        assertEquals(y.x(), m.M(1, 0), DELTA);
        assertEquals(y.y(), m.M(1, 1), DELTA);
        assertEquals(y.z(), m.M(1, 2), DELTA);
        assertEquals(y.w(), m.M(1, 3), DELTA);

        assertEquals(z.x(), m.M(2, 0), DELTA);
        assertEquals(z.y(), m.M(2, 1), DELTA);
        assertEquals(z.z(), m.M(2, 2), DELTA);
        assertEquals(z.w(), m.M(2, 3), DELTA);

        assertEquals(e.x(), m.M(3, 0), DELTA);
        assertEquals(e.y(), m.M(3, 1), DELTA);
        assertEquals(e.z(), m.M(3, 2), DELTA);
        assertEquals(e.w(), m.M(3, 3), DELTA);
    }

    @Test
    public void testVec3Constructor() {
        Vector3 x = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 y = new Vector3(5.0f, 6.0f, 7.0f);
        Vector3 z = new Vector3(9.0f, 10.0f, 11.0f);
        Vector3 e = new Vector3(13.0f, 14.0f, 15.0f);

        Matrix4 m = new Matrix4(x,y,z,e);

        assertEquals(x.x(), m.m[0], DELTA);
        assertEquals(x.y(), m.m[1], DELTA);
        assertEquals(x.z(), m.m[2], DELTA);
        assertEquals(0.0f,  m.m[3], DELTA);

        assertEquals(y.x(), m.m[4], DELTA);
        assertEquals(y.y(), m.m[5], DELTA);
        assertEquals(y.z(), m.m[6], DELTA);
        assertEquals(0.0f,  m.m[7], DELTA);

        assertEquals(z.x(), m.m[8],  DELTA);
        assertEquals(z.y(), m.m[9],  DELTA);
        assertEquals(z.z(), m.m[10], DELTA);
        assertEquals(0.0f,  m.m[11], DELTA);

        assertEquals(e.x(), m.m[12], DELTA);
        assertEquals(e.y(), m.m[13], DELTA);
        assertEquals(e.z(), m.m[14], DELTA);
        assertEquals(1.0f,  m.m[15], DELTA);
    }

    @Test
    public void testMat3Constructor() {
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;

        Matrix3 m3 = new Matrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
        Matrix4 m  = new Matrix4(m3);

        assertEquals(m00,  m.m[0], DELTA);
        assertEquals(m01,  m.m[1], DELTA);
        assertEquals(m02,  m.m[2], DELTA);
        assertEquals(0.0f, m.m[3], DELTA);

        assertEquals(m10,  m.m[4], DELTA);
        assertEquals(m11,  m.m[5], DELTA);
        assertEquals(m12,  m.m[6], DELTA);
        assertEquals(0.0f, m.m[7], DELTA);

        assertEquals(m20,  m.m[8], DELTA);
        assertEquals(m21,  m.m[9], DELTA);
        assertEquals(m22,  m.m[10], DELTA);
        assertEquals(0.0f, m.m[11], DELTA);

        assertEquals(0.0f,  m.m[12], DELTA);
        assertEquals(0.0f,  m.m[13], DELTA);
        assertEquals(0.0f,  m.m[14], DELTA);
        assertEquals(1.0f, m.m[15], DELTA);
    }

    @Test
    public void testDeepCopyConstructor() {
        Vector4 x = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 y = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 z = new Vector4(9.0f, 10.0f, 11.0f, 12.0f);
        Vector4 e = new Vector4(13.0f, 14.0f, 15.0f, 16.0f);

        Matrix4 m = new Matrix4(x,y,z,e);
        Matrix4 m2 = new Matrix4(m);

        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertEquals(m.m[i], m2.m[i], DELTA);

        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            m2.m[i] = 0.0f;

        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertNotEquals(m.m[i], m2.m[i], DELTA);
    }

    @Test
    public void testGetter() {
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;    float m30 = 13.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;   float m31 = 14.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;   float m32 = 15.0f;
        float m03 = 4.0f;   float m13 = 8.0f;   float m23 = 12.0f;   float m33 = 16.0f;

        Matrix4 m = new Matrix4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);

        assertEquals(m00, m.M(0,0), DELTA);
        assertEquals(m01, m.M(0,1), DELTA);
        assertEquals(m02, m.M(0,2), DELTA);
        assertEquals(m03, m.M(0,3), DELTA);

        assertEquals(m10, m.M(1,0), DELTA);
        assertEquals(m11, m.M(1,1), DELTA);
        assertEquals(m12, m.M(1,2), DELTA);
        assertEquals(m13, m.M(1,3), DELTA);

        assertEquals(m20, m.M(2,0), DELTA);
        assertEquals(m21, m.M(2,1), DELTA);
        assertEquals(m22, m.M(2,2), DELTA);
        assertEquals(m23, m.M(2,3), DELTA);

        assertEquals(m30, m.M(3,0), DELTA);
        assertEquals(m31, m.M(3,1), DELTA);
        assertEquals(m32, m.M(3,2), DELTA);
        assertEquals(m33, m.M(3,3), DELTA);
    }

    @Test
    public void testSetter() {
        float m00 = 1.0f;   float m10 = 5.0f;   float m20 = 9.0f;    float m30 = 13.0f;
        float m01 = 2.0f;   float m11 = 6.0f;   float m21 = 10.0f;   float m31 = 14.0f;
        float m02 = 3.0f;   float m12 = 7.0f;   float m22 = 11.0f;   float m32 = 15.0f;
        float m03 = 4.0f;   float m13 = 8.0f;   float m23 = 12.0f;   float m33 = 16.0f;

        Matrix4 m = new Matrix4();

        m.set(0,0, m00);   m.set(1,0, m10);   m.set(2,0, m20);   m.set(3,0, m30);
        m.set(0,1, m01);   m.set(1,1, m11);   m.set(2,1, m21);   m.set(3,1, m31);
        m.set(0,2, m02);   m.set(1,2, m12);   m.set(2,2, m22);   m.set(3,2, m32);
        m.set(0,3, m03);   m.set(1,3, m13);   m.set(2,3, m23);   m.set(3,3, m33);

        assertEquals(m00, m.m[0], DELTA);
        assertEquals(m01, m.m[1], DELTA);
        assertEquals(m02, m.m[2], DELTA);
        assertEquals(m03, m.m[3], DELTA);

        assertEquals(m10, m.m[4], DELTA);
        assertEquals(m11, m.m[5], DELTA);
        assertEquals(m12, m.m[6], DELTA);
        assertEquals(m13, m.m[7], DELTA);

        assertEquals(m20, m.m[8],  DELTA);
        assertEquals(m21, m.m[9],  DELTA);
        assertEquals(m22, m.m[10], DELTA);
        assertEquals(m23, m.m[11], DELTA);

        assertEquals(m30, m.m[12], DELTA);
        assertEquals(m31, m.m[13], DELTA);
        assertEquals(m32, m.m[14], DELTA);
        assertEquals(m33, m.m[15], DELTA);
    }

    @Test
    public void testIdentity() {
        Matrix4 m = Matrix4.identity();

        assertEquals(1.0f, m.M(0, 0), DELTA);
        assertEquals(0.0f, m.M(0, 1), DELTA);
        assertEquals(0.0f, m.M(0, 2), DELTA);
        assertEquals(0.0f, m.M(0, 3), DELTA);

        assertEquals(0.0f, m.M(1, 0), DELTA);
        assertEquals(1.0f, m.M(1, 1), DELTA);
        assertEquals(0.0f, m.M(1, 2), DELTA);
        assertEquals(0.0f, m.M(1, 3), DELTA);

        assertEquals(0.0f, m.M(2, 0), DELTA);
        assertEquals(0.0f, m.M(2, 1), DELTA);
        assertEquals(1.0f, m.M(2, 2), DELTA);
        assertEquals(0.0f, m.M(2, 3), DELTA);

        assertEquals(0.0f, m.M(3, 0), DELTA);
        assertEquals(0.0f, m.M(3, 1), DELTA);
        assertEquals(0.0f, m.M(3, 2), DELTA);
        assertEquals(1.0f, m.M(3, 3), DELTA);
    }

    @Test
    public void testMultiply() {
        Vector4 x = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 y = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 z = new Vector4(9.0f, 10.0f, 11.0f, 12.0f);
        Vector4 e = new Vector4(13.0f, 14.0f, 15.0f, 16.0f);

        Matrix4 identity = Matrix4.identity();
        Matrix4 m = new Matrix4(x,y,z,e);
        Matrix4 m2 = new Matrix4(e,x,y,z);

        Matrix4 rightIdentityResult = m.multiply(identity);
        Matrix4 leftIdentityResult = identity.multiply(m);
        Matrix4 mSquaredResult = m.multiply(m);
        Matrix4 mm2 = m.multiply(m2);
        Matrix4 m2m = m2.multiply(m);

        // Pre-calculated answers
        Matrix4 mSquaredAnswer = new Matrix4(90.0f, 100.0f, 110.0f, 120.0f, 202.0f, 228.0f, 254.0f, 280.0f,
                                       314.0f, 356.0f, 398.0f, 440.0f, 426.0f, 484.0f, 542.0f, 600.0f);
        Matrix4 mm2Answer = new Matrix4(426.0f, 484.0f, 542.0f, 600.0f, 90.0f, 100.0f, 110.0f, 120.0f,
                                        202.0f, 228.0f, 254.0f, 280.0f, 314.0f, 356.0f, 398.0f, 440.0f);
        Matrix4 m2mAnswer = new Matrix4(66.0f, 76.0f, 86.0f, 96.0f, 178.0f, 204.0f, 230.0f, 256.0f,
                                        290.0f, 332.0f, 374.0f, 416.0f, 402.0f, 460.0f, 518.0f, 576.0f);

        assertEquals(m, rightIdentityResult);
        assertEquals(m, leftIdentityResult);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertEquals(mSquaredAnswer.m[i], mSquaredResult.m[i], DELTA);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertEquals(mm2Answer.m[i], mm2.m[i], DELTA);

        // Multiplication involved, so use a delta
        for (int i = 0; i < Matrix4.MATRIX_SIZE; i++)
            assertEquals(m2mAnswer.m[i], m2m.m[i], DELTA);
    }

    @Test
    public void testMultiplyVector() {
        Vector4 x = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 y = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 z = new Vector4(9.0f, 10.0f, 11.0f, 12.0f);
        Vector4 e = new Vector4(13.0f, 14.0f, 15.0f, 16.0f);

        Matrix4 m = new Matrix4(x,y,z,e);

        Vector4 mxAnswer = new Vector4(90.0f, 100.0f, 110.0f, 120.0f);
        Vector4 myAnswer = new Vector4(202.0f, 228.0f, 254.0f, 280.0f);
        Vector4 mzAnswer = new Vector4(314.0f, 356.0f, 398.0f, 440.0f);
        Vector4 meAnswer = new Vector4(426.0f, 484.0f, 542.0f, 600.0f);

        Vector4 mx = m.multiply(x);
        Vector4 my = m.multiply(y);
        Vector4 mz = m.multiply(z);
        Vector4 me = m.multiply(e);

        assertEquals(mxAnswer, mx);
        assertEquals(myAnswer, my);
        assertEquals(mzAnswer, mz);
        assertEquals(meAnswer, me);
    }

    @Test
    public void testTranspose() {
        Vector4 x = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 y = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 z = new Vector4(9.0f, 10.0f, 11.0f, 12.0f);
        Vector4 e = new Vector4(13.0f, 14.0f, 15.0f, 16.0f);

        Matrix4 m = new Matrix4(x,y,z,e);
        Matrix4 transpose = m.transpose();

        assertEquals(m.M(0,0), transpose.M(0,0), DELTA);
        assertEquals(m.M(0,1), transpose.M(1,0), DELTA);
        assertEquals(m.M(0,2), transpose.M(2,0), DELTA);
        assertEquals(m.M(0,3), transpose.M(3,0), DELTA);

        assertEquals(m.M(1,0), transpose.M(0,1), DELTA);
        assertEquals(m.M(1,1), transpose.M(1,1), DELTA);
        assertEquals(m.M(1,2), transpose.M(2,1), DELTA);
        assertEquals(m.M(1,3), transpose.M(3,1), DELTA);

        assertEquals(m.M(2,0), transpose.M(0,2), DELTA);
        assertEquals(m.M(2,1), transpose.M(1,2), DELTA);
        assertEquals(m.M(2,2), transpose.M(2,2), DELTA);
        assertEquals(m.M(2,3), transpose.M(3,2), DELTA);

        assertEquals(m.M(3,0), transpose.M(0,3), DELTA);
        assertEquals(m.M(3,1), transpose.M(1,3), DELTA);
        assertEquals(m.M(3,2), transpose.M(2,3), DELTA);
        assertEquals(m.M(3,3), transpose.M(3,3), DELTA);
    }

    @Test
    public void testRotateX() {
        Vector4 x = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);
        Vector4 y = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
        Vector4 z = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);

        Matrix4 rotX = Matrix4.rotateX((float) (Math.PI / 2.0));

        Vector4 x2 = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);
        Vector4 y2 = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);
        Vector4 z2 = new Vector4(0.0f, -1.0f, 0.0f, 1.0f);

        Vector4 Tx = rotX.multiply(x);
        Vector4 Ty = rotX.multiply(y);
        Vector4 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }

    @Test
    public void testRotateY() {
        Vector4 x = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);
        Vector4 y = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
        Vector4 z = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);

        Matrix4 rotX = Matrix4.rotateY((float) (Math.PI / 2.0));

        Vector4 x2 = new Vector4(0.0f, 0.0f, -1.0f, 1.0f);
        Vector4 y2 = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
        Vector4 z2 = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);

        Vector4 Tx = rotX.multiply(x);
        Vector4 Ty = rotX.multiply(y);
        Vector4 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }

    @Test
    public void testRotateZ() {
        Vector4 x = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);
        Vector4 y = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
        Vector4 z = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);

        Matrix4 rotX = Matrix4.rotateZ((float) (Math.PI / 2.0));

        Vector4 x2 = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
        Vector4 y2 = new Vector4(-1.0f, 0.0f, 0.0f, 1.0f);
        Vector4 z2 = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);

        Vector4 Tx = rotX.multiply(x);
        Vector4 Ty = rotX.multiply(y);
        Vector4 Tz = rotX.multiply(z);

        for (int i = 0; i < Vector3.SIZE; i++) {
            assertEquals(x2.v[i], Tx.v[i], DELTA);
            assertEquals(y2.v[i], Ty.v[i], DELTA);
            assertEquals(z2.v[i], Tz.v[i], DELTA);
        }
    }
}

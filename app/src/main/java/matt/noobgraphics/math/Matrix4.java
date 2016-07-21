package matt.noobgraphics.math;

import matt.noobgraphics.BuildConfig;

/**
 * Column-major 4x4 matrix for homogeneous coordinates, meaning that m[0][0] is most likely a 1
 * [ 0  4  8 12 ]
 * [ 1  5  9 13 ]
 * [ 2  6 10 14 ]
 * [ 3  7 11 15 ]
 *
 * As a math library I try to refrain from loops for quickest access possible
 *
 * Created by Matth on 7/13/2016.
 */
public class Matrix4 {
    public final static int ROW_SIZE = 4;
    public final static int COL_SIZE = 4;
    public final static int MATRIX_SIZE = 16;

    public final float[] m = new float[16];

    /**
     * Creates an identity matrix
     */
    public Matrix4() { }

    /**
     * Constructor that takes in values like column vectors
     *
     * @param x1 the 1st component of the x vector
     * @param x2 the 2nd component of the x vector
     * @param x3 the 3rd component of the x vector
     * @param x4 the 4th component of the x vector
     * @param y1 the 1st component of the y vector
     * @param y2 the 2nd component of the y vector
     * @param y3 the 3rd component of the y vector
     * @param y4 the 4th component of the y vector
     * @param z1 the 1st component of the z vector
     * @param z2 the 2nd component of the z vector
     * @param z3 the 3rd component of the z vector
     * @param z4 the 4th component of the z vector
     * @param e1 the 1st component of the t vector
     * @param e2 the 2nd component of the t vector
     * @param e3 the 3rd component of the t vector
     * @param e4 the 4th component of the t vector
     */
    public Matrix4(float x1, float x2, float x3, float x4,
                   float y1, float y2, float y3, float y4,
                   float z1, float z2, float z3, float z4,
                   float e1, float e2, float e3, float e4) {
        m[0] = x1;  m[4] = y1;  m[8] = z1;  m[12] = e1;
        m[1] = x2;  m[5] = y2;  m[9] = z2;  m[13] = e2;
        m[2] = x3;  m[6] = y3;  m[10] = z3; m[14] = e3;
        m[3] = x4;  m[7] = y4;  m[11] = z4; m[15] = e4;
    }

    /**
     * Creates a Matrix4 out of 4 column vectors
     * @param x the x axis vector
     * @param y the y axis vector
     * @param z the z axis vector
     * @param e the translation
     */
    public Matrix4(Vector4 x, Vector4 y, Vector4 z, Vector4 e) {
        m[0] = x.x();   m[4] = y.x();   m[8]  = z.x();   m[12] = e.x();
        m[1] = x.y();   m[5] = y.y();   m[9]  = z.y();   m[13] = e.y();
        m[2] = x.z();   m[6] = y.z();   m[10] = z.z();   m[14] = e.z();
        m[3] = x.w();   m[7] = y.w();   m[11] = z.w();   m[15] = e.w();
    }

    /**
     * Creates a Matrix4 out of 4 column vectors. Last row is assumed to be 0, except for m[15]
     *
     * @param x the x axis vector
     * @param y the y axis vector
     * @param z the z axis vector
     * @param e the translation
     */
    public Matrix4(Vector3 x, Vector3 y, Vector3 z, Vector3 e) {
        m[0] = x.x();   m[4] = y.x();   m[8]  = z.x();   m[12] = e.x();
        m[1] = x.y();   m[5] = y.y();   m[9]  = z.y();   m[13] = e.y();
        m[2] = x.z();   m[6] = y.z();   m[10] = z.z();   m[14] = e.z();
        m[15] = 1.0f;
    }

    /**
     * Deep copy constructor
     */
    public Matrix4(Matrix4 other) {
        for (int i = 0; i < MATRIX_SIZE; i++)
            m[i] = other.m[i];
    }

    /**
     * Create Matrix4 out of a Matrix3
     * @param mat3
     */
    public Matrix4(Matrix3 mat3) {
        set(0,0,mat3.M(0,0));   set(1,0,mat3.M(1,0));   set(2,0,mat3.M(2,0));
        set(0,1,mat3.M(0,0));   set(1,1,mat3.M(1,1));   set(2,1,mat3.M(2,1));
        set(0,2,mat3.M(0,0));   set(1,2,mat3.M(1,2));   set(2,2,mat3.M(2,2));
        m[15] = 1.0f;
    }

    /**
     * Matrix times matrix multiplication. Returns a new matrix.
     * @param rhs the right hand side matrix
     * @return the resultant matrix
     */
    public Matrix4 multiply(Matrix4 rhs) {
        Matrix4 mat = new Matrix4();
        // Column 1
        mat.m[0] = m[0]*rhs.m[0] + m[4]*rhs.m[1] + m[8]*rhs.m[2]  + m[12]*rhs.m[3];
        mat.m[1] = m[1]*rhs.m[0] + m[5]*rhs.m[1] + m[9]*rhs.m[2]  + m[13]*rhs.m[3];
        mat.m[2] = m[2]*rhs.m[0] + m[6]*rhs.m[1] + m[10]*rhs.m[2] + m[14]*rhs.m[3];
        mat.m[3] = m[3]*rhs.m[0] + m[7]*rhs.m[1] + m[11]*rhs.m[2] + m[15]*rhs.m[3];

        // Column 2
        mat.m[4] = m[0]*rhs.m[4] + m[4]*rhs.m[5] + m[8]*rhs.m[6]  + m[12]*rhs.m[7];
        mat.m[5] = m[1]*rhs.m[4] + m[5]*rhs.m[5] + m[9]*rhs.m[6]  + m[13]*rhs.m[7];
        mat.m[6] = m[2]*rhs.m[4] + m[6]*rhs.m[5] + m[10]*rhs.m[6] + m[14]*rhs.m[7];
        mat.m[7] = m[3]*rhs.m[4] + m[7]*rhs.m[5] + m[11]*rhs.m[6] + m[15]*rhs.m[7];

        // Column 3
        mat.m[8]  = m[0]*rhs.m[8] + m[4]*rhs.m[9] + m[8]*rhs.m[10]  + m[12]*rhs.m[11];
        mat.m[9]  = m[1]*rhs.m[8] + m[5]*rhs.m[9] + m[9]*rhs.m[10]  + m[13]*rhs.m[11];
        mat.m[10] = m[2]*rhs.m[8] + m[6]*rhs.m[9] + m[10]*rhs.m[10] + m[14]*rhs.m[11];
        mat.m[11] = m[3]*rhs.m[8] + m[7]*rhs.m[9] + m[11]*rhs.m[10] + m[15]*rhs.m[11];

        // Column 4
        mat.m[12] = m[0]*rhs.m[12] + m[4]*rhs.m[13] + m[8]*rhs.m[14]  + m[12]*rhs.m[15];
        mat.m[13] = m[1]*rhs.m[12] + m[5]*rhs.m[13] + m[9]*rhs.m[14]  + m[13]*rhs.m[15];
        mat.m[14] = m[2]*rhs.m[12] + m[6]*rhs.m[13] + m[10]*rhs.m[14] + m[14]*rhs.m[15];
        mat.m[15] = m[3]*rhs.m[12] + m[7]*rhs.m[13] + m[11]*rhs.m[14] + m[15]*rhs.m[15];

        return mat;
    }

    /**
     * Matrix4 times vector4 multiplication
     * @param rhs the vector to transform
     * @return the resultant vector
     */
    public Vector4 multiply(Vector4 rhs) {
        return new Vector4(m[0]*rhs.x() + m[4]*rhs.y() + m[8]*rhs.z() + m[12]*rhs.w(),
                           m[1]*rhs.x() + m[5]*rhs.y() + m[9]*rhs.z() + m[13]*rhs.w(),
                           m[2]*rhs.x() + m[6]*rhs.y() + m[10]*rhs.z() + m[14]*rhs.w(),
                           m[3]*rhs.x() + m[7]*rhs.y() + m[11]*rhs.z() + m[15]*rhs.w());
    }

    /**
     * Transpose of the matrix. Returns a new matrix.
     * @return the transpose
     */
    public Matrix4 transpose() {
        Matrix4 mat = new Matrix4();
        mat.m[0] = m[0];    mat.m[4] = m[1];    mat.m[8]  = m[2];    mat.m[12] = m[3];
        mat.m[1] = m[4];    mat.m[5] = m[5];    mat.m[9]  = m[6];    mat.m[13] = m[7];
        mat.m[2] = m[8];    mat.m[6] = m[9];    mat.m[10] = m[10];   mat.m[14] = m[11];
        mat.m[3] = m[12];   mat.m[7] = m[13];   mat.m[11] = m[14];   mat.m[15] = m[15];
        return mat;
    }

    /**
     * The inverse of a model matrix. M = TR; M^-1 = R^-1 T^-1.
     * Returns a new Matrix
     * @return the fast inverse of a model/camera matrix
     */
    public Matrix4 fastInverse() { return new Matrix4(); }

    /**
     * Returns a new identity matrix
     */
    public static Matrix4 identity() {
        Matrix4 mat = new Matrix4();
        mat.m[0] = mat.m[5] = mat.m[10] = mat.m[15] = 1.0f;
        return mat;
    }

    /**
     * Creates and returns a new scale matrix
     * @param sx scale in the x dimension
     * @param sy scale in the x dimension
     * @param sz scale in the x dimension
     * @return the scale matrix
     */
    public static Matrix4 scale(float sx, float sy, float sz) {
        Matrix4 mat = new Matrix4();
        mat.m[0] = sx;
        mat.m[5] = sy;
        mat.m[10] = sz;
        return mat;
    }

    /**
     * Create a uniform scale matrix
     * @param s
     * @return
     */
    public static Matrix4 scale(float s) { return scale(s,s,s); }

    /**
     * Returns a new translation matrix
     * @param tx translation in the x dimension
     * @param ty translation in the x dimension
     * @param tz translation in the x dimension
     * @return the translation matrix
     */
    public static Matrix4 translate(float tx, float ty, float tz) {
        Matrix4 mat = Matrix4.identity();
        mat.m[12] = tx;
        mat.m[13] = ty;
        mat.m[14] = tz;
        return mat;
    }

    /**
     * Creates a rotation matrix around the given axis
     * @param axis the axis to rotate around
     * @param rad the degree in radians to rotate
     * @return the rotation matrix
     */
    public static Matrix4 rotate(Vector3 axis, float rad) {
        Matrix4 mat = new Matrix4();
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        mat.m[0] = (float) (cos + axis.x()*axis.x()*(1-cos));
        mat.m[1] = (float) (axis.y()*axis.x()*(1-cos) + axis.z()*sin);
        mat.m[2] = (float) (axis.z()*axis.x()*(1-cos) - axis.y()*sin);

        mat.m[4] = (float) (axis.x()*axis.y()*(1-cos) - axis.z()*sin);
        mat.m[5] = (float) (cos + axis.y()*axis.y()*(1-cos));
        mat.m[6] = (float) (axis.z()*axis.y()*(1-cos) + axis.x()*sin);

        mat.m[8] = (float) (axis.x()*axis.z()*(1-cos) + axis.y()*sin);
        mat.m[9] = (float) (axis.y()*axis.z()*(1-cos) - axis.x()*sin);
        mat.m[10] = (float) (cos + axis.z()*axis.z()*(1-cos));

        mat.m[15] = 1.0f;

        return mat;
    }

    /**
     * Create a rotation matrix around the positive X axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix4 rotateX(float rad) {
        Matrix4 mat = Matrix4.identity();
        mat.set(1,1,(float) Math.cos(rad));     mat.set(2,1,(float) Math.sin(rad));
        mat.set(1,2,(float) -Math.sin(rad));    mat.set(2,2,(float) Math.cos(rad));
        return mat;
    }
    /**
     * Create a rotation matrix around the positive Y axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix4 rotateY(float rad) {
        Matrix4 mat = Matrix4.identity();
        mat.set(0,0,(float) Math.cos(rad));     mat.set(2,0,(float) -Math.sin(rad));
        mat.set(0,2,(float) Math.sin(rad));     mat.set(2,2,(float) Math.cos(rad));
        return mat;
    }
    /**
     * Create a rotation matrix around the positive Z axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix4 rotateZ(float rad) {
        Matrix4 mat = Matrix4.identity();
        mat.set(0,0,(float) Math.cos(rad));     mat.set(1,0,(float) Math.sin(rad));
        mat.set(0,1,(float) -Math.sin(rad));    mat.set(1,1,(float) Math.cos(rad));
        return mat;
    }

    /**
     * Return a new perspective matrix
     * @param fovY the field of view along the Y axis, in radians
     * @param aspect the aspect ratio of the view (width/height)
     * @param zNear the near plane
     * @param zFar the far plane
     * @return a perspective projection matrix
     */
    public static Matrix4 perspective(float fovY, float aspect, float zNear, float zFar) {
        Matrix4 mat = new Matrix4();
        float tan2 = (float) Math.tan(fovY / 2.0f);

        mat.m[0] = 1.0f / (aspect * tan2);
        mat.m[5] = 1.0f / tan2;
        mat.m[10] = -1.0f * (zFar + zNear) / (zFar - zNear);
        mat.m[11] = -1.0f;
        mat.m[14] = (-2.0f * zFar * zNear) / (zFar - zNear);

        return mat;
    }

    /**
     * Return a new orthographic projection matrix
     *
     * @param left the left plane of the orthographic matrix
     * @param right the right plane of the orthographic matrix
     * @param bottom the bottom plane of the orthographic matrix
     * @param top the top plane of the orthographic matrix
     * @param zNear the near plane
     * @param zFar the far plane
     * @return an orthographic projection matrix
     */
    public static Matrix4 ortho(float left, float right, float bottom, float top, float zNear, float zFar) {
        Matrix4 mat = new Matrix4();

        mat.m[0] = 2.0f / (right-left);
        mat.m[5] = 2.0f / (top-bottom);
        mat.m[10] = -2.0f / (zFar - zNear);

        mat.m[12] = -1.0f * (right + left) / (right - left);
        mat.m[13] = -1.0f * (top + bottom) / (top - bottom);
        mat.m[14] = -1.0f * (zFar + zNear) / (zFar - zNear);
        mat.m[15] = 1.0f;

        return mat;
    }

    /**
     * 0 based index access
     * @param col The column of the element to access
     * @param row The row of the element to access
     * @return the element
     */
    public float M(int col, int row) {
        if (BuildConfig.DEBUG && (col < 0 || col >= ROW_SIZE || row < 0  || row >= COL_SIZE)) {
            throw new AssertionError("Invalid input into Matrix4.set(int, int)!");
        }

        return m[col*COL_SIZE + row];
    }

    /**
     * Return the array of elements
     * @return the array of elements
     */
    public float[] M() { return m; }

    /**
     * Setter method for the matrix
     * @param col the column index
     * @param row the row index
     * @param val value to set it to
     * @return true if valid column and row
     */
    public void set(int col, int row, float val) {
        if (BuildConfig.DEBUG && (col < 0 || col >= ROW_SIZE || row < 0  || row >= COL_SIZE)) {
            throw new AssertionError("Invalid input into Matrix4.set(int, int, float)!");
        }

        m[col*COL_SIZE + row] = val;
    }

    public void set(int col, Vector4 vec) {
        if (BuildConfig.DEBUG && (col < 0 || col >= ROW_SIZE)) {
            throw new AssertionError("Invalid input into Matrix4.set(int, Vector4)!");
        }

        int offset = col*COL_SIZE;
        m[offset]   = vec.v[0];
        m[offset+1] = vec.v[1];
        m[offset+2] = vec.v[2];
        m[offset+3] = vec.v[3];
    }
}

package matt.noobgraphics.math;

import matt.noobgraphics.BuildConfig;

/**
 * A column-major 3x3 matrix representation
 * [ 0  3  6 ]
 * [ 1  4  7 ]
 * [ 2  5  8 ]
 *
 * As a math library I try to refrain from loops for quickest access possible
 *
 * Created by Matth on 7/14/2016.
 */
public class Matrix3 {
    public final static int ROW_SIZE = 3;
    public final static int COL_SIZE = 3;
    public final static int MATRIX_SIZE = 9;

    public final float[] m = new float[9];

    public Matrix3() {}

    /**
     * Constructor that takes in values like column vectors
     *
     * @param x1 the 1st component of the x vector
     * @param x2 the 2nd component of the x vector
     * @param x3 the 3rd component of the x vector
     * @param y1 the 1st component of the y vector
     * @param y2 the 2nd component of the y vector
     * @param y3 the 3rd component of the y vector
     * @param z1 the 1st component of the z vector
     * @param z2 the 2nd component of the z vector
     * @param z3 the 3rd component of the z vector
     */
    public Matrix3(float x1, float x2, float x3,
                   float y1, float y2, float y3,
                   float z1, float z2, float z3) {
        m[0] = x1;  m[3] = y1;  m[6] = z1;
        m[1] = x2;  m[4] = y2;  m[7] = z2;
        m[2] = x3;  m[5] = y3;  m[8] = z3;
    }

    /**
     * Creates a Matrix4 out of 4 column vectors. Last row is assumed to be 0, except for m[15]
     *
     * @param x the x axis vector
     * @param y the y axis vector
     * @param z the z axis vector
     * @param e the translation
     */
    public Matrix3(Vector3 x, Vector3 y, Vector3 z) {
        m[0] = x.x();   m[3] = y.x();   m[6]  = z.x();
        m[1] = x.y();   m[4] = y.y();   m[7]  = z.y();
        m[2] = x.z();   m[5] = y.z();   m[8]  = z.z();
    }

    /**
     * Deep copy constructor
     *
     * @param other the matrix to copy
     */
    public Matrix3(Matrix3 other) {
        for (int i = 0; i < MATRIX_SIZE; i++)
            m[i] = other.m[i];
    }

    /**
     * Take the rotation portion of a Matrix4
     * @param mat4
     */
    public Matrix3(Matrix4 mat4) {
        set(0,0, mat4.M(0,0));  set(1,0, mat4.M(1,0));  set(2,0, mat4.M(2,0));
        set(0,1, mat4.M(0,1));  set(1,1, mat4.M(1,1));  set(2,1, mat4.M(2,1));
        set(0,2, mat4.M(0,2));  set(1,2, mat4.M(1,2));  set(2,2, mat4.M(2,2));
    }

    /**
     * Matrix times matrix multiplication. Returns a new matrix.
     * @param rhs the right hand side matrix
     * @return the resultant matrix
     */
    public Matrix3 multiply(Matrix3 rhs) {
        Matrix3 mat = new Matrix3();
        // Column 1
        mat.m[0] = m[0]*rhs.m[0] + m[3]*rhs.m[3] + m[6]*rhs.m[6];
        mat.m[1] = m[1]*rhs.m[0] + m[4]*rhs.m[3] + m[7]*rhs.m[6];
        mat.m[2] = m[2]*rhs.m[0] + m[5]*rhs.m[3] + m[8]*rhs.m[6];

        // Column 2
        mat.m[3] = m[0]*rhs.m[3] + m[3]*rhs.m[4] + m[6]*rhs.m[5];
        mat.m[4] = m[1]*rhs.m[3] + m[4]*rhs.m[4] + m[7]*rhs.m[5];
        mat.m[5] = m[2]*rhs.m[3] + m[5]*rhs.m[4] + m[8]*rhs.m[5];

        // Column 3
        mat.m[6] = m[0]*rhs.m[6] + m[3]*rhs.m[7] + m[6]*rhs.m[8];
        mat.m[7] = m[1]*rhs.m[6] + m[4]*rhs.m[7] + m[7]*rhs.m[8];
        mat.m[8] = m[2]*rhs.m[6] + m[5]*rhs.m[7] + m[8]*rhs.m[8];

        return mat;
    }

    /**
     * Matrix4 times vector4 multiplication
     * @param rhs the vector to transform
     * @return the resultant vector
     */
    public Vector3 multiply(Vector3 rhs) {
        return new Vector3(m[0]*rhs.x() + m[3]*rhs.y() + m[6]*rhs.z(),
                           m[1]*rhs.x() + m[4]*rhs.y() + m[7]*rhs.z(),
                           m[2]*rhs.x() + m[5]*rhs.y() + m[8]*rhs.z());
    }

    /**
     * Transpose of the matrix. Returns a new matrix.
     * @return the transpose
     */
    public Matrix3 transpose() {
        Matrix3 mat = new Matrix3();
        mat.m[0] = m[0];    mat.m[3] = m[1];    mat.m[6]  = m[2];
        mat.m[1] = m[3];    mat.m[4] = m[4];    mat.m[7]  = m[5];
        mat.m[2] = m[6];    mat.m[5] = m[7];    mat.m[8]  = m[8];
        return mat;
    }

    /**
     * Returns a new identity matrix
     */
    public static Matrix3 identity() {
        Matrix3 mat = new Matrix3();
        mat.m[0] = mat.m[4] = mat.m[8] = 1.0f;
        return mat;
    }

    /**
     * Creates and returns a new scale matrix
     * @param sx scale in the x dimension
     * @param sy scale in the x dimension
     * @param sz scale in the x dimension
     * @return the scale matrix
     */
    public static Matrix3 scale(float sx, float sy, float sz) {
        Matrix3 mat = new Matrix3();
        mat.m[0] = sx;
        mat.m[4] = sy;
        mat.m[8] = sz;
        return mat;
    }

    /**
     * Create a uniform scale matrix
     * @param s
     * @return
     */
    public static Matrix3 scale(float s) { return scale(s,s,s); }

    /**
     * Create a rotation matrix around the positive X axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix3 rotateX(float rad) {
        Matrix3 mat = Matrix3.identity();
        mat.set(1,1,(float) Math.cos(rad));     mat.set(2,1,(float) Math.sin(rad));
        mat.set(1,2,(float) -Math.sin(rad));    mat.set(2,2,(float) Math.cos(rad));
        return mat;
    }
    /**
     * Create a rotation matrix around the positive Y axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix3 rotateY(float rad) {
        Matrix3 mat = Matrix3.identity();
        mat.set(0,0,(float) Math.cos(rad));     mat.set(2,0,(float) -Math.sin(rad));
        mat.set(0,2,(float) Math.sin(rad));     mat.set(2,2,(float) Math.cos(rad));
        return mat;
    }
    /**
     * Create a rotation matrix around the positive Z axis
     * @param rad degree of rotation in radians
     * @return the rotation matrix
     */
    public static Matrix3 rotateZ(float rad) {
        Matrix3 mat = Matrix3.identity();
        mat.set(0,0,(float) Math.cos(rad));     mat.set(1,0,(float) Math.sin(rad));
        mat.set(0,1,(float) -Math.sin(rad));    mat.set(1,1,(float) Math.cos(rad));
        return mat;
    }

    /**
     * Creates a rotation matrix around the given axis
     * @param axis the axis to rotate around
     * @param rad the degree in radians to rotate
     * @return the rotation matrix
     */
    public static Matrix3 rotate(Vector3 axis, float rad) {
        Matrix3 mat = new Matrix3();
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        mat.m[0] = (float) (cos + axis.x()*axis.x()*(1-cos));
        mat.m[1] = (float) (axis.y()*axis.x()*(1-cos) + axis.z()*sin);
        mat.m[2] = (float) (axis.z()*axis.x()*(1-cos) - axis.y()*sin);

        mat.m[3] = (float) (axis.x()*axis.y()*(1-cos) - axis.z()*sin);
        mat.m[4] = (float) (cos + axis.y()*axis.y()*(1-cos));
        mat.m[5] = (float) (axis.z()*axis.y()*(1-cos) + axis.x()*sin);

        mat.m[6] = (float) (axis.x()*axis.z()*(1-cos) + axis.y()*sin);
        mat.m[7] = (float) (axis.y()*axis.z()*(1-cos) - axis.x()*sin);
        mat.m[8] = (float) (cos + axis.z()*axis.z()*(1-cos));
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
            throw new AssertionError("Invalid input into Matrix3.M(int, int)!");
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
     *
     * @param col the column index
     * @param row the row index
     * @param val value to set it to
     */
    public void set(int col, int row, float val) {
        if (BuildConfig.DEBUG && (col < 0 || col >= ROW_SIZE || row < 0  || row >= COL_SIZE)) {
            throw new AssertionError("Invalid input into Matrix3.set(int, int, float)!");
        }

        m[col*COL_SIZE + row] = val;
    }

    /**
     * Set a column in the matrix with a given vector
     * @param col the column number to set, 0 based
     * @param vec the vector to set the column to
     */
    public void set(int col, Vector3 vec) {
        if (BuildConfig.DEBUG && (col < 0 || col >= ROW_SIZE)) {
            throw new AssertionError("Invalid column input in Matrix3.set(int, Vector3)!");
        }

        int offset = col*ROW_SIZE;
        m[offset]   = vec.v[0];
        m[offset+1] = vec.v[1];
        m[offset+2] = vec.v[2];
    }

    /**
     * Equality check
     * @param rhs the object to test equality against
     * @return true if all entries in the two matrices are equal
     */
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Matrix3)) return false;

        Matrix3 m2 = (Matrix3) rhs;
        for (int i = 0; i < MATRIX_SIZE; i++)
            if (m[i] != m2.m[i])
                return false;

        return true;
    }
}

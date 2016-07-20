package matt.noobgraphics.math;

/**
 * A column-major 3x3 matrix representation
 * Created by Matth on 7/14/2016.
 */
public class Matrix3 {
    public final static int ROW_SIZE = 3;
    public final static int COL_SIZE = 3;
    public final static int MATRIX_SIZE = 9;

    public final float[] m = new float[9];

    public Matrix3() {}

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
        if (col < 0 || row < 0 || col > COL_SIZE || row > ROW_SIZE) return 0.0f;
        return m[row*ROW_SIZE + col];
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
    public boolean set(int col, int row, float val) {
        if (col < 0 || row < 0 || col >= COL_SIZE || row >= ROW_SIZE) return false;

        m[row*ROW_SIZE+col] = val;
        return true;
    }
}

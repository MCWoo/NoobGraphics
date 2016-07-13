package matt.noobgraphics.math;

/**
 * Column-major matrix
 * [ 0  4  8 12 ]
 * [ 1  5  9 13 ]
 * [ 2  6 10 14 ]
 * [ 3  7 11 15 ]
 * Created by Matth on 7/13/2016.
 */
public class Matrix4 {
    public final static int ROW_SIZE = 4;
    public final static int COL_SIZE = 4;

    public float[] m = new float[16];

    /**
     * Matrix times matrix multiplication. Returns a new matrix.
     * @param rhs the right hand side matrix
     * @return the resultant matrix
     */
    public Matrix4 multiply(Matrix4 rhs) {
        return new Matrix4();
    }

    /**
     * Matrix4 times vector4 multiplication
     * @param rhs the vector to transform
     * @return the resultant vector
     */
    public Vector4 multiply(Vector4 rhs) {
        return new Vector4();
    }

    /**
     * Transpose of the matrix. Returns a new matrix.
     * @return the transpose
     */
    public Matrix4 transpose() {
        return new Matrix4();
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
}

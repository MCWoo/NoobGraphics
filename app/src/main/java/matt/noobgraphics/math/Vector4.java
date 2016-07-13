package matt.noobgraphics.math;

/**
 * A 4D vector
 * Created by Matth on 7/13/2016.
 */
public class Vector4 {
    private float[] v = new float[4];
    public static int SIZE = 4;

    /**
     * Default constructor. Sets everything to 0
     */
    public Vector4() {
        v[0] = v[1] = v[2] = v[3] = 0.0f;
    }

    /**
     * Floating point constructor
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public Vector4(float x, float y, float z, float w) {
        v[0] = x;
        v[1] = y;
        v[2] = z;
        v[3] = w;
    }

    /**
     * Constructor. Deep copy
     * @param other the values to copy
     */
    public Vector4(float[] other) {
        if (other.length != SIZE)
            v[0] = v[1] = v[2] = v[3] = 0.0f;
        else {
            v[0] = other[0];
            v[1] = other[1];
            v[2] = other[2];
            v[3] = other[3];
        }
    }

    /**
     * Deep copy constructor
     * @param other the Vector4 to copy
     */
    public Vector4(Vector4 other) {
        v[0] = other.v[0];
        v[1] = other.v[1];
        v[2] = other.v[2];
        v[3] = other.v[3];
    }

    /**
     * Make a Vector4 out of a Vector3
     * @param other Vector3
     * @param w W coordinate
     */
    public Vector4(Vector3 other, float w) {
        v[0] = other.V(0);
        v[0] = other.V(1);
        v[0] = other.V(2);
        v[3] = w;
    }

    /**
     * Dot product
     * @param rhs the vector to calculate the dot product with
     * @return the dot product
     */
    public float dot(Vector4 rhs) {
        return v[0]*rhs.v[0] + v[1]*rhs.v[1] + v[2]*rhs.v[2] + v[3]*rhs.v[3];
    }

    /**
     * Normalize, i.e. make length = 1
     */
    public void normalize() {
        float length = length();
        if (length != 0) {
            v[0] /= length;
            v[1] /= length;
            v[2] /= length;
            v[3] /= length;
        }
    }

    /**
     * Dehomogenize, i.e. make w coordinate = 1
     */
    public void dehomogenize() {
        if (v[3] != 0) {
            v[0] /= v[3];
            v[1] /= v[3];
            v[2] /= v[3];
            v[3] = 1.0f;
        }
    }

    /**
     * Scale the vector
     * @param sx scale in the x dimension
     * @param sy scale in the y dimension
     * @param sz scale in the z dimension
     */
    public void scale(float sx, float sy, float sz, float sw) {
        v[0] *= sx;
        v[1] *= sy;
        v[2] *= sz;
        v[3] *= sw;
    }

    /**
     * Uniform scale
     */
    public void scale(float s) { scale(s,s,s,s); }

    /**
     * @return the magnitude of the vector
     */
    public float length() {
        return (float) Math.sqrt(this.dot(this));
    }

    /**
     * @return the magnitude of the vector
     */
    public float magnitude() {
        return length();
    }

    /**
     * @return the number of components in the vector, i.e. the dimension
     */
    public int size() { return SIZE; }

    /**
     * Accessor
     * @param i index into vector
     * @return the vector component
     */
    public float V(int i) {
        if (i < 0 || i > SIZE)
            return 0;
        return v[i];
    }

    /**
     * Setter
     * @param i index into the vector
     * @param val value to set to
     * @return true if succeeds
     */
    public boolean setV(int i, float val) {
        if (i < 0 || i > SIZE)
            return false;
        v[i] = val;
        return true;
    }

    // For readability's sake
    public float x() { return v[0]; }
    public float y() { return v[1]; }
    public float z() { return v[2]; }
    public float w() { return v[3]; }
}

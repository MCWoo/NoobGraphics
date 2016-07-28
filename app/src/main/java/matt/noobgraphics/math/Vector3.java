package matt.noobgraphics.math;

/**
 * 3D Vector class
 * Created by Matth on 7/13/2016.
 */
public class Vector3 {
    public final static int SIZE = 3;

    public final float[] v = new float[3];

    /**
     * Default constructor
     */
    public Vector3() {
        v[0] = v[1] = v[2] = 0.0f;
    }

    public Vector3(float x, float y, float z) {
        v[0] = x;
        v[1] = y;
        v[2] = z;
    }

    /**
     * Addition. Returns a new Vector3
     * @param rhs the right hand vector to add
     * @return resultant vector
     */
    public Vector3 plus(Vector3 rhs) {
        return new Vector3(v[0]+rhs.v[0], v[1]+rhs.v[1], v[2]+rhs.v[2]);
    }

    /**
     * Subtraction. Returns a new Vector3
     * @param rhs the right hand vector to subtract
     * @return resultant vector
     */
    public Vector3 minus(Vector3 rhs) {
        return new Vector3(v[0]-rhs.v[0], v[1]-rhs.v[1], v[2]-rhs.v[2]);
    }

    /**
     * Component-wise multiplication. Returns a new Vector3
     * @param rhs the right hand vector to multiply by
     * @return resultant vector
     */
    public Vector3 times(Vector3 rhs) {
        return new Vector3(v[0]*rhs.v[0], v[1]*rhs.v[1], v[2]*rhs.v[2]);
    }

    /**
     * Component-wise division. Returns a new Vector3
     * @param rhs the right hand vector to divide by
     * @return resultant vector
     */
    public Vector3 divideBy(Vector3 rhs) {
        return new Vector3(v[0]/rhs.v[0], v[1]/rhs.v[1], v[2]/rhs.v[2]);
    }

    /**
     * Constructor
     * @param other the values to copy
     */
    public Vector3(float[] other) {
        if (other.length != SIZE)
            v[0] = v[1] = v[2] = 0.0f;
        else {
            v[0] = other[0];
            v[1] = other[1];
            v[2] = other[2];
        }
    }

    /**
     * Copy constructor
     * @param other the Vector4 to copy
     */
    public Vector3(Vector3 other) {
        v[0] = other.v[0];
        v[1] = other.v[1];
        v[2] = other.v[2];
    }

    /**
     * Dot product
     * @param rhs the vector to calculate the dot product with
     * @return the dot product
     */
    public float dot(Vector3 rhs) {
        return v[0]*rhs.v[0] + v[1]*rhs.v[1] + v[2]*rhs.v[2];
    }

    /**
     * Cross product
     */
    public Vector3 cross(Vector3 rhs) {
        return new Vector3(v[1]*rhs.V(2) - v[2]*rhs.V(1),
                           v[2]*rhs.V(0) - v[0]*rhs.V(2),
                           v[0]*rhs.V(1) - v[1]*rhs.V(0));
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
        }
    }

    /**
     * In place negation
     */
    public void negate() {
        scale(-1);
    }

    /**
     * Scale the vector
     * @param sx scale in the x dimension
     * @param sy scale in the y dimension
     * @param sz scale in the z dimension
     */
    public void scale(float sx, float sy, float sz) {
        v[0] *= sx;
        v[1] *= sy;
        v[2] *= sz;
    }

    /**
     * Uniform scale
     */
    public void scale(float s) { scale(s,s,s); }

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
     * Access the components array
     * @return array of components
     */
    public float[] V() { return v; }

    /**
     * Setter
     * @param i index into the vector
     * @param val value to set to
     * @return true if succeeds
     */
    public boolean set(int i, float val) {
        if (i < 0 || i > SIZE)
            return false;
        v[i] = val;
        return true;
    }

    public float x() { return v[0]; }
    public float y() { return v[1]; }
    public float z() { return v[2]; }

    public boolean equals(Vector3 other) {
        return (v[0] == other.v[0]) && (v[1] == other.v[1]) && (v[2] == other.v[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector3)) return false;

        Vector3 rhs = (Vector3) o;
        for (int i = 0; i < SIZE; i++)
            if (v[i] != rhs.v[i]) return false;

        return true;
    }
}

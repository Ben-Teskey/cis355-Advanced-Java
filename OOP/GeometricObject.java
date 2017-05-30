/**
 * The following class is used to provide implementation for variables and methods for any geometric object class
 * that is a subclass of this one, as well as provide abstract methods for those subclasses to implement on
 * their own.
 */
public abstract class GeometricObject implements Comparable<GeometricObject> {

    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /** Construct a default geometric object */
    protected GeometricObject() {

        dateCreated = new java.util.Date();

    }

    /**
     * Construct a geometric object with color and filled value
     *
     * @param color string for color of object
     * @param filled boolean for whether object is to be filled
     */
    protected GeometricObject(String color, boolean filled) {

        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;

    }

    /** Return color */
    public String getColor() {

        return color;

    }

    /** Set a new color */
    public void setColor(String color) {

        this.color = color;

    }
    /** Return filled. Since filled is boolean,
     * the get method is named isFilled */
    public boolean isFilled() {

        return filled;

    }

    public void setFilled(boolean filled) {

        this.filled = filled;

    }

    /** Get dateCreated */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Override toString method in Object class
     *
     * @return a string representing the object
     */
    @Override
    public String toString() {

        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;

    }

    /**
     * Implementation of compareTo specified in comparable interface
     *
     * @param otherObject
     * @return an integer specifying whether area of current object is greater than, equal to, or less than
     * area of object compared to.
     */
    public int compareTo(GeometricObject otherObject) {

        if (getArea() > otherObject.getArea()) return 1;
        else if (getArea() == otherObject.getArea()) return 0;
        else return -1;

    }

    /** Abstract method getArea */
    public abstract double getArea();

    /** Abstract method getPerimeter */
    public abstract double getPerimeter();

}

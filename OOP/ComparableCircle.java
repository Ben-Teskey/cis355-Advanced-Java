/**
 * The following class is used to build a circle that can be compared, implementing the abstract methods
 * in the geometric object class and the comparable interface.
 */
public class ComparableCircle extends GeometricObject {

    private double radius;

    public ComparableCircle() {

    }

    /**
     * Arg constructor for circle
     *
     * @param radius radius of the circle as double
     */
    public ComparableCircle(double radius) {

        this.radius = radius;

    }

    /**
     * Arg constructor for circle
     *
     * @param radius radius of the circle as double
     * @param color color of the circle as String
     * @param filled whether the circle is filled as boolean
     */
    public ComparableCircle(double radius, String color, boolean filled) {

        this.radius = radius;
        setColor(color);
        setFilled(filled);

    }

    public double getRadius() {

        return radius;

    }

    public void setRadius(double radius) {

        this.radius = radius;

    }

    /**
     * Calculate the area of the circle and return it
     *
     * @return area as double
     */
    public double getArea() {

        return radius * radius * Math.PI;

    }

    public double getDiameter() {

        return 2 * radius;

    }

    /**
     * Calculate the perimeter of the circle and return it
     *
     * @return perimeter as double
     */
    public double getPerimeter() {

        return 2 * radius * Math.PI;

    }

    public void printCircle() {

        System.out.println("The circle is created " + getDateCreated() + " and the radius is " + radius);

    }

    /**
     * Override toString method to append area to geometric object toString method result
     */
    @Override
    public String toString() {

        return super.toString() + " area: " + getArea();

    }

    /**
     * Override the equals method. Comparison is if radii are the same
     */
    @Override
    public boolean equals(Object otherObject) {

        if (otherObject instanceof ComparableCircle) {

            if (((ComparableCircle)otherObject).getRadius() == getRadius()) return true;
            else return false;

        }
        else return false;

    }

}

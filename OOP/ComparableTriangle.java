/**
 * The following class is used to build a triangle that can be compared, implementing the abstract methods
 * in the geometric object class and the comparable interface.
 */
public class ComparableTriangle extends GeometricObject {

    private double side1, side2, side3;

    ComparableTriangle() {

        side1 = side2 = side3 = 1;

    }

    /**
     * Arg constructor to create a triangle with specified side lengths
     *
     * @param side1 double for first specified side
     * @param side2 double for second specified side
     * @param side3 double for third specified side
     */
    ComparableTriangle(double side1, double side2, double side3) {

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

    }

    public double getSide1() {

        return side1;

    }

    public double getSide2() {

        return side2;

    }

    public double getSide3() {

        return side3;

    }

    /**
     * Adds sides of the triangle to get the perimeter
     *
     * @return perimeter as a double
     */
    public double getPerimeter() {

        return side1 + side2 + side3;

    }

    /**
     * Calculates area of the triangle using formal equation and returns it
     *
     * @return area as a double
     */
    public double getArea() {

        double s = (side1 + side2 + side3) / 2;

        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));

    }

    /**
     * Override the toString method, appending area to parent toString result and returning it
     */
    @Override
    public String toString() {

        return super.toString() + " area: " + getArea();

    }

}

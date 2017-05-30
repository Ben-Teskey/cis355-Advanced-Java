/**
 * The following class is used to build a rectangle that can be compared, implementing the abstract methods
 * in the geometric object class and the comparable interface.
 */
public class ComparableRectangle extends GeometricObject {

    private double width, height;

    public ComparableRectangle() {

    }

    public ComparableRectangle(double width, double height) {

        this.width = width;
        this.height = height;

    }

    public ComparableRectangle(double width, double height, String color, boolean filled) {

        this.width = width;
        this.height = height;
        setColor(color);
        setFilled(filled);

    }

    public double getWidth() {

        return width;

    }

    public void setWidth(double width) {

        this.width = width;

    }

    public double getHeight() {

        return height;

    }

    public void setHeight(double height) {

        this.height = height;

    }

    /**
     * Calculate and return area
     *
     * @return area of the rectangle
     */
    public double getArea() {

        return width * height;

    }

    /**
     * Calculate and return perimeter
     *
     * @return perimeter of the rectangle
     */
    public double getPerimeter() {

        return 2 * (width + height);

    }

    /**
     * Override the equals method. Comparison is if areas are the same
     */
    @Override
    public boolean equals(Object otherObject) {

        if (otherObject instanceof ComparableRectangle) {

            if (((ComparableRectangle)otherObject).getArea() == getArea()) return true;
            else return false;

        }
        else return false;

    }

    /**
     * Override toString method to append area to geometric object toString method result
     */
    @Override
    public String toString() {

        return super.toString() + " area: " + getArea();

    }

}

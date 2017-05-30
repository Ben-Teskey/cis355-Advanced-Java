// Homework 3: 2-D Shapes
// Student Name: Benjamin Teskey
// Course: CIS357, Winter 2017
// Instructor: Dr. Cho
// Date finished: 3/2/2017
// Program description: This program tests the core functionality of Geometric Object shape class
// and all of its subclasses, including implementations of the comparable interface, the geometric
// object abstract methods, and the equals method.

import java.util.ArrayList;
import java.util.Arrays;


public class TwoDShapesTeskey_HW3 {

    public static void main(String[] args) {

        ArrayList<GeometricObject> myShapes = new ArrayList<GeometricObject>();

        // Add triangles
        myShapes.add(new ComparableTriangle(5, 6, 5));
        myShapes.add(new ComparableTriangle(7, 6, 4));
        myShapes.add(new ComparableTriangle(5, 5, 5));

        // Add circles
        myShapes.add(new ComparableCircle(2));
        myShapes.add(new ComparableCircle(3));
        myShapes.add(new ComparableCircle(3.5));

        // Add rectangles
        myShapes.add(new ComparableRectangle(4, 5));
        myShapes.add(new ComparableRectangle(3, 5));
        myShapes.add(new ComparableRectangle(6, 3));

        for (int i = 0; i < myShapes.size(); i++) System.out.println(myShapes.get(i));

        GeometricObject[] myShapesArr = new GeometricObject[myShapes.size()];
        myShapesArr = myShapes.toArray(myShapesArr);

        Arrays.sort(myShapesArr);

        myShapes.clear();
        myShapes = new ArrayList<GeometricObject>(Arrays.asList(myShapesArr));

        System.out.println();

        for (int i = 0; i < myShapes.size(); i++) {

            System.out.println(myShapes.get(i));

        }

    }

}

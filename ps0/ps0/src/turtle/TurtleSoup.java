/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double result = (sides - 2) * 180.0 / sides;
        return result;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        int result = (int) Math.round(360 / (180 - angle));
        return result;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle = calculateRegularPolygonAngle(sides);
        for (int i = 0; i < sides; i++) {
            turtle.turn(180 - angle);
            turtle.forward(sideLength); 
            
        }
        
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        // We actually don't care about the current point because the default angle is always
        // upward. What we really want is the
        // angle between target point and the upward direction. So, we
        // need to make two vectors to calculate the angle between them.
        double vector1X = 0;
        double vector1Y = currentY;
        double vector2X = targetX - currentX;
        double vector2Y = targetY - currentY;
        double angle = 0;
        // the origin should be treated as a upward direction
        if (vector1X == 0 && vector1Y == 0) {
            vector1X = 0;
            vector1Y = 1;
        } else if (vector2X == 0 && vector2Y == 0) {
            vector2X = 0;
            vector2Y = 1;
        }
        
        // convert the atan2 angle to the clockwise angle in order to match the currentHeading
        angle  = Math.atan2( vector2Y, vector2X) / Math.PI * 180;
        if (angle < 0) {
            angle = -angle + 90;
        } else if (angle <= 90) {
            angle = 90 - angle;
        } else if (angle > 90) {
            angle = 360 - (angle - 90);
        }
        
//        System.out.println("vector1x: " + vector1X);
//        System.out.println("vector1Y: " + vector1Y);
//        System.out.println("vector2x: " + vector2X);
//        System.out.println("vector2Y: " + vector2Y);
//        System.out.println(angle);
        double result = angle - currentHeading;
        return result < 0? 360 + result : result;
//        throw new RuntimeException("implement me!");
    }
    

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> result = new ArrayList<Double>();
        double direction = 0;
        for (int i = 1; i < xCoords.size(); i++) {
            direction =  direction % 360;
            double heading = calculateHeadingToPoint(direction, xCoords.get(i - 1), yCoords.get(i - 1),
                    xCoords.get(i), yCoords.get(i));
//            System.out.println("heading: " + heading);
//            System.out.println("direction: " + direction);
            direction += heading;
            result.add(heading);
        }
        return result;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        for (int i = 0; i < 100; i++) {
            turtle.forward(100);
            turtle.turn(175);
            turtle.forward(300);
        }
//        throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawPersonalArt(turtle);
//        drawRegularPolygon(turtle, 5, 100);
//
//        drawSquare(turtle, 40);

        // draw the window
        turtle.draw();
    }

}

package ru.stqa.figures.triangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void getTrianglePerimeter(){
        Triangle triangle = new Triangle(2.0, 2.0, 3.0);
        Assertions.assertEquals(7.0, triangle.perimeter());
    }

    @Test
    void  getTriangleArea(){
        Triangle triangle = new Triangle(23.0, 24.0, 12.0);
        Assertions.assertEquals(135.85263155345942, triangle.area());
    }

    @Test
    void cannotCreateTriangleWithNegativeASide() {
        try {
            new Triangle(-2.0, 5.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        //OK
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeBSide() {
        try {
            new Triangle(2.0, - 5.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeCSide() {
        try {
            new Triangle(2.0, 5.0, - 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleWithSumOfSidesAAndBLessThenC() {
        try {
            new Triangle(1.0, 3.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleWithSumOfSidesAAndCLessThenB() {
        try {
            new Triangle(1.0, 5.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleWithSumOfSidesBAndCLessThenA() {
        try {
            new Triangle(10.0, 5.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
}


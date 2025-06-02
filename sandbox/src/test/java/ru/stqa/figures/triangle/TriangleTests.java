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
        Assertions.assertEquals(135.85263155345942, triangle.area(triangle));
    }
}

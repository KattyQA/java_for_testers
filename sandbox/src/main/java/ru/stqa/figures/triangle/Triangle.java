package ru.stqa.figures.triangle;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter(){
        return this.a + this.b + this.c;
    }

    public double area(Triangle triangle){
        double p2 = (triangle.perimeter())/2;
        return Math.sqrt(p2*(p2 - this.a)*(p2 - this.b)*(p2 - this.c));
    }


}

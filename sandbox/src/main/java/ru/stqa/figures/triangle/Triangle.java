package ru.stqa.figures.triangle;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;

        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (((a + b) < c) ||((a + c) < b) || ((b + c) < a)) {
            throw  new IllegalArgumentException("Triangle inequality is violated");
        }
    }

    public double perimeter(){
        return this.a + this.b + this.c;
    }

    public double area(){
        double p2 = (this.perimeter())/2;
        return Math.sqrt(p2*(p2 - this.a)*(p2 - this.b)*(p2 - this.c));
    }


}

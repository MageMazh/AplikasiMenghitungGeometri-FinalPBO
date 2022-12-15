public class Segitiga implements BangunDatar{
    protected double a; //sisi 1
    protected double b; //sisi 2
    protected double c; //sisi 3

    Segitiga(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double keliling() {
        return a+b+c;
    }
    
    public double luas() {
        return 0.5*a*b;
    }
}

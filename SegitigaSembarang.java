public class SegitigaSembarang extends Segitiga{
    SegitigaSembarang(double a, double b, double c) {
        super(a, b, c);
    }

    public double luas() {
        return Math.sqrt(getS()*(getS()*a)*(getS()*b)*(getS()*c));
    }

    public double getS() {
        return 0.5*(a+b+c);
    }

}

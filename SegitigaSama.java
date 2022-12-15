public class SegitigaSama extends Segitiga {

    SegitigaSama(double a, double b, double c) {
        super(a, b, c);
    }
    
    public double luas() {
        return 0.5* b * getHeight();
    }

    public double getHeight() {
        double temp = 0.5*b;
        return Math.sqrt((c*c)-(temp*temp));
    }
}

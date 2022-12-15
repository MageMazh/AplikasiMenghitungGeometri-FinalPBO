abstract class BangunRuang {
    private double p;
    private double l;
    private double t;

    public abstract double keliling();
    public abstract double luas();
    public abstract double volume();
    public BangunRuang(double panjang, double lebar, double tinggi) {
        this.p = panjang;
        this.l = lebar;
        this.t = tinggi;
    }
    public double getP() {
        return this.p;
    }
    public double getL() {
        return this.l;
    }
    public double getT() {
        return this.t;
    }
}

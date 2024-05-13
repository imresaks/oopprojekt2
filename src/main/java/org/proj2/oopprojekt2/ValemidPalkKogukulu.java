package org.proj2.oopprojekt2;
public class ValemidPalkKogukulu {
    int deltaQ;
    int K1;
    int K2;
    int L1;
    int L2;
    int r;

    public ValemidPalkKogukulu(int deltaQ, int k1, int k2, int l1, int l2, int r) {
        this.deltaQ = deltaQ;
        this.K1 = k1;
        this.K2 = k2;
        this.L1 = l1;
        this.L2 = l2;
        this.r = r;
    }

    public int getDeltaQ() {
        return deltaQ;
    }

    public void setDeltaQ(int deltaQ) {
        this.deltaQ = deltaQ;
    }

    public int getK1() {
        return K1;
    }

    public void setK1(int k1) {
        K1 = k1;
    }

    public int getK2() {
        return K2;
    }

    public void setK2(int k2) {
        K2 = k2;
    }

    public int getL1() {
        return L1;
    }

    public void setL1(int l1) {
        L1 = l1;
    }

    public int getL2() {
        return L2;
    }

    public void setL2(int l2) {
        L2 = l2;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double piirkuluKapital(){
        return (double) this.deltaQ / (this.K2 - this.K1);
    }
    public double piirkuluTööjõud(){
        return (double) this.deltaQ / (this.L2 - this.L1);
    }
    public double palk(){
        return this.r * piirkuluTööjõud() / piirkuluKapital();
    }
    public double kogukulu(){
        return palk() * this.L2 + this.r * this.K2;
    }


    @Override
    public String toString() {
        return "Vajalikud andmed{" +
                "deltaQ=" + deltaQ +
                ", K1=" + K1 +
                ", K2=" + K2 +
                ", L1=" + L1 +
                ", L2=" + L2 +
                ", r=" + r +
                '}';
    }
}

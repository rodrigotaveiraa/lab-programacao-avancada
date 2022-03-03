import java.io.*;

class Vetor implements Serializable {
    private int[] x, y;
    private int produtoEscalar;

    Vetor (int[] a, int[] b) {
        this.x = a;
        this.y = b;
        this.produtoEscalar = 0;
    }
    int getX(int i) {return x[i];}

    int getY(int i) {return y[i];}

    void setProduto(int i) {
        this.produtoEscalar = i;
    }

    int getProduto() {
        return this.produtoEscalar;
    }
}
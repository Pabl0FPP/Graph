package model;

public class Arista<V> {
    private Vertice<V> origen;
    private Vertice<V> destino;


    public Arista(Vertice<V> origen, Vertice<V> destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Vertice<V> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice<V> origen) {
        this.origen = origen;
    }

    public Vertice<V> getDestino() {
        return destino;
    }

    public void setDestino(Vertice<V> destino) {
        this.destino = destino;
    }

    public Vertice<V> getVecino(Vertice<V> vertice){
        if (vertice == origen) {
            return destino;
        } else if (vertice == destino) {
            return origen;
        } else {
            throw new IllegalArgumentException("La arista no esta conectada al vertice que se proporcionó");
        }
    }
}

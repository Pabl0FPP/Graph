package model;

import java.util.ArrayList;

public class Vertice<V> {
    private V value;
    private ArrayList<Vertice<V>> adyacentes;
    private ArrayList<Arista<V>> aristas;
    private int color;
    private Vertice<V> previous;
    private int time;

    public Vertice(V value, ArrayList<Vertice<V>> adyacentes) {
        this.aristas=new ArrayList<>();
        this.value = value;
        this.adyacentes = adyacentes;
        color = 0;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public ArrayList<Vertice<V>> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(ArrayList<Vertice<V>> adyacentes) {
        this.adyacentes = adyacentes;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Vertice<V> getPrevious() {
        return previous;
    }

    public void setPrevious(Vertice<V> previous) {
        this.previous = previous;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addArista(Arista<V> arista){
        aristas.add(arista);
    }

    public ArrayList<Arista<V>> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<Arista<V>> aristas) {
        this.aristas = aristas;
    }

    public void deleteAristas() {
        for (Arista<V> arista: aristas) {
            Vertice<V> vertice = statusArista(this, arista);
            if (vertice != null) {
                vertice.getAristas().remove(arista);
            }
        }
    }

    private Vertice<V> statusArista(Vertice<V> verticeCurrent, Arista<V> arista) {
        if (verticeCurrent != arista.getOrigen()) {
            return arista.getOrigen();
        } else if (verticeCurrent != arista.getDestino()) {
            return arista.getDestino();
        } else {
            return null;
        }
    }



}

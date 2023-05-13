package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GrafoListaAdyacencia<V> implements IGrafo<V>{
    private ArrayList<Vertice<V>> vertices;
    private ArrayList<Arista<V>> aristas;
    private boolean grafoDirecto;
    int time;

    public GrafoListaAdyacencia(boolean grafoDirecto) {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.grafoDirecto = grafoDirecto;
        this.time = time;
    }


    public ArrayList<Vertice<V>> getVertices() {
        return vertices;
    }

    public ArrayList<Arista<V>> getAristas(){
        return aristas;
    }
    @Override
    public void addVertice(V element) {

        if (searchVertice(element) != null){
            return;
        }
        vertices.add(new Vertice<>(element, new ArrayList<>()));
    }

    @Override
    public boolean deleteVertice(V vertice) {
        Vertice<V> verticeee= searchVertice(vertice);
        boolean delete= false;
        if (verticeee != null){
            vertices.remove(verticeee);
            verticeee.deleteAristas();
            delete=true;
        }
        return delete;
    }

    @Override
    public void addArista(V elementA, V elementB) {

        Vertice<V> verticeOrigen = searchVertice(elementA);
        Vertice<V> verticeDestino = searchVertice(elementB);

        if (!grafoDirecto) {
            if (elementA != elementB) {
                Arista<V> arista = new Arista<>(verticeOrigen, verticeDestino);
                if (!aristas.contains(arista)) {
                    verticeOrigen.addArista(arista);
                    verticeDestino.addArista(arista);
                    aristas.add(arista);
                }
            }
        }  else if (grafoDirecto) {
            Arista<V> arista = new Arista<>(verticeOrigen, verticeDestino);
            if (!aristas.contains(arista)) {
                verticeOrigen.addArista(arista);
                aristas.add(arista);
                //getAdyacentes().add(verticeDestino);
            }
        }
    }


    @Override
    public boolean deleteArista(V elementA, V elementB){
        Vertice<V> verticeOrigen = searchVertice(elementA);
        Vertice<V> verticeDestino = searchVertice(elementB);
        boolean delete = false;
        if (verticeOrigen != null && verticeDestino != null) {
            Arista<V> AristaToDelete = null;
            for (Arista<V> arista : aristas) {
                if (arista.getOrigen() == verticeOrigen && arista.getDestino() == verticeDestino) {
                    AristaToDelete = arista;
                    break;
                }
            }
            if (AristaToDelete != null) {
                aristas.remove(AristaToDelete);
                verticeOrigen.getAristas().remove(AristaToDelete);
                verticeDestino.getAristas().remove(AristaToDelete);
                deleteVertice(elementB);
                delete = true;
            }
        }
        return delete;

    }

    @Override
    public boolean BFS(V element) {
        Vertice<V> vertice = searchVertice(element);
        for (Vertice<V> VAdyacente: vertices) {
            VAdyacente.setColor(0);
            VAdyacente.setTime(Integer.MAX_VALUE);
            VAdyacente.setPrevious(null);
        }
        vertice.setColor(1);
        vertice.setTime(0);
        vertice.setPrevious(null);

        Queue<Vertice<V>> queue = new LinkedList<>();

        queue.add(vertice);
        while (!queue.isEmpty()){
            Vertice<V> u = queue.poll();

            //Recorrer los vertices adyacentes
            for (Arista<V> arista : aristas) {
                Vertice<V> adyacente = arista.getDestino();
                if(adyacente.getColor() == 0) {
                    adyacente.setColor(1);
                    adyacente.setTime(adyacente.getTime()+1);
                    adyacente.setPrevious(u);
                    queue.add(adyacente);
                }
            }
            u.setColor(2);
        }
        for (Vertice<V> g: vertices) {
            if(g.getColor() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int DFS(){
        for (Vertice<V> vertice: vertices) {
            vertice.setPrevious(null);
            vertice.setColor(0);
            vertice.setTime(0);
        }
        int trees = 0;
        int time = 0;
        for (Vertice<V> vertice: vertices) {
            if(vertice.getColor() == 0) {
                trees++;
                DFSVisit(vertice);
            }
        }
        return trees;
    }

    public void DFSVisit(Vertice<V> vertice){
        time++;
        vertice.setTime(time);
        vertice.setColor(1);
        for (Arista<V> arista : aristas) {
            Vertice<V> adyacente = arista.getDestino();
            if(adyacente.getColor() == 0) {
                adyacente.setPrevious(vertice);
                DFSVisit(adyacente);
            }
        }
        vertice.setColor(2);
        time++;
        vertice.setTime(time);
    }

    public Vertice<V> searchVertice(V element) {
        for (Vertice<V> g: vertices) {
            if(g.getValue().equals(element)){
                return g;
            }
        }
        return null;
    }
}

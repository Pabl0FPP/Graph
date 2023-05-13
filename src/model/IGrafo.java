package model;

public interface IGrafo<V> {

        public void addVertice(V element);
        public void addArista(V elementA, V elementB);
        public int DFS();
        public boolean BFS(V element);
        public boolean deleteVertice(V element);
        public boolean deleteArista(V origen, V destino);
}

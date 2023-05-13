package model;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.*;

public class GrafoListaAdyacenciaTest {

    private GrafoListaAdyacencia<Integer> grafo;

    public void setupStage1() {
        grafo = new GrafoListaAdyacencia<>(false);
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addArista(1, 2);
        grafo.addArista(2, 3);
    }

    public void setupStage2() {
        grafo = new GrafoListaAdyacencia<>(true);
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addArista(1, 2);
        grafo.addArista(2, 3);
    }

    @Test
    public void addVerticeTest() {
        setupStage1();
        ArrayList<Vertice<Integer>> adyacentes = grafo.getVertices();
        Assert.assertEquals(3, adyacentes.size());
        Assert.assertEquals(Integer.valueOf(1), adyacentes.get(0).getValue());
        Assert.assertEquals(Integer.valueOf(2), adyacentes.get(1).getValue());
        Assert.assertEquals(Integer.valueOf(3), adyacentes.get(2).getValue());
    }

    @Test
    public void addExisistingVerticeTest(){
        setupStage1();
        int sizeBefore = grafo.getVertices().size();
        grafo.addVertice(2);
        int sizeAfter = grafo.getVertices().size();
        assertEquals(sizeBefore, sizeAfter);
    }

    //OTRO
    //TEST
    //AQUI

    @Test
    public void DeleteVerticeTest() {
        setupStage1();
        Assert.assertTrue(grafo.deleteVertice(2));
        ArrayList<Vertice<Integer>> adyacentes = grafo.getVertices();
        Assert.assertEquals(2, adyacentes.size());
        Assert.assertEquals(Integer.valueOf(1), adyacentes.get(0).getValue());
        Assert.assertEquals(Integer.valueOf(3), adyacentes.get(1).getValue());
    }
    @Test
    public void DeleteVerticeNullTest(){
        setupStage1();
        Assert.assertTrue(grafo.deleteVertice(1));
        Assert.assertTrue(grafo.deleteVertice(2));
        Assert.assertTrue(grafo.deleteVertice(3));
        assertNull(grafo.searchVertice(3));
    }

    @Test
    public void verifyThatVerticesAreDeletedTest(){
        setupStage1();
        Assert.assertTrue(grafo.deleteVertice(1));
        assertEquals(2 , grafo.getVertices().size());
    }

    @Test
    public void AddAristaTest() {
        setupStage1();
        ArrayList<Arista<Integer>> aristas = grafo.getAristas();
        Assert.assertEquals(2, aristas.size());
        assertEquals(2 , grafo.getVertices().get(1).getAristas().size());
        assertNotNull(grafo.getVertices().get(1).getAristas().get(0));
        assertEquals(1 , grafo.getVertices().get(2).getAristas().size());
    }

    @Test
    void AddAristaGrafoDirectoTest() {
        setupStage2();
        assertEquals(1 , grafo.getVertices().get(1).getAristas().size());
        assertNotNull(grafo.getVertices().get(1).getAristas().get(0));
        assertEquals(0 , grafo.getVertices().get(2).getAristas().size());
    }

    //OTRO
    //TEST
    //AQUI

    @Test
    public void testDeleteAristaTest() {
        setupStage1();
        Assert.assertTrue(grafo.deleteArista(2, 3));
        ArrayList<Arista<Integer>> aristas = grafo.getAristas();
        Assert.assertEquals(1, aristas.size());
        Assert.assertEquals(Integer.valueOf(1), aristas.get(0).getOrigen().getValue());
        Assert.assertEquals(Integer.valueOf(2), aristas.get(0).getDestino().getValue());
    }

    @Test
    public void DeleteAristaGrafoDirectoTest() {
        setupStage2();
        assertEquals(1, grafo.getVertices().get(1).getAristas().size());
        assertNotNull(grafo.getVertices().get(1).getAristas().get(0));
        assertEquals(0, grafo.getVertices().get(2).getAristas().size());

        assertTrue(grafo.deleteArista(1, 2));

        assertEquals(0, grafo.getVertices().get(0).getAristas().size());
        assertEquals(0, grafo.getVertices().get(1).getAristas().size());
    }

    @Test
    public void verifyThatAristasAreDeletedTest(){
        setupStage1();
        Assert.assertTrue(grafo.deleteArista(1, 2));
        assertEquals(1 , grafo.getAristas().size());
    }



    @Test
    public void testBFS() {
        setupStage1();
        Assert.assertTrue(grafo.BFS(1));
        Assert.assertFalse(grafo.BFS(3));
    }

    @Test
    public void testBFSGDisconnected() {
        setupStage2();
        grafo.addVertice(6);
        Assert.assertFalse(grafo.BFS(6));
    }

    @Test
    public void testDFS() {
        setupStage1();
        Assert.assertEquals(1, grafo.DFS());
        grafo.addVertice(4);
        grafo.addArista(4, 1);
        Assert.assertEquals(2, grafo.DFS());
    }

    @Test
    public void testDFSGMultipleTrees() {
        setupStage2();
        grafo.addVertice(9);
        grafo.addVertice(8);
        grafo.addArista(8,9);
        Assert.assertEquals(2, grafo.DFS());
    }

}

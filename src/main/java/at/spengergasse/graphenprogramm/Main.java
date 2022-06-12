package at.spengergasse.graphenprogramm;

import at.spengergasse.graphenprogramm.model.Graph;
import at.spengergasse.graphenprogramm.model.Matrix;
import at.spengergasse.graphenprogramm.viewmodel.GraphViewModel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Xzep {
        testAll();
        //testImport();
        //testOne();
        //System.out.println(1*0 + 23);
        //testRemoveKnoten();
    }

    public static void testAll() throws Xzep {
        Graph graph = new Graph("graph3.csv");
        graph.calculate();
        System.out.println(graph.exzentrizitaetenToString());
        System.out.println(graph.toString());



        for (int i = 0; i < graph.getAnzKnoten(); i++) {
            Matrix temp = new Matrix(graph.getAdjMatrix().removeKnoten(i, graph.getAdjMatrix().getMatrix()));
            Graph gTemp = new Graph(temp);
            gTemp.calculate();
             if(gTemp.getAnzKomponenten() > graph.getAnzKomponenten()){

             }
            //System.out.println(gTemp.exzentrizitaetenToString());
            //System.out.println(gTemp);

        }
    }

    public static void testOne () {
        /*String erg = "0;0;0;1;1;0;0;1";
        String [] data = erg.split(";");
        System.out.println(data[0]);*/
            int[][] array = new int[1][1];
            System.out.println(array[0][0]);
    }

    public static void testImport() throws Xzep {
        Graph graph = new Graph();
        graph.populateKnotenNamen();
        graph.importCsv("graph3.csv");
        graph.calculate();

        System.out.println(graph.toString());



        /*for(int i = 0; i < graph.getAnzKnoten(); i++){
            Matrix temp = new Matrix(graph.getAdjMatrix().removeKnoten(i,graph.getAdjMatrix().getMatrix()));
            Graph gTemp = new Graph(temp);
            //gTemp.calculate();
           // if(gTemp.getAnzKomponenten() > graph.getAnzKomponenten()){

           // }
            //System.out.println(gTemp);
            //System.out.println("----------------------------------------------");
        }*/

        /*graph.calculatePotenzMatrizen();
        graph.calculateDistanzMatrix();
        graph.calculateWegMatrix();
        graph.setExzentrizitaeten();
        graph.findDurchmesser();
        graph.findRadius();
        graph.setAnzKomponenten();*/


    }

    public static void testRemoveKnoten () {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getLength(); i++) {
            for (int j = 0; j < m.getLength(); j++) {
                m.getMatrix()[j][i] = j;
            }
        }
        System.out.println(m.toString());
        Matrix m2 = new Matrix(m.removeKnoten(1, m.getMatrix()));

        System.out.println(m2.toString());

        int[][] x = new int[4][4];
    }
}


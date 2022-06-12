package at.spengergasse.graphenprogramm;

import at.spengergasse.graphenprogramm.model.Graph;
import at.spengergasse.graphenprogramm.viewmodel.GraphViewModel;

public class Main {
    public static void main(String[] args) throws Xzep {
        testImport();
        //testOne();
        //System.out.println(1*0 + 23);
    }

    public static void testOne(){
        /*String erg = "0;0;0;1;1;0;0;1";
        String [] data = erg.split(";");
        System.out.println(data[0]);*/
        int [][] array = new int[1][1];
        System.out.println(array[0][0]);
    }

    public static void testImport() throws Xzep {
        Graph graph = new Graph();
        graph.populateKnotenNamen();
        graph.importCsv("graph2.csv");
        graph.calculatePotenzMatrizen();
        graph.calculateDistanzMatrix();
        graph.setExzentrizitaeten();
        graph.findDurchmesser();
        graph.findRadius();
        System.out.println(graph.toString());
    }
}

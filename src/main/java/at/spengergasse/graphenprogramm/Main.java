package at.spengergasse.graphenprogramm;

import at.spengergasse.graphenprogramm.model.Graph;
import at.spengergasse.graphenprogramm.viewmodel.GraphViewModel;

public class Main {
    public static void main(String[] args) throws Xzep {

        testImport();
        //System.out.println(1*0 + 23);

    }

    public static void testOne(){
        String erg = "0;0;0;1;1;0;0;1";
        String [] data = erg.split(";");
        System.out.println(data[0]);
    }

    public static void testImport() throws Xzep {
        Graph graph = new Graph();
        graph.importCsv("graph.csv");
        System.out.println(graph.toString());
        System.out.println("===================================================");
        int k = 5;
        //graph.addMatrix(graph.multiplyMatrix(graph.getAdjMatrix(),graph.getAdjMatrix()));
        for(int i = 0; i < k; i++){
            graph.addMatrix(graph.multiplyMatrix(graph.getAdjMatrix(),graph.getMatrixInPos(i)));
        }

        System.out.println(graph.toString());
    }
}

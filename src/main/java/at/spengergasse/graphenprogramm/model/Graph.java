package at.spengergasse.graphenprogramm.model;

import at.spengergasse.graphenprogramm.Xzep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    private ArrayList<Matrix> matrixes;
    private ArrayList<Integer> exzentrizitaeten;
    private ArrayList<Character> knotenNamen;
    private int anzKnoten;
    private Matrix distanzMatrix;
    private Matrix wegMatrix;
    private int durchmesser;
    private int radius;
    private int anzKomponenten;
    private String zentrum;

    //CONSTRUCTORS---------------------------------------------------------------------------
    public Graph() {
        matrixes = new ArrayList<>();
        knotenNamen= new ArrayList<>();
        exzentrizitaeten = new ArrayList<>();
        zentrum = "";
    }

    public Graph(String filename) throws Xzep {
        matrixes = new ArrayList<>();
        knotenNamen= new ArrayList<>();
        exzentrizitaeten = new ArrayList<>();
        zentrum = "";
        importCsv(filename);
    }

    public Graph(Matrix m) throws Xzep {
        matrixes = new ArrayList<>();
        knotenNamen= new ArrayList<>();
        exzentrizitaeten = new ArrayList<>();
        //setAdjazenzMatrix(m);
        matrixes.add(m);
        anzKnoten = getAdjMatrix().getLength();
        distanzMatrix = new Matrix(getAdjMatrix().getLength(),getAdjMatrix().getLength());
        m.copyMatrix(m,distanzMatrix);
        //distanzMatrix = new Matrix(matrixes.get(0).getMatrix().clone());
        wegMatrix = new Matrix(getAdjMatrix().getLength(),getAdjMatrix().getLength());
        m.copyMatrix(m,wegMatrix);
        //wegMatrix = new Matrix(matrixes.get(0).getMatrix().clone());

    }

    public void setAdjazenzMatrix(Matrix m) throws Xzep {
        if(m != null){
            matrixes.add(m);
        }else{
            throw new Xzep("null ref fuer m");
        }
    }

    //METHODS---------------------------------------------------------------------------------
    public void calculate() throws Xzep {
        populateKnotenNamen();
        calculatePotenzMatrizen();
        calculateDistanzMatrix();
        calculateWegMatrix();
        setExzentrizitaeten();
        findDurchmesser();
        findRadius();
        findZentrum();
        setAnzKomponenten();
    }

    public void calculateArtikulationen(){

    }

    public String findArtikulationen(){
        //String csv = wegMatrix.toCsv();
        //ArrayList<Matrix> iter = new ArrayList<>();
        String erg = "Artikulationen: ";
        Matrix m = new Matrix(wegMatrix.getMatrix());

        for(int i = 0; i < wegMatrix.getLength(); i++){
            //iter.add(new Matrix(m.removeKnoten(i,m.getMatrix())));
            Matrix temp = new Matrix(m.removeKnoten(i,m.getMatrix()));
            ArrayList<String> tempLines = new ArrayList<>();

            String csv = temp.toCsv();
            Scanner scanner = new Scanner(csv);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tempLines.add(line);
            }
            scanner.close();

                if(recalculateKomponenten(tempLines) > anzKomponenten){
                    erg += getKnotenNamenPos(i) + ", ";
                }
            }
        return erg;
    }

    public String arrayListToCsv(ArrayList<String> list){
        String erg = "";
        for(String s : list){
            erg += s + "\n";
        }
        return erg;
    }

    public int recalculateKomponenten(ArrayList<String> recalculate){
        String csv = arrayListToCsv(recalculate);
        ArrayList<String> tempLines = new ArrayList<>();
        int counter = 0;

        Scanner scanner = new Scanner(csv);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(tempLines.contains(line)){

            }else {
                tempLines.add(line);
            }
        }
        scanner.close();
        counter = tempLines.size();
        return counter;
    }

    public void setAnzKomponenten(){
        String csv = wegMatrix.toCsv();
        ArrayList<String> tempLines = new ArrayList<>();

        Scanner scanner = new Scanner(csv);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(tempLines.contains(line)){

            }else {
                tempLines.add(line);
            }
        }
        scanner.close();
        anzKomponenten = tempLines.size();
    }

    public void findZentrum(){
        String erg = "Zentrum: ";

        for(int e = 0; e < exzentrizitaeten.size(); e++){
            if(exzentrizitaeten.get(e) == radius ){
                erg += knotenNamen.get(e) + ",";
            }
        }

        zentrum = erg;
    }

    public void findDurchmesser(){
        durchmesser = 0;
        for(int d : exzentrizitaeten){
            if(d > durchmesser){
                durchmesser = d;
            }
        }
    }

    public void findRadius(){
        radius = Integer.MAX_VALUE;
        for(int r : exzentrizitaeten){
            if(r < radius){
                radius = r;
            }
        }
    }

    public void populateKnotenNamen(){
        char c;

        for(c = 'A'; c <= 'Z'; ++c)
            knotenNamen.add(c);
    }

    public void calculatePotenzMatrizen() throws Xzep {
        int k = anzKnoten;
        //graph.addMatrix(graph.multiplyMatrix(graph.getAdjMatrix(),graph.getAdjMatrix()));
        for(int i = 0; i < k; i++){
            addMatrix(multiplyMatrix(getAdjMatrix(),getMatrixInPos(i)));
        }
    }

    public void calculateDistanzMatrix(){
        for(int i = 0; i < anzKnoten; i++){
            for(int j = 0; j < anzKnoten; j++){
                if(distanzMatrix.getMatrix()[i][j] == 0 && i != j){
                    distanzMatrix.getMatrix()[i][j] = -1;
                }
            }
        }
        for(int k = 2; k < anzKnoten; k++) {
            for (int i = 0; i < anzKnoten; i++) {
                for (int j = 0; j < anzKnoten; j++) {
                    if (distanzMatrix.getMatrix()[i][j] == -1 && getMatrixInPos(k - 1).getValue(i, j) != 0) {
                        distanzMatrix.getMatrix()[i][j] = k;
                    }
                }
            }
        }
    }

    public void calculateWegMatrix(){
        for(int i = 0; i < anzKnoten; i++){
            for(int j = 0; j < anzKnoten; j++){
                if( i == j){
                    wegMatrix.getMatrix()[i][j] = 1;
                }
            }
        }
        for(int k = 2; k < anzKnoten; k++) {
            for (int i = 0; i < anzKnoten; i++) {
                for (int j = 0; j < anzKnoten; j++) {
                        if(i != j && wegMatrix.getMatrix()[i][j] == 0 && getMatrixInPos(k-1).getValue(i,j) != 0){
                            wegMatrix.getMatrix()[i][j] = 1;
                        }
                    }
                }
            }
        }

    public void setExzentrizitaeten(){
        int tempExz = 0;
        for(int i = 0; i < distanzMatrix.getLength(); i++){
            for(int j = 0; j < distanzMatrix.getLength(); j++){
                if(distanzMatrix.getValue(i,j) > tempExz) {
                    tempExz = (distanzMatrix.getValue(i, j));
                }
            }
            exzentrizitaeten.add(tempExz);
            tempExz = 0;
        }
    }

    public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2){
        //returns Matrix object
         Matrix resultFinal = new Matrix(multiplyArrays(matrix1.getMatrix(), matrix2.getMatrix()));
         return resultFinal;
    }

    public int[][] multiplyArrays(int[][] matrix1, int[][] matrix2){
        //returns int[][]
        int[][] result = new int[matrix1.length][matrix1.length];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result.length; j++){
                int temp = 0;
                for(int a = 0; a < matrix1.length; a++){
                    temp += matrix1[i][a] * matrix2[a][j];
                }
                result[i][j] = temp;
                temp = 0;
            }
        }
        //this.distanzMatrix = new Matrix(tempDistanzMatrix);
        return result;
    }

    public void importCsv(String filename) throws Xzep {
        //zerlegt lines von csv file und uebergibt werte an einem int[][], der in die ArrayList matrixes gespeichert wird
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int limiter = 0;
            line = br.readLine();
            if(line != null){
                String[] dataLength = line.split(";");
                limiter = dataLength.length;
            }
            int[][] importMatrix = new int[limiter][limiter];
            int[][] importDistanzMatrix = new int[limiter][limiter];
            int[][] importWegMatrix = new int[limiter][limiter];
            int row = 0;
            while(line != null){
                line.trim();
                String[] data = line.split(";");
                for(int i = 0; i < data.length; i++){
                    importMatrix[row][i] = Integer.parseInt(data[i]);
                    importDistanzMatrix[row][i] = Integer.parseInt(data[i]);
                    importWegMatrix[row][i] = Integer.parseInt(data[i]);
                }
                row++;
                line = br.readLine();
            }
            Matrix adjMatrix = new Matrix(importMatrix);
            distanzMatrix = new Matrix(importDistanzMatrix);
            wegMatrix = new Matrix(importWegMatrix);
            matrixes.add(adjMatrix);
            anzKnoten = adjMatrix.getLength();
            br.close();
            fr.close();
        }  catch (FileNotFoundException e) {
            throw new Xzep("-error: file not found!");
        } catch (IOException e) {
            throw new Xzep("-error: file cannot be read!");
        }catch (NullPointerException e) {
            throw new Xzep("-error: file does not exist");
        }
    }

    public void addMatrix(Matrix matrix) throws Xzep {
        if (matrix != null){
            matrixes.add(matrix);
        } else {
            throw new Xzep("-error: null ref fuer matrix");
        }
    }

    public String toString(){
        String erg =    /*exzentrizitaetenToString() + "\n\n" +*/
                        "Durchmesser: " + durchmesser + " " +
                        "Radius: " + radius + " "+
                        zentrum + "\n" +
                        "AnzKomponenten: " + getAnzKomponenten() + "\n\n" +
                        /*findArtikulationen() + "\n\n" +*/
                        "Distanzmatrix: \n" +
                        distanzMatrix.toString() + "\n";
        int k = 1;
        for(Matrix e : matrixes){
            erg += "A"+ k + "\n" + e.toString() + "\n";
            k++;
        }
        return erg;
    }

    public String exzentrizitaetenToString() {

        String erg = "Exzentrizitaeten: ";
        for(int i = 0; i < exzentrizitaeten.size(); i++){
            erg += knotenNamen.get(i) + ":" + exzentrizitaeten.get(i) + ",";
        }
        return erg;
    }

    //GETTER----------------------------------------------------------------------------------
    public int getAnzKomponenten() {
        return anzKomponenten;
    }

    public Character getKnotenNamenPos(int i){
        return knotenNamen.get(i);
    }

    public ArrayList<Character> getKnotenNamen(){
        return knotenNamen;
    }

    public ArrayList<Integer> getExzentrizitaeten(){
        return exzentrizitaeten;
    }

    public Matrix getAdjMatrix(){
       return matrixes.get(0);
    }

    public Matrix getWegMatrix() {
        return wegMatrix;
    }

    public Matrix getDistanzMatrix(){
        return this.distanzMatrix;
    }

    public Matrix getMatrixInPos(int pos){return matrixes.get(pos);}

    public ArrayList<Matrix> getMatrixes(){
        return matrixes;
    }

    public int getAnzKnoten(){return anzKnoten;}

    public int getDurchmesser() {
        return durchmesser;
    }

    public int getRadius() {
        return radius;
    }
}

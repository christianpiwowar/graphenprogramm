package at.spengergasse.graphenprogramm.model;

import at.spengergasse.graphenprogramm.Xzep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Matrix> matrixes;
    private int anzKnoten = 0;

    public Graph() {
        matrixes = new ArrayList<>();
    }

    public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2){
         Matrix resultFinal = new Matrix(multiplyArrays(matrix1.getMatrix(), matrix2.getMatrix()));
         return resultFinal;
    }

    public int[][] multiplyArrays(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[matrix1.length][matrix1.length];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result.length; j++){
                int temp = 0;
                for(int a = 0; a < matrix1.length; a++){
                    temp += matrix1[i][a] * matrix2[a][j];
                    //if (temp != 0){} //DISTANZMATRIX
                }
                result[i][j] = temp;
                temp = 0;
            }
        }
        return result;
    }

    public void importCsv(String filename) throws Xzep {
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
            int row = 0;
            while(line != null){
                line.trim();
                String[] data = line.split(";");
                for(int i = 0; i < data.length; i++){
                    importMatrix[row][i] = Integer.parseInt(data[i]);
                }
                row++;
                line = br.readLine();
            }
            //this.adjMatrix = importMatrix;
            Matrix adjMatrix = new Matrix(importMatrix);
            matrixes.add(adjMatrix);
            anzKnoten = adjMatrix.getLength();
            System.out.println("-importCsv finished");
            br.close();
            fr.close();
            //return importMatrix;
        }  catch (FileNotFoundException e) {
            throw new Xzep("-error: file not found!");
        } catch (IOException e) {
            throw new Xzep("-error: file cannot be read!");
        }catch (NullPointerException e) {
            throw new Xzep("-error: file does not exist");
        }
    }

    public String toString(){
        String erg = "";
        int k = 1;
        for(Matrix e : matrixes){
            erg += "A"+ k + "\n" + e.toString() + "\n";
            k++;
        }
        return erg;
    }

    public Matrix getAdjMatrix(){
       return matrixes.get(0);
    }

    public Matrix getMatrixInPos(int pos){return matrixes.get(pos);}

    public void addMatrix(Matrix matrix) throws Xzep {
        if (matrix != null){
            matrixes.add(matrix);
        } else {
            throw new Xzep("-error: null ref fuer matrix");
        }
    }


/*
                 c2
             r2  0   1  0
*                1   0  1
*      c1        0   1  0
*    r1 0   1  0
*       1   0  1
*       0   1  0
r1=c2= limiter=matrix.length
c1=r2
a00 = ar1c1*ar2c2 + ans
*
    public void multiply(int[][] matrix1, int[][] matrix2) throws Xzep {
        if(matrix1 != null && matrix2 != null){
            int temp = 0;
            int limiter = matrix1.length;

            int[][] result = new int[matrix1.length][matrix1.length];

            for(int colRes = 0; colRes < matrix1.length; colRes++){
                for(int row = 0; row < matrix1.length;row++){
                    for(int col = 0; col < matrix1.length;col++){
                        temp += matrix1[row][col] * matrix2[col][row];
                    }
                    //save temp to result in pos ??? [r1c2][] for row
                    result[row][colRes] = temp;
                    temp = 0;
                }
            }
        }else{
            throw new Xzep("-error: invalid matrix -null");
        }
    }*/


}

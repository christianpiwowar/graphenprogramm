package at.spengergasse.graphenprogramm.model;

public class Matrix {
    private int[][] matrix;

    public int[][] getMatrix() {
        return matrix;
    }

    public Matrix(int rows, int cols){
        matrix = new int[rows][cols];
    }
    public Matrix(int [][] matrix){
        this.matrix = matrix;
    }

    public int getLength(){
        return matrix.length;
    }
    public String toString(){
        String erg= "";
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (j == (matrix[i].length - 1)) {
                    erg += matrix[i][j] + "\n";
                }else {
                    erg += matrix[i][j] + ", ";
                }
            }
        }
        return erg;
    }
    public String toCsv(){
        String erg= "";
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (j == (matrix[i].length - 1)) {
                    erg += matrix[i][j] + "\n";
                }else {
                    erg += matrix[i][j] + ";";
                }
            }
        }
        return erg;
    }
}

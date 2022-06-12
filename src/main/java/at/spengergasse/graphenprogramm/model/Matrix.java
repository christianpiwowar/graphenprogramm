package at.spengergasse.graphenprogramm.model;

public class Matrix {
    private int[][] matrix;

    //GETTER-----------------------------------------------------------------------------------------
    public int[][] getMatrix() {
        return matrix;
    }

    public int getLength(){
        return matrix.length;
    }

    public int getValue(int row, int col){
        return matrix[row][col];
    }

    //CONTRUCTORS-------------------------------------------------------------------------------------
    public Matrix(int rows, int cols){
        matrix = new int[rows][cols];
    }

    public Matrix(int [][] matrix){
        this.matrix = matrix;
    }

    public Matrix(){matrix = new int[1][1];}

    //METHODS------------------------------------------------------------------------------------------
    public String toString(){
        String erg= "";
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (j == (matrix[i].length - 1)) {
                    erg += String.format("%6d", matrix[i][j]) + "\n";
                }else {
                    erg += String.format("%6d", matrix[i][j]);
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

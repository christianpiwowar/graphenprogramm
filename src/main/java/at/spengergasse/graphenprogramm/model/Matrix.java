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
        this.matrix = matrix.clone();
    }

    public Matrix(){matrix = new int[1][1];}

    //METHODS------------------------------------------------------------------------------------------
    public String toString(){
        String erg= "";
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (j == (matrix[i].length - 1)) {
                     if(matrix[i][j] < 0){
                         erg += String.format("%6s", "∞") + "\n";
                    }else{erg += String.format("%6d", matrix[i][j]) + "\n";}
                }else {
                    if(matrix[i][j] < 0){
                        erg += String.format("%6s", "∞");
                    }else{erg += String.format("%6d", matrix[i][j]);}
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

    public int[][] removeKnoten(int pos, int[][] original){
        int[][] knotenRemoved = new int[original.length -1][original.length-1];
        int row=0;
        int col=0;

        for(int i = 0; i < original.length; i++){
            if(i != pos){
                for(int j = 0; j < original.length; j++){
                    if(j != pos){
                        knotenRemoved[row][col] = original[i][j];
                        col++;
                    }
                }
                col = 0;
                row++;
            }
        }
        return knotenRemoved;
    }

    public void setValue(int row, int col, int value){
        this.matrix[row][col] = value;
    }

    public void copyMatrix(Matrix origin, Matrix destiny){
        for(int i = 0; i < origin.getLength(); i++){
            for(int j = 0; j < origin.getLength(); j++){
                destiny.getMatrix()[j][i] = origin.getMatrix()[j][i];
            }
        }
    }
}

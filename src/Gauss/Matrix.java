package Gauss;

public class Matrix {
    public static final int NORMALIZED=100;
    public static final int TOPRANGE=10;
    private int[][] matrix;
    private int rows;
    private int columns;

    public Matrix(int rows, int columns) {
        this.rows=rows;
        this.columns=columns;
        this.matrix=new int[rows][columns];
        this.fillMatrix();
    }
    public void printMatrix() {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                System.out.print(this.matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public Matrix gaussMethod() {
        int[][] auxMatrix=this.copyMatrix();
        if(auxMatrix[0][0]==0) {
            int[] bufferRow = new int[this.columns];
            int nonZeroRow = 1;
            boolean nonZeroRowFound=false;
            while(nonZeroRow < this.rows && !nonZeroRowFound) {
                if(this.matrix[nonZeroRow][0] != 0) {
                    nonZeroRowFound=true;
                    for(int i=0;i<this.columns;i++) {
                        bufferRow[i]=auxMatrix[0][i];
                        auxMatrix[0][i]=auxMatrix[nonZeroRow][i];
                        auxMatrix[nonZeroRow][i]=bufferRow[i];
                    }
                }
                nonZeroRow++;
            }
        }
        for(int i=0;i<this.columns;i++) {
            int currentRow =i;
            int superiorPivot=0;
            if(currentRow <this.rows-1) {
                superiorPivot = auxMatrix[currentRow][i];
            }
            while(superiorPivot==0 && currentRow <this.rows-1) {
                currentRow++;
                superiorPivot=auxMatrix[currentRow][i];
            }
            for(int j = currentRow; j<this.rows-1; j++) {
                int inferiorPivot=auxMatrix[j+1][i];
                int superiorPivotNormalized=superiorPivot/Utils.GCF(inferiorPivot,superiorPivot);
                int inferiorPivotNormalized=inferiorPivot/Utils.GCF(inferiorPivot,superiorPivot);
                if(inferiorPivot==0) {
                    continue;
                }
                for(int k=i;k<this.columns;k++) {
                    auxMatrix[j+1][k]=auxMatrix[j+1][k]*superiorPivotNormalized-auxMatrix[i][k]*inferiorPivotNormalized;
                }
            }
        }
        Matrix matrixToBeReturned=new Matrix(this.rows,this.columns);
        matrixToBeReturned.matrix=auxMatrix;
        return matrixToBeReturned;
    }
    public void fillMatrix() {
        for(int i=0;i<this.rows;i++) {
            for(int j=0;j<this.columns;j++) {
                this.matrix[i][j]=(int)(Math.random()*NORMALIZED)%TOPRANGE;
            }
        }
    }

    public Integer determinantCalculator() {
        if(this.rows!=this.columns) {
            System.out.println("This is not a square matrix!");
            return null;
        }
        else {
            Double determinant = 1.0;
            double[][] auxMatrix=this.transformMatrixToDouble();
            if (auxMatrix[0][0] == 0.0) {
                double[] bufferRow = new double[this.columns];
                int nonZeroRow = 1;
                boolean nonZeroRowFound = false;
                while (nonZeroRow < this.rows && !nonZeroRowFound) {
                    if (auxMatrix[nonZeroRow][0] != 0.0) {
                        nonZeroRowFound = true;
                        determinant = -1.0;
                        for (int i = 0; i < this.columns; i++) {
                            bufferRow[i]=auxMatrix[0][i];
                            auxMatrix[0][i]=auxMatrix[nonZeroRow][i];
                            auxMatrix[nonZeroRow][i]=bufferRow[i];
                        }
                    }
                    nonZeroRow++;
                }
            }
            for (int i = 0; i < this.columns; i++) {
                int currentRow = i;
                double superiorPivot = 0.0;
                if (currentRow < this.rows - 1) {
                    superiorPivot = auxMatrix[currentRow][i];
                }
                while (superiorPivot == 0 && currentRow < this.rows - 1) {
                    currentRow++;
                    superiorPivot = auxMatrix[currentRow][i];
                }
                for (int j = currentRow; j < this.rows - 1; j++) {
                    double inferiorPivot = auxMatrix[j + 1][i];
                    if (inferiorPivot == 0) {
                        continue;
                    }
                    for (int k = i; k < this.columns; k++) {
                        auxMatrix[j + 1][k] = auxMatrix[j + 1][k] - auxMatrix[i][k] * inferiorPivot/superiorPivot;
                    }
                }

                determinant *= auxMatrix[i][i];
            }
            return new Integer(determinant.intValue());
        }
    }
    public int rankCalculator() {
        this.gaussMethod();
        int rank=0;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                if(matrix[i][j]!=0) {
                    rank++;
                    break;
                }
            }
        }
        return rank;
    }

    public Integer traceCalculator() {
        Integer trace=0;
        if(this.rows!=this.columns) {
            System.out.println("This is not a square matrix!");
            trace=null;
        }
        else {
            for(int i=0;i<this.rows;i++) {
                trace+=this.matrix[i][i];
            }
        }
        return trace;
    }

    public int[][] copyMatrix() {
        int[][] matrixToBeReturned = new int[this.rows][this.columns];
        for(int i=0;i<this.columns;i++) {
            for(int j=0;j<this.rows;j++) {
                matrixToBeReturned[i][j]=this.matrix[i][j];
            }
        }
        return matrixToBeReturned;
    }
    public double[][] transformMatrixToDouble() {
        double[][] matrixToBeReturned = new double[this.rows][this.columns];
        for(int i=0;i<this.columns;i++) {
            for(int j=0;j<this.rows;j++) {
                matrixToBeReturned[i][j]=this.matrix[i][j];
            }
        }
        return matrixToBeReturned;
    }
}

package Gauss;

import java.util.Scanner;

public class Utils {
    public static Scanner sc = new Scanner(System.in);
    public static Matrix matrix;

    public static void menu() {
        boolean keepExecution=true;
        String option="";
        while(keepExecution) {
            System.out.println("Choose an option: ");
            System.out.println("1: Generate matrix");
            System.out.println("2: Print matrix");
            System.out.println("3: Triangulate matrix");
            System.out.println("4: Calculate matrix rank");
            System.out.println("5: Calculate matrix determinant");
            System.out.println("6: Calculate matrix trace");
            System.out.println("0: Terminate program execution");
            option=sc.nextLine();
            switch (option) {
                case "1": {
                    System.out.println("How many rows?");
                    int rows=Integer.parseInt(sc.nextLine());
                    System.out.println("How many columns?");
                    int columns=Integer.parseInt(sc.nextLine());
                    matrix=new Matrix(rows,columns);
                    break;
                }
                case "2": {
                    matrix.printMatrix();
                    break;
                }
                case "3": {
                    matrix.gaussMethod().printMatrix();
                    break;
                }
                case "4": {
                    System.out.println("The rank of the matrix is "+matrix.rankCalculator());
                    break;
                }
                case "5": {
                    System.out.println("The determinant is "+matrix.determinantCalculator());
                    break;
                }
                case "6": {
                    System.out.println("The trace is "+matrix.traceCalculator());
                    break;
                }
                case "0": {
                    sc.close();
                    keepExecution=false;
                }
            }
        }
    }
    public static int GCF(int a, int b) {
        int gcf=1;
        boolean gcfNotFound=true;
        int currentFactor=Math.min(a,b);
        while (gcfNotFound && currentFactor>0) {
            if(a%currentFactor==0 && b%currentFactor==0) {
                gcf=currentFactor;
                gcfNotFound=false;
            }
            currentFactor--;
        }
        return gcf;
    }

}

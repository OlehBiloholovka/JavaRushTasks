package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,1,1,1}
                , {1,1,1,1,0}
                , {0,1,1,1,0}
                , {1,1,1,1,0}
                , {1,1,1,1,1}
                , {0,1,1,1,0}};
        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
//        int[][] matrixForCalc = new int[matrix.length+1][matrix[0].length];
        int maxSquare = 0;
//        for (int i = 0; i < matrixForCalc.length; i++) {
//            for (int j = 0; j < matrixForCalc[i].length; j++) {
//                if (i == 0 || j== 0) {
//                    matrixForCalc[i][j] = 0;
//                }else {
//                    matrixForCalc[i][j] = minValue(matrixForCalc[i-1][j], matrixForCalc[i-1][j-1], matrixForCalc[i][j-1])
//                            + matrix[i-1][j-1];
//                    if (maxSquare < matrixForCalc[i][j]) maxSquare = matrixForCalc[i][j];
//                }
//            }
//        }
//        return maxSquare;

        int[][]matrixForCalc = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 || j == 0) {
                    matrixForCalc[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        matrixForCalc[i][j] = matrix[i][j] + minValue(matrixForCalc[i][j-1], matrixForCalc[i-1][j], matrixForCalc[i-1][j-1]);
                    } else {
                        matrixForCalc[i][j] = 0;
                    }
                    if (maxSquare < matrixForCalc[i][j]) maxSquare = matrixForCalc[i][j];
                }

            }
        }
        return maxSquare * maxSquare;
    }
    private static int minValue(int a, int b, int c){
        int min = a < b ? a : b;
        return min < c ? min : c;
    }
}

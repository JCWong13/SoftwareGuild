/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.summativesums;

/**
 *
 * @author janie
 */
public class SummativeSums {

    public static void main(String[] args) {
        int[] firstRow = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] secondRow = {999, -60, -77, 14, 160, 301};
        int[] thirdRow = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
            140, 150, 160, 170, 180, 190, 200, -99};
//
//        addRows(firstRow);
//        addRows(secondRow);
//        addRows(thirdRow);

        System.out.println("#1 Array Sum: " + addRows(firstRow));
        System.out.println("#2 Array Sum: " + addRows(secondRow));
        System.out.println("#3 Array Sum: " + addRows(thirdRow));

    }

    public static int addRows(int[] rowArray) {
        int sumRow = 0;

        for (int i = 0; i < rowArray.length; i++) {
//            rowArray[i] += rowArray[i] + 1;
            sumRow += rowArray[i];
           
        }
         return sumRow;
    }
}

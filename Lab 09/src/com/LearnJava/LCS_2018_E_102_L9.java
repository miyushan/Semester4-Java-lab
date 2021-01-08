package com.LearnJava;

import java.util.Scanner;
public class LCS_2018_E_102_L9 {

    /**
     *
     *@Author 2018/E/102
     *
     */

    /**
     *
     * This programme is used to find the  Longest Common Subsequence of 2 words.
     *
     * @param args
     */
    public static void main (String[] args) {

        char[] array1 = null, array2 = null;
        int intInputSize1 = 0, intInputSize2 = 0;

        for (int i = 0; i < 2 ; i++){

            //get sizes of input arrays as an input
            Scanner inputSize = new Scanner(System.in);
            System.out.println("String " + (i+1) + " size: ");
            int intInputSize = inputSize.nextInt();

            //get input names
            Scanner inputName = new Scanner(System.in);
            String name = inputName.nextLine();
            char[] array = getCharInputs(intInputSize, name);

            switch (i){
                case 0:
                    array1 = array;
                    intInputSize1 = intInputSize;
                case 1:
                    array2 = array;
                    intInputSize2 = intInputSize;
            }

        }

        //get the length of Longest Common Subsequence
        int length = LCS(array1, array2, intInputSize1, intInputSize2);
        System.out.println("\nLength of Longest Common Subsequence: "+ length);



    }


    /**
     *
     * This method is used to convert the input String to char type
     *
     * @param intInputSize  characters in input word
     * @param name          input word
     * @return
     */
    public static char[] getCharInputs(int intInputSize, String name){

        if( intInputSize <= 100 && intInputSize > 0 ){

        }
        //char array to convert Names to char
        char[] charArray = new char[intInputSize];

        //input name converts to char values
        for(int i = 0; i < intInputSize ; i++){
            charArray[i] = name.charAt(i);
        }

        return charArray;
    }


    /**
     *
     * This method is used to produce the two dimentional array of two inputs
     * @param arr1  input array1
     * @param arr2  input array2
     * @param a     length of array1
     * @param b     length of array2
     * @return
     */
    static int LCS(char[] arr1, char[] arr2, int a, int b)
    {
        if (a == 0 || b == 0){
            return 0;
        }
        //elements at same posision in two strings are equal
        if (arr1[a - 1] == arr2[b - 1]){
            return 1 + LCS(arr1, arr2, a - 1, b - 1);
        }
        //elements at same posision in two strings are not equal
        else{
            return maximumLength(LCS(arr1, arr2, a, b - 1), LCS(arr1, arr2, a - 1, b));
        }

    }



    /**
     *
     * This method is used to find the length of Longest Common Subsequence
     * @param a     length of array1
     * @param b     length of array2
     * @return
     */
    public static int maximumLength(int a, int b)
    {
        return (a > b) ? a : b;
    }





}

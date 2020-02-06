/*
Assignment 1;
Titus Wen

 */


import java.util.Scanner;
import java.io.File;
public class hw1{
    public static void main(String[] args) throws Exception{


        String[][] arr = new String[200][5];
        readFile(arr);
        /*
        for(int i=0;i<200;i++){
            for(int j=0;j<5;j++) {
                System.out.println(arr[i][j]);
            }
        }

         */
        for (int i=0;i<200;i++){
            System.out.println(arr[i][0]);
        }





    }

    //accepts 2d array as parameter and reads in data from xls file into array
    public static void readFile(String[][] arr) throws Exception{
        //array[] numberArray = {0,1,2,3,4,5,6,7,8,9};
        //ArrayList<Integer> numbers = new ArrayList<>(numberArray);

        Scanner file = new Scanner(new File("../data/list.xls"));
        for(int i = 0; i<200;i++){
            String line = file.nextLine();
            int comma = 0;
            for (int j=0;j<5;j++) {
                if(line.contains(",")) {
                    comma = line.indexOf(",");
                    boolean wrongCommaPlace = true;
                    do {
                        //checks to see if there is a space after comma(separation of names) or if the comma has a number in front and two numbers behind (ie 10,000)
                        if (line.charAt(comma+1)==' ' || (Character.isDigit(line.charAt(comma-1)) && Character.isDigit(line.charAt(comma+1)) && Character.isDigit(line.charAt(comma+2)))){
                            comma = line.indexOf(",", comma+1);
                            wrongCommaPlace=true;
                        }else{
                            wrongCommaPlace=false;
                        }
                    }while(wrongCommaPlace);
                    String insert = line.substring(0, comma);
                    arr[i][j] = insert;
                    //System.out.println(insert);
                    line = line.substring(comma+1,line.length());
                }else{
                    arr[i][j]= line;
                    //System.out.println(line);
                }

            }
        }
    }
}

/*
Assignment 1;
Titus Wen
*/
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class hw1{
    public static void main(String[] args) throws Exception{

        String[][] arr = new String[200][5];

        String fileName;

        //fileName = "../data/list.xls";
        fileName = "../data/regional2-14.csv";

        //reads in file and separates values into 2d array
        readFile(arr, fileName);

        //creates doubly linked list
        TopStreamingArtists list = new TopStreamingArtists();

        //creates arraylist to store sorted names
        ArrayList<String> sort = new ArrayList<>();
        sort(arr,sort);

        //Created hashmap to count amount of times artist name shows up
        HashMap<String, Integer> map = new HashMap<>();
        countArtist(sort,map);

        //adds names (without repetition) into linked list
        ArrayList<String> orderedNameSet = new ArrayList<>(map.keySet());
        orderedNameSet.sort(String.CASE_INSENSITIVE_ORDER);
        for(String names: orderedNameSet){
            list.insert(names);
        }

        //prints Array to file
        printArrayToFile(arr);

        //prints Artist and amount of times they show up in the array using linked list
        printArtistAndCountToFile(map,list);

    }

    //accepts 2d array as parameter and reads in data from xls file into array
    public static void readFile(String[][] arr, String fileName) throws Exception{

        Scanner file = new Scanner(new File(fileName));

        //Ignores header(first two lines) in file
        String fileHeader = file.nextLine();
        fileHeader = file.nextLine();

        //Goes through each row
        for(int i = 0; i<200;i++){
            String line = file.nextLine();
            int comma = 0;
            //Goes through each column
            for (int j=0;j<5;j++) {
                if(line.contains(",")) {
                    comma = line.indexOf(",");
                    boolean wrongCommaPlace = true;
                    do {
                        //checks to see if there is a space after comma(separation of names) or if the comma has a number in front and two numbers behind (ie 10,000)
                        if (line.charAt(comma+1)==' ' || (Character.isDigit(line.charAt(comma-1)) && Character.isDigit(line.charAt(comma+1)) && Character.isDigit(line.charAt(comma+2)))){
                            comma = line.indexOf(",", comma+1);
                        }else{
                            wrongCommaPlace=false;
                        }
                    }while(wrongCommaPlace);
                    //adds characters up to first comma into 2d array
                    String insert = line.substring(0, comma);
                    arr[i][j] = insert;
                    //removes characters up to first comma
                    line = line.substring(comma+1,line.length());
                }else{
                    //if there is no comma(last column of information), add in into last spot
                    arr[i][j]= line;
                }

            }
        }
        //closes Scanner object
        file.close();
    }

    //adds names from 2d array into arraylist and sorts in ascending order
    public static void sort(String[][] arr, ArrayList<String> sort){
        //reads in name from 2d array column and removes quotation marks to add into arraylist
        for (int i=0;i<200;i++) {
            String nameHold = arr[i][2];
            if(nameHold.charAt(0)=='"')
                nameHold = nameHold.substring(1,nameHold.length()-1);
            sort.add(nameHold);
        }
        //sorts in ascending order
        sort.sort(String.CASE_INSENSITIVE_ORDER);
    }

    //adds artist into hashmap and changes value according to the amount of time the name shows up
    public static void countArtist(ArrayList<String> sort, HashMap<String, Integer> map ){
        for(String names: sort){
            if(map.containsKey(names)){
                Integer value = map.get(names);
                value++;
                map.put(names,value);
            }
            else{
                map.put(names,1);
            }
        }
    }

    //formats data in 2d array and prints to file
    public static void printArrayToFile(String[][] arr) throws Exception{
        PrintStream output = new PrintStream(new FileOutputStream(new File("../output/2DArray.txt")));
        output.printf("%-9s|%-60s|%-25s|%-9s|%s%n%n" , "Position","Track Name", " Artist" , "Streams", "URL ");
        for(int i=0; i<200; i++){
            output.printf("%-9s %-60s %-25s %-9s %s%n",arr[i][0],arr[i][1],arr[i][2],arr[i][3],arr[i][4]);
        }
        //closes PrintStream
        output.close();
    }

    //uses hashmap and linked list to print out data to file
    public static void printArtistAndCountToFile(HashMap<String, Integer> map, TopStreamingArtists list)throws Exception{
        PrintStream output = new PrintStream(new FileOutputStream(new File("../output/ArtistNamesAndCount.txt")));
        output.printf("%-30s | %15s%n%n" , "Artist Name", "Number of appearances on top 200");
        Artist node = list.getLast();
        while(node != null){
            output.printf("%-30s %10d%n", node.toString() , map.get(node.toString()));
            node = node.getPrevious();
        }
        output.close();
    }

}

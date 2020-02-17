/* The List TopStreamingArtists is composed of a series of artist names */

//Object that holds nodes and stores names of an artist in each node
public class TopStreamingArtists {
    private Artist first;
    private Artist last;

    public void TopStreamingArtists(){
        first = null;
        last = null;
    }

    //checks to if there is an element in the list
    public boolean isEmpty(){
        return (first == null);
    }

    //creates a node if empty and if there is already a node it places it in front and sets the pointers
    public void insert(String name){
        if(isEmpty()){
            first = new Artist(name);
            last = first;
        }
        else{
            Artist newFirst = new Artist(name);
            newFirst.setNext(first);
            first.setPrevious(newFirst);
            first = newFirst;
        }
    }

    //goes through each node and prints out the name starting from the front
    public void printAll(){
        Artist current = first;
        while(current!=null){
            System.out.println(current);
            current = current.getNext();
        }
    }

    //goes through each node and prints out the name starting from the back
    public void printAllBackwards(){
        Artist current = last;
        while(current!=null){
            System.out.println(current);
            current = current.getPrevious();
        }
    }
}



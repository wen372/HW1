/* The List TopStreamingArtists is composed of a series of artist names */

//Object that holds nodes and stores names of an artist in each node
public class TopStreamingArtists {
    private Artist first;
    public void TopStreamingArtists(){
        first = null;
    }
    public boolean isEmpty(){
        return (first == null);
    }
    public void insert(String name){
        if(isEmpty()){
            first = new Artist(name);
        }
        else{
            Artist newFirst = new Artist(name);
            newFirst.setNext(first);
            first = newFirst;
        }
    }

    //goes through each node and prints out the name
    public void printAll(){
        Artist current = first;
        while(current!=null){
            System.out.println(current);
            current = current.getNext();
        }
    }
}



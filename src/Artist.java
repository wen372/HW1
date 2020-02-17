/* A node represents an artist */

//Node that holds artist name;
public class Artist {
    private String name;
    private Artist next = null;
    private Artist previous = null;

    //Constructor
    public Artist(String name){
        this.name = name;
    }
    //sets pointer to next node
    public void setNext(Artist artist){
        this.next = artist;
    }
    //returns reference from next node
    public Artist getNext(){
        return next;
    }
    //sets pointer to previous node
    public void setPrevious(Artist artist){
        this.previous = artist;
    }
    //returns reference from previous node
    public Artist getPrevious(){
        return previous;
    }

    public String toString(){
        return name;
    }


}

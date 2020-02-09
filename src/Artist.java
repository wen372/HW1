/* A node represents an artist */

//Node that holds artist name;
public class Artist {
    private String name;
    private Artist next = null;
    // add constructors
    public Artist(String name){
        this.name = name;
    }
    public void setNext(Artist artist){
        this.next = artist;
    }
    public Artist getNext(){
        return next;
    }
    public String toString(){
        return name;
    }
}

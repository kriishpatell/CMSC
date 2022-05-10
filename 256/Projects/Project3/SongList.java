/******************************************************************************************************
* SongList.java
* Krish Patel
* Project 3
* CMSC256 Semester 2
* This file creates a LinkedList of song objects and allows you to see information about artist songs
******************************************************************************************************/

package Projects.Project3;

import bridges.data_src_dependent.Song;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.SLelement;

import java.util.ArrayList;

public class SongList implements Projects.Project3.List<bridges.data_src_dependent.Song> {    

    // Create linked list of SLelements and populate with Song objects
    private SLelement<Song> head;
    private SLelement<Song> tail;
    private SLelement<Song> curr;

    private int listSize;

    // Connect to bridges account
    Bridges bridges = new Bridges(5, "kriishpatell", "666459817853");
    
    // No argument constructor
    public SongList() {
        DataSource ds = bridges.getDataSource();
        listSize = 0;
        clear();

        ArrayList<Song> songArr = new ArrayList<Song>();

        try{
            songArr = ds.getSongData();
        } catch (Exception e){
            System.out.println("Unable to connect to bridges");
        }

        for(Song songs : songArr){
            append(songs);
        }
    }

    // Parameterized constructor
    public SongList(int size){
        this();
        listSize = size;
    }

    // Pass artist parameter and check their songs and append to list
    public String getSongsByArtist(String artist) {
        moveToStart();
        StringBuilder stringBuilder = new StringBuilder();

        while(!isAtEnd()){
            if(curr.getValue().getArtist().equals(artist)){
                String output = "\nTitle: " + curr.getValue().getSongTitle() + "\nArtist: " + curr.getValue().getArtist() + "\nAlbum: " + curr.getValue().getAlbumTitle() + "\n";
                stringBuilder.append(output);
                next();
            } else {
                next();
            }
        }

        if(stringBuilder.toString().isEmpty()){
            return "There are no songs by " + artist + " in this playlist.";
        }

        return stringBuilder.toString();
    }
    
    // Clears the linked list
    public void clear() {
        curr = tail = new SLelement<Song>();
        head = new SLelement<Song>(tail);
        listSize = 0;
    }
    
    // Insert a song object (parameter) to the linked list
    public boolean insert(Song it) {
        SLelement<Song> temp = new SLelement<>(it);
        if(curr == head){
            temp.setNext(head);
            listSize++;
            return true;
        }
        curr.setNext(new SLelement<Song>(curr.getValue(), curr.getNext()));
        curr.setValue(it);
        if(tail == curr){
            tail = curr.getNext();
        }
        listSize++;
        return true;
    }
    
    // Create new node for linked list
    public boolean append(Song it) {
        SLelement<Song> temp = new SLelement<>(null);
        tail.setNext(temp);
        tail.setValue(it);
        tail = tail.getNext();
        listSize++;
        return true;
    }
    
    // Remove and return object from linked list
    public Song remove() {
        if(curr == tail) { return null; }
        
        Song song = curr.getValue();
        curr.setValue(curr.getNext().getValue());

        if(curr.getNext() == tail){
            tail = curr;
        }
        
        curr.setNext(curr.getNext().getNext());
        listSize--;
        return song;
    }
    
    // Move the current node to the start
    public void moveToStart() { 
        curr = head.getNext(); 
    }
    
    // Move the current node to the end
    public void moveToEnd() { 
        curr = tail; 
    }
    
    // Move current node to the previous node
    public void prev() {       
        if(head.getNext() == curr) { 
            return; 
        }
        
        SLelement<Song> songList = head;
        
        while(songList.getNext() != curr){
            songList = songList.getNext();
        }
        curr = songList;
    }
    
    // Move current node to the next node
    public void next() {
        if(curr != tail) { 
            curr = curr.getNext(); 
        }        
    }

    // Return the size of the list
    public int length() {
        return listSize;
    }
    
    // Return the position of the current node
    public int currPos() {
        SLelement<Song> songList = head.getNext();
        int i;
        
        for(i = 0; curr != songList; i++){
            songList = songList.getNext();
        }
        
        return i;
    }
    
    // Set the current node to the passed "pos" parameter
    public boolean moveToPos(int pos) {
        if((pos < 0) || (pos > listSize)){
            return false;
        }

        for(int i = 0; i < pos; i++){
            curr = curr.getNext();
        }
        
        return true;
    }
    
    // Return true if current position is at the end of the list
    public boolean isAtEnd() { 
        return curr == tail; 
    }
   
    // Return the data within the current node
    public Song getValue() {
        if(curr == tail){
            return null;
        }
        return curr.getValue();
    }  
}

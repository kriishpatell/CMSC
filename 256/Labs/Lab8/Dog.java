package Labs.Lab8;

import java.util.Objects;
import java.util.Comparator;

public class Dog implements Comparator<Dog>{
    private String dogName;
    private int count;
    
    public Dog(){
        dogName = "";
        count = 0;
    }

    public Dog(String dogName, int count){
        this.dogName = dogName;
        this.count = count;
    }

    public String getDogName(){
        return dogName;
    }

    public void setDogName(String dogName){
        this.dogName = dogName;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count; 
    }

    public String toString() {
        return "Dog{" + "name = '" + dogName + '\'' + ", count = " + count + '}';
    }

    public int compare(Dog o1, Dog o2) {
        return (o1.getDogName()).compareTo(o2.getDogName());
    }

    public boolean equals(Object dog) {
        if (this == dog) {
            return true;
        }
        if (dog == null || getClass() != dog.getClass()) {
            return false;
        }
        Dog dogVar = (Dog) dog;
        return count == dogVar.count && Objects.equals(dogName, dogVar.dogName);
    }

}

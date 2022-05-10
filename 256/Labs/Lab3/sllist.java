package Labs.Lab3;

import bridges.connect.Bridges;
import javafx.scene.control.Slider;
import bridges.base.SLelement;

public class sllist {
    public static void main(String[] args) throws Exception {
        Bridges bridges = new Bridges(0,"kriishpatell","666459817853");

        SLelement sle0 = new SLelement("Hello","");
        SLelement sle1 = new SLelement("World","");

        sle0.setNext(sle1);

        sle0.getVisualizer().setColor("black");
        sle0.getVisualizer().setOpacity(0.5f);
        sle1.getVisualizer().setColor("green");

        bridges.setDataStructure(sle0);

        bridges.visualize();
    }
}

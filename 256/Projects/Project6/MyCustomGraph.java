/***********************************************************************************************
 * Krish Patel
 * Project 6 
 * Reading in a file name and constructing and interpreting a graph from values within the file
 * CMSC 256 Semester 2  
 ***********************************************************************************************/

package Projects.Project6;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MyCustomGraph<V> extends UnweightedGraph<V> {

    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException {
        int numberOfVertices = 0;
        List<Edge> edges = new ArrayList<Edge>();

        try {
            /* create a file object and read using Scanner class */
            File file = new File(fileName);
            Scanner scr = new Scanner(file);

            String[] values = new String[0];

            /* number of vertices is represented by the first line of the file */
            numberOfVertices = Integer.parseInt(scr.nextLine());

            while (scr.hasNextInt()) {
                /* String array is flooded with each value from each line following the first */
                values = scr.nextLine().split(" ");
                try {
                    /* for each value for the line, add an edge to the first value which is the initial vertex */
                    for (int i = 1; i < values.length; i++) {
                        Edge newEdge = new Edge(Integer.parseInt(values[0]), Integer.parseInt(values[i]));
                        edges.add(newEdge);
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Value of vertex is invalid");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File was not found");
        }

        /* create new graph object and instantiate it with list edges and # vertices as indicated by the implemented class */
        return new MyCustomGraph<V>(edges, numberOfVertices);
    }

    public boolean isComplete() {
        /* grab number of vertices */
        int numberOfVertices = vertices.size();

        /* check if # vertices is greater than the # of vertices found */
        if((numberOfVertices * (numberOfVertices - 1) / 2) > dfs(0).getNumberOfVerticesFound()){
            return false;
        }
        return true;
    }

    public boolean areAdjacent(V v1, V v2) {
        /* get the integer value for the index of the vertex within the list */
        int v1Index = getIndex(v1);

        /* instantiate a new list with neighbors of first vertex */
        List<Integer> v1Neighbor = getNeighbors(v1Index);

        /* check if any neighbor of the first vertex is adjacent to the second */
        for(int i = 0; i < v1Neighbor.size(); i++){
            if(v1Neighbor.get(i) == v2){
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        /* check if vertex size is less than 2 (graph only consists of 2 points) */
        if (vertices.size() < 2) {
            return false;
        }

        /* call for the recursive method to check if the number of vertices found is equal to the size of the graph (with traversal each vertex is connected) */
        return dfs(0).getNumberOfVerticesFound() == vertices.size();
    }

    public List<V> getShortestPath(V begin, V end) {
        /* initialize list with path from one vertex to end vertex using bfs traversal */
        List<V> distance = bfs((int) end).getPath((int) begin);
        if (end != distance.get(distance.size()-1)) {
            return null;
        }
        return distance;
    }

    /* determine if cycle in graph exists or not */
    public boolean hasCycle() {
        List<V> listVertices = new ArrayList<>();
        boolean[] visitedVertex = new boolean[vertices.size()];
        int[] parent = new int[vertices.size()];

        /* check vertices and add to list if the vertex can be visited using dfs traversal */
        for(int i = 0; i < vertices.size(); i++){
            visitedVertex[i] = false;
            listVertices.add(vertices.get(i));
            parent[i] = dfs(i).getParent(i);
        }

        for(int i = 0; i < vertices.size(); i++){
            if(!visitedVertex[i]){
                /* call to recursive parameterized method */
                return hasCycle(i, parent, listVertices, visitedVertex);
            }
        }
        return false;
    }

    /* create another recursive method to test for all the unvisited vertices in the unparameterized hasCycle() method */
    public boolean hasCycle(int vertex, int[] parent, List<V> listVertices, boolean[] visitedVertex){
        listVertices.remove(vertex);
        visitedVertex[vertex] = true;
        for(Edge edge : neighbors.get(vertex)){
            if(!visitedVertex[vertex]){
                parent[vertex] = vertex;
                hasCycle(parent[vertex], parent, listVertices, visitedVertex);
            }
            return visitedVertex[vertex] && vertex != parent[vertex];
        }
        return false;
    }

    /* constructor classes for custom graph */
    public MyCustomGraph() { super(); }
    public MyCustomGraph(V[] vertices, int[][] edges) { super(vertices, edges); }
    public MyCustomGraph(List<V> vertices, List<Edge> edges) { super(vertices, edges); }
    public MyCustomGraph(List<Edge> edges, int numberOfVertices) { super(edges, numberOfVertices); }
    public MyCustomGraph(int[][] edges, int numberOfVertices) { super(edges, numberOfVertices); }
    public static void main(String[] args) {
        MyCustomGraph<Integer> myGraph = new MyCustomGraph<>();
        try {
            MyCustomGraph<Integer> myGraph2 = myGraph.readFile("test2-1.txt");
            
            System.out.println("Graph of text2-1.txt");
            System.out.println("is complete => " + myGraph2.isComplete());
            System.out.println("is Connected => " + myGraph2.isConnected());
            System.out.println("A path from 0 to 4 is " + myGraph2.getShortestPath(0, 4));
            System.out.println("hasCycle() returns " + myGraph2.hasCycle());
            System.out.println("printEdges() displays: ");
            myGraph2.printEdges();
            System.out.println();

            MyCustomGraph<Integer> myGraph3 = myGraph.readFile("test3.txt");
            
            System.out.println("Graph of text3.txt");
            System.out.println("is complete => " + myGraph3.isComplete());
            System.out.println("is connected => " + myGraph3.isConnected());
            System.out.println("The shortest path from 0 to 4 is " + myGraph3.getShortestPath(0, 4));
            System.out.println("The shortest path from 5 to 4 is " + myGraph3.getShortestPath(5, 4));
            System.out.println("hasCyclic() returns " + myGraph3.hasCycle());

        } catch (Exception e) {
            System.out.println("Oops, something went wrong.");
            e.printStackTrace();
        }
    }
}

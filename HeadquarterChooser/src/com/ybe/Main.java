package com.ybe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<City> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {

        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter The Graph Name : ");

        String graphPath = new java.io.File(".").getCanonicalPath() + "\\src\\com\\ybe\\graphs\\" + scanner.nextLine();

        File graphFile = new File(graphPath);

        int numberOfEdges;
        String optimalCity = null;

        try {
            scanner = new Scanner(graphFile);

            numberOfEdges = scanner.nextInt();

            ArrayList<Integer> entries = new ArrayList<>();
            ArrayList<Integer> vertices = new ArrayList<>();

            while (scanner.hasNextInt())    {
                entries.add(scanner.nextInt());
            }
            System.out.println(entries);

            for (int i = 0; i < entries.size(); i++) {
                if (i % 3 == 0 || i%3 == 1) {
                    if (!vertices.contains(entries.get(i))) {
                        vertices.add(entries.get(i));
                    }
                }
            }
            System.out.println(vertices);
            for (int i = 0; i < vertices.size(); i++) {
                int j = i+1;
                City location = new City("City_" + j, "City_" + j);
                nodes.add(location);
                System.out.println(location.toString());
            }
            int edgeCounter = 0;

            for (int i = 0; i < entries.size(); i=i+3)  {
                addLine("Edge_" + edgeCounter, entries.get(i), entries.get(i+1),entries.get(i+2));
                System.out.println("\nEdges: " + edges.get(edges.size()-1));
                edgeCounter++;
            }

            Graph graph = new Graph(nodes, edges);
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);

            for (int i = 0; i < nodes.size(); i++) {
                dijkstraAlgorithm.execute(nodes.get(i));
                for (int j = 0; j < nodes.size(); j++) {
                    LinkedList<City> path = dijkstraAlgorithm.getPath(nodes.get(nodes.size()-1));
                    System.out.println("\nPath of " + nodes.get(i).toString() + " to " + nodes.get(nodes.size()-1).toString() + ": " + path);
                }
            }

            for (Edge edge : edges) {
                if (edge.getSource().equals(nodes.get(4)) && edge.getDestination().equals(nodes.get(3)))    {
                    System.out.println("Destination of the lane " + nodes.get(3).toString()+ " to " + nodes.get(4).toString() + " is graph1.txt" + edge.getWeight());
                }
            }

        }catch (FileNotFoundException e)    {
            e.printStackTrace();
        }
    }

    private static void addLine(String laneId, int sourceLocNo, int destLocNo, int distance) {
        Edge edge = new Edge(laneId, nodes.get(sourceLocNo-1), nodes.get(destLocNo-1), distance);
        edges.add(edge);
    }
}

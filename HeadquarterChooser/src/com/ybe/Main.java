package com.ybe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static List<City> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {

        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter The Graph Name : ");

        String pathOfFile = new java.io.File(".").getCanonicalPath();

        String graphPath = new java.io.File(".").getCanonicalPath() + "\\src\\com\\ybe\\graphs\\" + scanner.nextLine();


        try (Stream<Path> filePathStream=Files.walk(Paths.get(pathOfFile))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);
                }
            });
        }

        File graphFile = new File(graphPath);

        String optimalCity = null;
        int totalOfDistances = Integer.MAX_VALUE;

        try {
            scanner = new Scanner(graphFile);

            scanner.nextInt();

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
                edgeCounter++;
            }

            for (Edge edge1 : edges) {
                System.out.println("Edges:" + edge1);
            }

            Graph graph = new Graph(nodes, edges);
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);

            for (Edge edge : edges) {
                if (edge.getSource().equals(nodes.get(4)) && edge.getDestination().equals(nodes.get(3)))    {
                    System.out.println("Destination of the lane " + nodes.get(4).toString()+ " to " + nodes.get(3).toString() + " is " + edge.getWeight());
                }
            }

            for (int i = 0; i < nodes.size(); i++) {
                int totalEdgeWeights = 0;
                for (int j = 0; j < nodes.size(); j++) {
                    dijkstraAlgorithm.execute(nodes.get(i));
                    LinkedList<City> path = dijkstraAlgorithm.getPath(nodes.get(j));
                    System.out.println("\nPath of " + nodes.get(i).toString() + " to " + nodes.get(j).toString() + ": " + path);
                    if (i == j) System.out.println("No lane has been found. Destination is 0");
                    else {
                        for (int k = 0; k<path.size()-1; k++)   {
                            for (Edge edge : edges) {

                                if (edge.getSource().equals(path.get(k)) && edge.getDestination().equals(path.get(k + 1)))  {
                                    System.out.println("Destination of the lane " + path.get(k).toString() + " to " + path.get(k+1) + " is " + edge.getWeight());
                                    totalEdgeWeights+=edge.getWeight();
                                }
                            }
                        }
                    }
                }
                if (totalOfDistances>totalEdgeWeights)  {
                    totalOfDistances = totalEdgeWeights;
                    optimalCity = nodes.get(i).toString();
                }
            }
            System.out.println("\nThe Optimal City to install Headquarters is " + optimalCity);
        }catch (FileNotFoundException e)    {
            e.printStackTrace();
        }
    }

    private static void addLine(String laneId, int sourceLocNo, int destLocNo, int distance) {
        Edge edge = new Edge(laneId, nodes.get(sourceLocNo-1), nodes.get(destLocNo-1), distance);
        Edge edgeTurnBack = new Edge(laneId, nodes.get(destLocNo-1), nodes.get(sourceLocNo-1), distance);
        edges.add(edge);
        edges.add(edgeTurnBack);
    }
}

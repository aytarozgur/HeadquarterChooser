package com.ybe;

/**
 * Created by yunusbora on 6.05.2017.
 */

import java.util.List;

/**
 * @author yunusbora
 *
 */
public class Graph {
    private final List<City> cities;
    private static List<Edge> edges;

    public Graph(List<City> cities, List<Edge> edges) {
        this.cities = cities;
        this.edges = edges;
    }

    /**
     * @return the edges
     */
    public static List<Edge> getEdges() {
        return edges;
    }

    /**
     * @param edges the edges to set
     */
    public static void setEdges(List<Edge> edges) {
        Graph.edges = edges;
    }

    /**
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }
}

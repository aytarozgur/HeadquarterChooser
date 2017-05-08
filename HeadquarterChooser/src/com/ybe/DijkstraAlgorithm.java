package com.ybe;

/**
 * Created by yunusbora on 6.05.2017.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
    private List<City> cities;
    private List<Edge> edges;
    private Set<City> settledCities, unSettledCities;
    private Map<City, City> predecessors;
    private Map<City, Integer> distances;

    public DijkstraAlgorithm(Graph graph) {

        this.cities = new ArrayList<City>(graph.getCities());
        this.edges = new ArrayList<Edge>(Graph.getEdges());
    }

    public void execute	(City source) {
        settledCities = new HashSet<>();
        unSettledCities = new HashSet<>();
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        distances.put(source, 0);
        unSettledCities.add(source);
        while (unSettledCities.size() > 0) {
            City node = getMinimum(unSettledCities);
            settledCities.add(node);
            unSettledCities.remove(node);
            findMinimalDistances(node);
        }

    }

    private void findMinimalDistances(City node) {
        List<City> adjacentCities = getNeighbors(node);
        for (City target : adjacentCities) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distances.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledCities.add(target);
            }
        }
    }

    private int getDistance(City node, City target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("That Should Not Happen!");
    }

    private int getShortestDistance(City destination) {
        Integer d = distances.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else return d;
    }

    private List<City> getNeighbors(City node) {
        List<City> neighbors = new ArrayList<>();
        for (Edge edge : edges)
            if ((edge.getSource().equals(node))&& !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        return neighbors;
    }

    private boolean isSettled(City city) {
        return settledCities.contains(city);
    }

    private City getMinimum(Set<City> cities) {
        City minimum = null;
        for (City city : cities) {
            if (minimum == null) {
                minimum = city;
            } else	{
                if (getShortestDistance(city) < getShortestDistance(minimum)) {
                    minimum = city;
                }
            }
        }
        return minimum;    }

    /**********
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     **********/

    public LinkedList<City> getPath(City target) {
        LinkedList<City> path = new LinkedList<>();
        City step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}

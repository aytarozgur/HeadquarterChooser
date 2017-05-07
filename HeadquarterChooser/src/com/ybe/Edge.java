package com.ybe;

/**
 * Created by yunusbora on 6.05.2017.
 */
public class Edge {
    /******
     * This class to initializing the edges between Cities.
     ******/
    private final String id;
    private final City source, destination;
    private final int weight;

    public Edge(String id, City source, City destination, int weight)	{
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @return the source
     */
    public City getSource() {
        return source;
    }


    /**
     * @return the destination
     */
    public City getDestination() {
        return destination;
    }


    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString()	{
        return source + " " + destination;
    }

}
package com.ybe;

/**
 * Created by yunusbora on 6.05.2017.
 */

/**********
 *
 * That Class to initialize a City.
 *
 ***********/

public class City {

    /*********
     * Each City has a id and a name.
     ********/

    final private String id, name;

    /********
     * Initializing a City.
     **********/

    public City(String id, String name)	{
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString()    {
        return getName();
    }
}

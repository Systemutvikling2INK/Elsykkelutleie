package no.ntnu.elsykkelutleie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knut on 15.09.2016.
 */
public class Parking {

    private int id;
    private String name;
    //private Map<Integer, Bike> bikes = new HashMap<Integer, Bike>();
    private ArrayList<Bike> bikes = new ArrayList<Bike>();

    //Tom for at den kan kjøre wtf ;P
    public Parking() {

    }

    public Parking(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Collection<Bike> getBikes() {
        return bikes.values();
    }

    public void setBikes(HashMap<Integer, Bike> bikes) {
        this.bikes = bikes;
    }*/

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }

    public Bike getBike(int id) {
        if (bikes.get(id) != null) {
            return bikes.get(id);
        } else {
            return null;
        }
    }

    public void addBike(Bike bike) { //TODO:boolean?
        //bikes.put(bike.getId(), bike);
        bikes.add(bike);
    }
}

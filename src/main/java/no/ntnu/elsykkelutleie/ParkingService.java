package no.ntnu.elsykkelutleie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knut on 15.09.2016.
 */

@Path("/parking")
public class ParkingService {

    private static Map<Integer, Parking> parkingMap = new HashMap<Integer, Parking>() {{
        put(1, new Parking(1, "Kalvskinnet"));
        put(2, new Parking(2, "Gløshaugen"));
        put(3, new Parking(3, "Dragvoll"));
        put(4, new Parking(4, "Midtbyen"));
        put(5, new Parking(5, "Solsiden"));
    }};

    public ParkingService() {
        /*bicycles.put(1, new Bike(1, 0.56, 3));
        bicycles.put(2, new Bike(2, 0.57, 1));
        bicycles.put(3, new Bike(3, 0.51, 2));*/
    }

    /*@GET
    public Collection<Bike> getBicycles() {
        return bicycles.values();
    }*/
    @GET
    public Collection<Parking> getParking() {
        return parkingMap.values();
    }

    //TODO: en annen metode som går til: @Path("{id}")
    @GET
    @Path("{id}/bikes")
    public Collection<Bike> getBikesInParking(@PathParam("id") int id) {
        if (parkingMap.get(id) != null) {
            return parkingMap.get(id).getBikes();
        } else {
            throw new javax.ws.rs.NotFoundException("");
        }
    }

    @GET
    @Path("{id}/bikes/{bikeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bike getBike(@PathParam("id") int id, @PathParam("bikeId") int bikeId) {
        if (parkingMap.get(id) != null && parkingMap.get(id).getBike(bikeId) != null) {
            return parkingMap.get(id).getBike(bikeId);
        } else {
            throw new javax.ws.rs.NotFoundException("Something is missing");
        }
        /*
        if (bicycles.get(id) != null) {
            return bicycles.get(id);
        } else {
            throw new javax.ws.rs.NotFoundException("There is no bike with the specific id.");
        }*/
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addParking(Parking parking) {
        parkingMap.put(parking.getId(), parking);
    }
    /*
    @POST
    @Path()
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBike(Bike bike) {
        bicycles.put(bike.getId(), bike);
    }*/

    @PUT
    @Path("/{id}/bikes/{bikeId}")
    public void reserveBike(@PathParam("id") int id, @PathParam("bikeId") int bikeId) {
        parkingMap.get(id).getBike(bikeId).setInUse(true);
    }

    @DELETE
    @Path("/{id}")
    public void deleteParking(@PathParam("id") int id) {
        parkingMap.remove(id);
    }

    @POST
    @Path("/{id}/bikes/{bikeId}") //TODO: sjekk om en sykkel med samme id allerede eksisterer
    public void addBikeToParking(@PathParam("id") int id, Bike bike) {/*@PathParam("id") int id, @PathParam("bikeId") int bikeId, double batteryPercentage) {*/
        //parkingMap.get(id).addBike(new Bike(bikeId, batteryPercentage));
        parkingMap.get(id).addBike(bike);
    }

    @POST
    @Path("/{id}/bikes/{bikeId}/reserver")
    public String reserverBike(@PathParam("id") int id, @PathParam("bikeId") int bikeId) {
        if (parkingMap.get(id) != null && parkingMap.get(id).getBike(bikeId) != null) {
            Bike biken = parkingMap.get(id).getBike(bikeId);
            if (!biken.getInUse()) {
                parkingMap.get(id).getBike(bikeId).reserveBike();
                return parkingMap.get(id).getBike(bikeId).getReservationCode();
            } else {
                throw new javax.ws.rs.NotFoundException("Bike is already in use :(");
            }

        }
        return "Error, something wrong happened";
    }

    @POST
    @Path("/{id}/bikes/{bikeId}/hent/{henteKode}")
    public void hentSykkel(@PathParam("id") int id, @PathParam("bikeId") int bikeId, @PathParam("henteKode") String henteKode) {
        if (parkingMap.get(id) != null && parkingMap.get(id).getBike(bikeId) != null) { //TODO:metode for å sjekke det i denne if setningen
            Bike biken = parkingMap.get(id).getBike(bikeId);
            if (!biken.isInUse()) { //TODO: inuse, getinuse
                biken.receiveBike(henteKode); //TODO:// FIXME: 06.10.2016 boolean
            }
        }
    }

    @POST
    @Path("/{id}/bikes/{bikeId}/levertilbake")
    public void leverTilbake(@PathParam("id") int id, @PathParam("bikeId") int bikeId) {
        if (parkingMap.get(id) != null && parkingMap.get(id).getBike(bikeId) != null) {
            Bike biken = parkingMap.get(id).getBike(bikeId);
            biken.deliverBackBike();
        }
    }

    @GET
    @Path("/{id}/bikes/{bikeId}/kode")
    public String getDeliveryCode(@PathParam("id") int id, @PathParam("bikeId") int bikeId) {
        if (parkingMap.get(id) != null && parkingMap.get(id).getBike(bikeId) != null) {
            Bike biken = parkingMap.get(id).getBike(bikeId);

            System.out.println(biken.isInUse());
            System.out.println(biken.getReservationCode());

            if (biken.getBooking() != null) {
                return biken.getReservationCode();
            }
        }
        return "Sykkelen er ikke leid ut.";
    }

    /*@DELETE
    @Path("/{id}")
    public void deleteBike(@PathParam("id") int id) {
        bicycles.remove(id);
        /*if (bicycles.get(id) != null) {
            bicycles.remove(id);
        } else {
            throw new javax.ws.rs.NotFoundException("There is no bike to remove.");
        }
    }*/

    /*@PUT
    @Path("/{id}/location/{location}")
    public void changeLocation(@PathParam("id") int id, @PathParam("location") int parking) {
        //bicycles.get(id).setParking(parking);
        Bike bike = new Bike(id, bicycles.get(id).getBatteryPercentage(), parking);
        bicycles.put(id, bike);
    }

    @PUT
    @Path("/{id}/battery/{batteryp}")
    public void changeBatteryPercentage(@PathParam("id") int id, @PathParam("batteryp") int batteryPercentage) {
        bicycles.get(id).setBatteryPercentage(batteryPercentage);
    }

    @GET//THIS METHOD IS JUST A TEST
    @Path("/{id}/location/")
    public int getLocation(@PathParam("id") int id) {
        return bicycles.get(id).getParking();
    }*/
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demandsimulator;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class Vehicle {
    private String plate;
    private String vehicleType;

    public Vehicle() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }        
}

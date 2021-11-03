/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.Map;
import ooc.enums.Make;
import ooc.enums.Month;

public class Car implements CarInterface {
    private Make make;
    private double rate;
    private Map<Month, boolean[]> availability;
    private final int id;
    
    public Car(){
        this.id = -1;
    }
    
    public Car(int id){
        this.id = id;
    }
    
    @Override
    public Map<Month, boolean[]> createAvailability() {
        for(Month month : Month.values()){
            boolean[] temp = new boolean[month.getNumberOfDays()];

            for(int j = 0; j < month.getNumberOfDays(); j++){
                temp[j] = true;
            } 

            availability.put(month, temp);
        }

        this.setAvailability(availability);
        
        return this.availability;
    }

    @Override
    public Make getMake() {
        return this.make;
    }

    @Override
    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public double getRate() {
        return this.rate;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public Map<Month, boolean[]> getAvailability() {
        return this.availability;
    }

    @Override
    public void setAvailability(Map<Month, boolean[]> availability) {
        this.availability = availability;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isAvailable(Month month, int day) {
        return this.availability.get(month)[day];
    }

    @Override
    public boolean book(Month month, int day) {
        if(this.availability.get(month)[day]){
            this.availability.get(month)[day] = false;
            
            return true;
        }
        
        return false;
    }
    
}

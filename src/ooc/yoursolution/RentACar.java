
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.util.List;
import ooc.enums.Make;
import ooc.enums.Month;

public class RentACar implements RentACarInterface {
    private List<CarInterface> cars;
    private String name;
    
    
    @Override
    public List<CarInterface> getCars() {
        return this.cars;
    }

    @Override
    public void setCars(List<CarInterface> cars) {
        this.cars = cars;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean checkAvailability(Month month, int day, Make make, int lengthOfRent) {
        boolean should_continue = true;
        
        for(CarInterface car : this.getCars()){
            if(car.getMake().equals(make)){
                should_continue = false;
                
                if(car.isAvailable(month, day)){
                    
                    for(int i = 1; i < lengthOfRent; i++){
                        if(!car.isAvailable(month, day + i)){
                            return false;
                        }
                    }
                    
                    return true;
                }
            }
            
            if(!car.getMake().equals(make) && !should_continue){
                break;
            }
        }
        
        return false;
    }

    @Override
    public int getCarAvailable(Month month, int day, Make make, int lengthOfRent) {

        if(this.checkAvailability(month, day, make, lengthOfRent)){
            for(CarInterface car : this.getCars()){
                if(car.getMake().equals(make)){
                    if(car.isAvailable(month, day)){
                        return car.getId();
                    }
                }
            }
        }
        
        return -1;
    }

    @Override
    public boolean bookCar(Month month, int day, Make make, int lengthOfRent) {
        int car_index = this.getCarAvailable(month, day, make, lengthOfRent);
        
        if(car_index == -1){
            return false;
        }
        
        CarInterface car = this.getCars().get(car_index);
        
        for(int i = 0; i < lengthOfRent; i++){
            car.book(month, day + i);
        }
        
        return true;
    }

    @Override
    public int getNumberOfCars() {
        return this.cars.size();
    }
    
}

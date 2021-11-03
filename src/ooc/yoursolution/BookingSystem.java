/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ooc.yoursolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ooc.enums.Make;
import ooc.enums.Month;

public class BookingSystem implements BookingSystemInterface {

    @Override
    public RentACarInterface setupRentACar(BufferedReader in) throws IOException {
       String line;
       int line_number = 1;
       
       RentACar rentACar = new RentACar();
       List<CarInterface> car_list = new ArrayList<>();
        
       while(in.ready()){
           line = in.readLine();
           
           if(line.trim().equals("")){
               continue;
           }
           
           if(line_number == 1){
               rentACar.setName(line);
           } else {               
               String[] cars_array = line.split(":");
               
               if(cars_array.length != 3){
                   throw new IOException("Error on line " + line_number + " '" + line + "'");
               }
               
               for(int i = 0; i < Integer.parseInt(cars_array[2]); ++i){
                    // Create the car
                    Car car = new Car(i);

                    // Set the make                
                    switch(cars_array[0].toLowerCase()){
                        case "bmw":
                            car.setMake(Make.BMW);
                            break;

                        case "toyota":
                            car.setMake(Make.TOYOTA);
                            break;

                        case "ford":
                            car.setMake(Make.FORD);
                            break;

                        case "fiat":
                            car.setMake(Make.FIAT);
                            break;

                        case "chevrolet":
                            car.setMake(Make.CHEVROLET);
                            break;

                        default:
                            car.setMake(null);
                            break;
                    }

                    // Set the price per day
                    car.setRate(Double.parseDouble(cars_array[1]));

                    // Set availability
                    HashMap<Month, boolean[]> availability = new HashMap<>();

                    for(Month month : Month.values()){
                        boolean[] temp = new boolean[month.getNumberOfDays()];

                        for(int j = 0; j < month.getNumberOfDays(); j++){
                            temp[j] = true;
                        } 

                        availability.put(month, temp);
                    }

                    car.setAvailability(availability);

                    // Add the car to the car list
                    car_list.add(car);
               }
           }
           
           ++line_number;
       }
       
       in.close();
       
       rentACar.setCars(car_list);
       
       return rentACar;
    }
    
}

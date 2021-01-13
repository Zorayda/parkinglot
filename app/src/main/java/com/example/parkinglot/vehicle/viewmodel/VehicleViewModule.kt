package com.example.parkinglot.vehicle.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle

class VehicleViewModule@ViewModelInject constructor(var services: ParkingLotService): ViewModel(){
    private var vehicles = MutableLiveData<ArrayList<Vehicle>>()
    var message = MutableLiveData<String>()

    fun getVehicles() {
        vehicles = services.getAllVehicles()
    }

    fun getOnlyVehiclesEnteredParkingLot() {
        vehicles = services.getOnlyVehiclesEnteredParkingLot()
    }

    fun getVehiclesLiveData(): MutableLiveData<ArrayList<Vehicle>> {
        return vehicles
    }

    fun insideNewVehicle(vehicle: Vehicle){
        vehicle.state = Vehicle.INSIDE_PARKING_LOT

        if (vehicle is Motorcycle) {
            services.enterANewMotorcycle(vehicle)
        } else if (vehicle is Car) {
            services.enterANewCar(vehicle)
        }
    }
    fun exitToAVehicle(vehicle: Vehicle): Int{
        return services.exitToAVehicle(vehicle)
    }

    fun getCostToPay(vehicle: Vehicle): Int {
        return services.getCostToPay(vehicle)
    }
}
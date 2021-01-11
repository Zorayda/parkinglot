package com.example.parkinglot.vehicle.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.service.VehicleService

class VehicleViewModule@ViewModelInject constructor(var services: VehicleService): ViewModel(){
    private var vehicles = MutableLiveData<ArrayList<Vehicle>>()

    init {
        getVehicles()
    }

    private fun getVehicles() {
        vehicles = services.getAllVehicles()
    }

    fun getVehiclesLiveData(): MutableLiveData<ArrayList<Vehicle>> {
        return vehicles
    }

    fun getCostToPay(vehicle: Vehicle): Int {
        return services.getCostToPay(vehicle)
    }
}
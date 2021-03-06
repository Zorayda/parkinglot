package com.example.parkinglot.vehicle.viewmodel

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.parkinglot.service.ParkingLotService
import com.example.domain.vehicle.aggregate.Car
import com.example.domain.vehicle.aggregate.Motorcycle
import com.example.domain.vehicle.aggregate.Vehicle
import com.example.domain.vehicle.aggregate.Vehicle.Companion.OUTSIDE_PARKING_LOT
import com.example.domain.vehicle.entity.LicensePlate
import com.example.parkinglot.generic.Utils

class AddVehicleViewModel@ViewModelInject constructor(var services: ParkingLotService) : ViewModel() {
    private var vehicles = MutableLiveData<ArrayList<Vehicle>>()
    var licensePlateComplete = MutableLiveData<Float>()
    var message = MutableLiveData<String>()
    var availableCylinderCapacity = MutableLiveData<Int>()

    fun saveVehicle(licensePlate: String, licensePlateCity:String, carType: Boolean, motorcycleType: Boolean, cylinderCapacity: String){
        if (!Utils.validateString(licensePlate)){
            //Mensaje
            //message.value = context.getString(R.string.se_necesita_la_placa)
            message.value = "se_necesita_la_placa"
            Log.i("TEST", "Placa vacía")
        } else if (carType){
            //Guardar carro
            Log.i("TEST", "Carro")
            enterANewCar(Car(LicensePlate(Utils.convertTextToUpperCase(licensePlate), licensePlateCity), OUTSIDE_PARKING_LOT, ""))
        } else if (motorcycleType){
            //Guardar moto
            Log.i("TEST", "Moto")
            if (!Utils.validateString(cylinderCapacity)){
                message.value = "se_necesita_el_cilindraje"
            } else {
                enterANewMotorcycle(Motorcycle(LicensePlate(Utils.convertTextToUpperCase(licensePlate), licensePlateCity), OUTSIDE_PARKING_LOT, "", cylinderCapacity.toInt()))
            }
        }
    }

    fun validateLicensePlateComplete(licensePlate: String){
        if (Utils.validateString(licensePlate)){
            licensePlateComplete.value = 1F
        } else {
            licensePlateComplete.value = 0.5F
        }
    }

    private fun enterANewMotorcycle(motorcycle: Motorcycle) {
        services.saveMotorcycle(motorcycle)
        vehicles.value?.add(motorcycle)
        message.value = "Moto OK"
    }

    private fun enterANewCar(car: Car) {
        services.saveCar(car)
        vehicles.value?.add(car)
        message.value = "Carro OK"
    }

    fun validateMotorcycleType(isMotorcycle: Boolean){
        if (isMotorcycle) {
            availableCylinderCapacity.value = View.VISIBLE
        } else {
            availableCylinderCapacity.value = View.GONE
        }
    }
}
package com.example.domain.vehicle.aggregate

import com.example.domain.vehicle.entity.LicensePlate

abstract class Vehicle {
    // Para el estado del vehiculo
    companion object {
        val INSIDE_PARKING_LOT = "inside_the_parking_lot"
        val OUTSIDE_PARKING_LOT = "outside_the_parking_lot"
    }

    abstract val plateLicensePlate: LicensePlate
    abstract var state: String
    abstract var dateOfAdmission: String
}
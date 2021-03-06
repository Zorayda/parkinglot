package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
class CarEntity (
    //  @ColumnInfo nombre de la columna de la tabla en la base de datos
    @ColumnInfo(name = "licensePlate")
    var licensePlate: String,

    @ColumnInfo(name = "licensePlateCity")
    var licensePlateCity: String,

    @ColumnInfo(name = "state")
    var state: String,

    @ColumnInfo(name = "dateadmission")
    var dateOfAdmission: String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
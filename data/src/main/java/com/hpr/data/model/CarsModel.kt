package com.hpr.data.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarsModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "carImageUrl") val carImageUrl: String,
    @Json(name = "color") val color: String,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "licensePlate") val licensePlate: String,
    @Json(name = "modelName") val modelName: String,
    @Json(ignore = true) var carImageBitmap: Bitmap? = null,
) {
    fun toEntity() = CarsEntity(
        id,
        name,
        carImageUrl,
        color,
        latitude,
        longitude,
        licensePlate,
        modelName
    )
}

@Entity(tableName = "CarsEntity")
data class CarsEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,


    @ColumnInfo(name = "carImageUrl")
    val carImageUrl: String,

    @ColumnInfo(name = "color")
    val color: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "licensePlate")
    val licensePlate: String,

    @ColumnInfo(name = "modelName")
    val modelName: String,
) {
    fun toModel() = CarsModel(
        id, name, carImageUrl, color, latitude, longitude, licensePlate, modelName,
    )
}
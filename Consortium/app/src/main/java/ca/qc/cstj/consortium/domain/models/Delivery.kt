package ca.qc.cstj.consortium.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Deliveries")
data class Delivery(var zuscum: Float = 0.0f,
                    var wusnyx: Float = 0.0f,
                    var jasmalt: Float = 0.0f,
                    var iaspyx: Float = 0.0f,
                    var blierium: Float = 0.0f)
{
    @PrimaryKey(autoGenerate = true) var idDelivery: Int = 0
}

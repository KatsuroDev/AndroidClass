package ca.qc.cstj.consortium.data.repositories

import androidx.room.*
import ca.qc.cstj.consortium.core.Constants
import ca.qc.cstj.consortium.domain.models.Delivery
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

@Dao
interface DeliveryRepository {
    @Query("SELECT * FROM Deliveries")
    fun retrieveAll(): Flow<List<Delivery>>
    @Insert
    suspend fun create(delivery: Delivery)
    @Query("DELETE FROM Deliveries")
    suspend fun deleteAll()

    fun newDelivery() : Delivery {
        return Delivery(
            Random.nextDouble(0.0, Constants.randomElementAmount).toFloat(),
            Random.nextDouble(0.0, Constants.randomElementAmount).toFloat(),
            Random.nextDouble(0.0, Constants.randomElementAmount).toFloat(),
            Random.nextDouble(0.0, Constants.randomElementAmount).toFloat(),
            Random.nextDouble(0.0, Constants.randomElementAmount).toFloat()
        )
    }
}
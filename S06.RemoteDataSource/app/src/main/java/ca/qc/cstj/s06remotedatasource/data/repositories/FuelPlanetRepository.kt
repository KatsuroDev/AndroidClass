package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.core.LoadingResource
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasource.FuelPlanetDatasource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FuelPlanetRepository {

    private val planetDatasource = FuelPlanetDatasource()

    // Retrouve les planètes sans mise à jour
    suspend fun retrieveAll(): Resource<List<Planet>> {
        return try{
            Resource.Success(planetDatasource.retrieveAll())
        } catch (ex: Exception) {
            Resource.Error(ex, "Server error")
        }
    }

    // Retrouve les planètes avec une mise à jour régulière
    suspend fun retrieveAllWithUpdate() : Flow<Resource<List<Planet>>> {
        return flow {
            while(true){
                try{
                    emit(Resource.Success(planetDatasource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(Resource.Error(ex, "Server error"))
                }
                delay(Constants.RefreshRates.PLANETS_REFRESH_RATE)
            }
        }
    }

    suspend fun retrieveAllWithLoading() : Flow<LoadingResource<List<Planet>>> {
        return flow {
            while(true){
                try{
                    emit(LoadingResource.Loading())
                    delay(5000)
                    emit(LoadingResource.Success(planetDatasource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, "Server error"))
                }
                delay(Constants.RefreshRates.PLANETS_REFRESH_RATE)
            }
        }
    }
}
package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasource.FuelPlanetDatasource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet

class FuelPlanetRepository {

    private val planetDatasource = FuelPlanetDatasource()

    suspend fun retrieveAll(): Resource<List<Planet>> {
        return try{
            Resource.Success(planetDatasource.retrieveAll())
        } catch (ex: Exception) {
            Resource.Error(ex, "Server error")
        }
    }
}
package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MemoryPlanetRepository {

    fun retrieveAll(): Flow<List<Planet>> {

        val numberToGenerate = Random.nextInt(0, 21)
        val planets = mutableListOf<Planet>()

        for (i in 0..numberToGenerate) {
            planets.add(newPlanet())
        }

        return flow {
            emit(planets)
        }

    }

    private fun newPlanet() : Planet {
        val planetNumber =  Random.nextInt(1, 50)
        val planetImage = Random.nextInt(1, 7)
        val temperature = Random.nextDouble(0.0,60.0)
        return Planet("planet$planetNumber", planetImage.toString(), temperature)
    }
}
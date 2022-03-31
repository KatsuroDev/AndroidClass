package ca.qc.cstj.consortium.domain.models

import ca.qc.cstj.consortium.core.Constants
import kotlin.random.Random

data class Trader(val name: String,
                  var zuscum: Float = Constants.defaultElementAmount,
                  var wusnyx: Float = Constants.defaultElementAmount,
                  var jasmalt: Float = Constants.defaultElementAmount,
                  var iaspyx: Float = Constants.defaultElementAmount,
                  var blierium: Float = Constants.defaultElementAmount)
{
    fun load()
    {
        zuscum += Random.nextDouble(50.0, 201.0).toFloat()
        wusnyx += Random.nextDouble(50.0, 201.0).toFloat()
        jasmalt += Random.nextDouble(50.0, 201.0).toFloat()
        iaspyx += Random.nextDouble(50.0, 201.0).toFloat()
        blierium += Random.nextDouble(50.0, 201.0).toFloat()
    }
}
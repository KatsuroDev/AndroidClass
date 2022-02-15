package ca.qc.cstj.s02constraintlayout.domain.models

import kotlin.random.Random

data class Pilot(var name: String, var life: Int, var cube: Int = 0) {

    private var _experience: Int = 0
    var shield: Int = 5
    var energy: Int = 7

    val level: Int  get() = _experience / 10

    fun fly(revolutions:Int, isTraining:Boolean)
    {
        if(!isTraining)
        {
            _experience += revolutions * Random.nextInt(1, 6)
            life -= Random.nextInt(0, 2)
            shield -= Random.nextInt(0, 6)
            energy--
            cube += Random.nextInt(0, revolutions + 1)
        }
    }

    fun canFly() : Boolean
    {
        return life > 0 && energy > 0 && shield > 0
    }

    fun recharge()
    {
        //TODO: Add microtransaction
        if(energy <= 0)
            energy = 7
        if(life <= 0)
            life = 10
        if(shield <= 0)
            shield = 5
    }
}
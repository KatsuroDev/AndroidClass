package ca.qc.cstj.consortium.core

object Formatter {
    fun toDecimalFormat(number:Double) : String {
        return "%,.2f".format(number)
    }
}
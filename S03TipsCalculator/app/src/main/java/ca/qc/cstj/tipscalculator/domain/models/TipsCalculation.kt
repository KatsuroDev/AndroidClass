package ca.qc.cstj.tipscalculator.domain.models

import ca.qc.cstj.tipscalculator.core.Constants

data class TipsCalculation(private val subtotal: Double, private val tipsPercent: Float) {

    val tipsAmount = subtotal * tipsPercent
    val TPS = subtotal * Constants.TPS
    val TVQ = subtotal * Constants.TVQ
    val total = subtotal + TPS + TVQ + tipsAmount
}
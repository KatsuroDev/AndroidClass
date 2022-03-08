package ca.qc.cstj.s05localdatasource.core

import ca.qc.cstj.s05localdatasource.domain.models.Contact

object Constants {
    val PREPOPULATE_CONTACTS = listOf(
        Contact("Yannick", "Charron", true),
        Contact("Robert", "Turenne", true),
        Contact("Karine", "Moreau", false),
        Contact("Joël", "Beaudet", false)
    )
}
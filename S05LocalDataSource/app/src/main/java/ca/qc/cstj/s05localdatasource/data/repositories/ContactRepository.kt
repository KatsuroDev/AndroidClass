package ca.qc.cstj.s05localdatasource.data.repositories

import ca.qc.cstj.s05localdatasource.domain.models.Contact

class ContactRepository {

    fun retrieveAll(numContacts: Int): List<Contact> {

       val contacts = mutableListOf<Contact>()
       for (i in 1..numContacts) {
            contacts.add(Contact("FirstName $i", "LastName $i", i % 2 == 0))
       }

       return contacts
   }


}
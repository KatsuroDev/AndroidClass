package ca.qc.cstj.s05localdatasource.domain.models

data class User(var firstName:String, var lastName:String, var isOnline:Boolean) {
    val fullName: String get() = "$firstName $lastName"
}
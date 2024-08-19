package com.example.gcsmadactivivity.models

data class Contact(
    val name: String,
    val id: Int,
    val phone: String? = null,
    val pictureUri: String? = null
) {

    constructor(name: String, id: Int, phone: String) : this(
        name,
        id,
        null,
        null
    ) // Named constructor


//    companion object {
//        val contacts = (1..100).map {
//            Contact("Name0000 $it", it)
//        }
//    }

}

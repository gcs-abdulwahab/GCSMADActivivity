package com.example.gcsmadactivivity.models

data class Contact(val name: String, val id: Int) {

    companion object {
        val contacts = (1..100 ).map {
            Contact("Name $it", it )
        }
    }

}

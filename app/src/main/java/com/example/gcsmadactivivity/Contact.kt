package com.example.gcsmadactivivity

data class Contact(val id: Int, val name: String){
    companion object {
        val contacts = (1..100).map {
            Contact(it, "Name $it")
        }
    }
}

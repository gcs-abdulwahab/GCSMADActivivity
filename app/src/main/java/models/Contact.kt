package models

import kotlin.random.Random

data class Contact(val id: Int, val name: String, val imageUrl: String = "", var balance: Int = 0) {
    companion object {
        val contacts = (1..10).map {
            Contact(
                it,
                "Name $it",
                balance = Random.nextInt(from = 10, 1000),
                imageUrl = "https://picsum.photos/200/300"
            )
        }

    }
}

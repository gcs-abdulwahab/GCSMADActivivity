package models

data class Contact(val id: Int, val name: String, val imageUrl: String) {
    companion object {
        val contacts = (1..11515).map {
            Contact(
                it,
                "Name $it",
                "https://picsum.photos/200/300"
            )
        }

    }
}

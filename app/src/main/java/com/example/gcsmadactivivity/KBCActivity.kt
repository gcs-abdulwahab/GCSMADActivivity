package com.example.gcsmadactivivity

import Product
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Math.random
import kotlin.random.Random

data class Tabs(val id: Int, val title: String, val imageURL: String, val priority: Double) {
    companion object {
        val tabs = (1..10).map {
            Tabs(
                it,
                "Tab $it",
                "https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg",
                random()
            )
        }
    }

}



class KBCActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            GCSMADActivivityTheme {

                Column {
                    Spacer(modifier = Modifier.size(40.dp))
                  //  ProductList(products)
                }


            }
        }
    }


    private suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        val assetManager: AssetManager = assets

        try {
            assetManager.open("products.json").bufferedReader().use { reader ->
                val jsonString = reader.readText()
                Gson().fromJson(jsonString, Array<Product>::class.java).toList()
            }.also { products ->
                Log.d("TAGA", "getProducts: ${products.size}")
            }
        } catch (e: IOException) {
            // Handle the exception (e.g., log the error, display a message)
            Log.d("TAGA", "getProducts: ${e.message}")
            emptyList()
        }
    }

}

@Composable
private fun ProductList(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(products) { product ->
                Product(product = product)
            }
        }
    )
}


@Composable
fun Product(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(16.dp)
    ) {
        product.title?.let { ShowText(it) }
        ShowText(product.price.toString())
        product.description?.let { ShowText(it) }
        product.category?.let { ShowText(it) }
    }
}

@Composable
fun ShowText(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value,
        modifier = Modifier.padding(4.dp)
    )
}

// create preview for Product.kt
@Preview(showBackground = true)
@Composable
fun ProductPreview() {


    Product(Product(1))
}


@Composable
fun KBCTab(modifier: Modifier = Modifier, tab: Tabs) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(1.dp, Color.Black)

    ) {

        AsyncImage(
            model = tab.imageURL, contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .border(1.dp, Color.Black)
        )
        Text(tab.title)
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play Arrow")


    }
}


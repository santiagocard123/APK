package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "My Products", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(0.dp, 20.dp))
        TextField(
            value = state.productName,
            onValueChange = { viewModel.changeName(it) },
            placeholder = { Text(text = "Name") }
        )
        TextField(
            value = state.productPrice,
            onValueChange = { viewModel.changePrice(it) },
            placeholder = { Text(text = "Price") }
        )
        Button(onClick = { viewModel.handleButton() }) {
            val buttonText: String = if (state.isEditing) "Save Changes" else "Create Product"
            Text(text = buttonText)
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.products) {
                ProductItem(
                    product = it,
                    modifier = Modifier.fillMaxWidth(),
                    onEdit = {
                        viewModel.editProduct(it)
                    },
                    onDelete = {
                        viewModel.deleteProduct(it)
                    }
                )
            }
        }
    }
}

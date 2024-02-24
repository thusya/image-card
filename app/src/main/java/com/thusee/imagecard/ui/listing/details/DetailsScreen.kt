package com.thusee.imagecard.ui.listing.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.thusee.imagecard.R
import com.thusee.imagecard.ui.listing.SharedViewModel
import com.thusee.imagecard.ui.util.getImageUrl
import java.util.Currency
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val product = sharedViewModel.selectedProduct

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.product_details)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.onBackground),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                )
            ) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f),
                        model = product?.let { getImageUrl(it.url) },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.place_holder),
                        fallback = painterResource(id = R.drawable.place_holder),
                        error = painterResource(id = R.drawable.place_holder),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    product?.let {
                        Text(
                            modifier = Modifier,
                            text = it.name,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    product?.salePrice?.let {
                        val currency = Currency.getInstance(it.currency)
                        val currencySymbol = currency.getSymbol(Locale.getDefault())

                        val annotatedText = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(currencySymbol)
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(it.amount)
                            }
                        }
                        Text(
                            modifier = Modifier,
                            text = annotatedText,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
//    DetailsScreen()
}
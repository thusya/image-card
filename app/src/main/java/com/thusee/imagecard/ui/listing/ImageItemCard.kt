package com.thusee.imagecard.ui.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.thusee.imagecard.R
import timber.log.Timber

@Composable
fun ImageItemCard(
    modifier: Modifier = Modifier,
    imageURL: String,
    viewModel: ListingViewModel = hiltViewModel()
) {

    val categoryState = viewModel.categoryState.collectAsState()

    when (val state = categoryState.value) {
        is UIState.Loading -> {}
        is UIState.Success -> {
            Timber.d("Response : ${state.data}")
        }

        is UIState.Error -> {
            Timber.d("Error ${state.exception.message}")
        }

        is UIState.Empty -> {}
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = imageURL,
                    contentDescription = stringResource(id = R.string.image_url),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.hotlap),
                    fallback = painterResource(id = R.drawable.hotlap),
                    error = painterResource(id = R.drawable.hotlap)
                )

                Text(
                    text = "Food name",
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
@Preview
fun ImageItemCardPreview() {
    ImageItemCard(
        imageURL = ""
    )
}
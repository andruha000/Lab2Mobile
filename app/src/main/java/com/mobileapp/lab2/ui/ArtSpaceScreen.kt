package com.mobileapp.lab2.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mobileapp.lab2.R
import com.mobileapp.lab2.data.repository.ArtRepository

@Composable
fun ArtworkWall(
    imageId: Int,
    descriptionId: Int,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small)),
        tonalElevation = dimensionResource(R.dimen.elevation_large),
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = descriptionId),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }

    }
}

@Composable
fun ArtworkDescriptor(
    titleId: Int,
    artistId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.clip_medium)),
        tonalElevation = dimensionResource(R.dimen.elevation_large),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_text)),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(titleId),
                lineHeight = dimensionResource(R.dimen.line_height).value.sp,
                fontSize = dimensionResource(R.dimen.font_size_title).value.sp,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface

            )

            Text(
                text = stringResource(artistId),
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(R.dimen.font_size_text).value.sp,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    isPreviousEnabled: Boolean = true,
    isNextEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NavigationButton(
            onClick = onPreviousClick,
            text = stringResource(R.string.previous_button_name),
            enabled = isPreviousEnabled,
            modifier = Modifier.weight(1f),
            buttonDescription = stringResource(R.string.previous_button_description)
        )

        Spacer(modifier = Modifier.weight(0.2f))

        NavigationButton(
            onClick = onNextClick,
            text = stringResource(R.string.next_button_name),
            enabled = isNextEnabled,
            modifier = Modifier.weight(1f),
            buttonDescription = stringResource(R.string.next_button_description)
        )
    }
}

@Composable
fun NavigationButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonDescription: String
) {
    Button(
        onClick = onClick,
        modifier = modifier.semantics {contentDescription = buttonDescription},
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.clip_medium)),

    ) {
        Text(
            text = text,
            fontSize = dimensionResource(R.dimen.font_size_label).value.sp
        )
    }
}

@Composable
fun ArtSpaceScreen() {

    val configuration = LocalConfiguration.current
    var currentArtworkIndex by rememberSaveable { mutableIntStateOf(0) }
    val artworks = ArtRepository.getArtworks()
    val currentArtwork = artworks[currentArtworkIndex]

    val isPreviousEnabled = currentArtworkIndex > 0
    val isNextEnabled = currentArtworkIndex < artworks.size - 1

    val displayController = @Composable {
        DisplayController(
            onPreviousClick = {
                if (isPreviousEnabled) currentArtworkIndex--
            },
            onNextClick = {
                if (isNextEnabled) currentArtworkIndex++
            },
            isPreviousEnabled = isPreviousEnabled,
            isNextEnabled = isNextEnabled,
            modifier = Modifier.fillMaxWidth()
        )
    }

    val artworkWall = @Composable {
        ArtworkWall(
            imageId = currentArtwork.imageId,
            descriptionId = currentArtwork.descriptionId
        )
    }

    val artworkDescriptor = @Composable {
        ArtworkDescriptor(
            titleId = currentArtwork.titleId,
            artistId = currentArtwork.artistId
        )
    }

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.weight(0.3f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    contentAlignment = Alignment.Center

                ) {
                    artworkWall()
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.3f),
                    contentAlignment = Alignment.Center

                ) {
                    artworkDescriptor()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                contentAlignment = Alignment.Center

            ) {
                displayController()
            }
        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.weight(0.3f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                contentAlignment = Alignment.Center

            ) {
                artworkWall()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .weight(0.3f),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    artworkDescriptor()
                    displayController()
                }

            }
        }
    }
}
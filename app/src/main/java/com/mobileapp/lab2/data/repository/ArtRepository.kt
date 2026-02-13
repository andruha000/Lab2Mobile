package com.mobileapp.lab2.data.repository

import com.mobileapp.lab2.R
import com.mobileapp.lab2.data.model.Artwork

object ArtRepository {
    fun getArtworks(): List<Artwork> = listOf(
        Artwork(
            imageId = R.drawable.artwork1,
            titleId = R.string.artwork1_name,
            artistId = R.string.artwork_artist,
            descriptionId = R.string.artwork1_description
        ),
        Artwork(
            imageId = R.drawable.artwork2,
            titleId = R.string.artwork2_name,
            artistId = R.string.artwork_artist,
            descriptionId = R.string.artwork2_description
        ),
        Artwork(
            imageId = R.drawable.artwork3,
            titleId = R.string.artwork3_name,
            artistId = R.string.artwork_artist,
            descriptionId = R.string.artwork3_description
        ),
        Artwork(
            imageId = R.drawable.artwork4,
            titleId = R.string.artwork4_name,
            artistId = R.string.artwork_artist,
            descriptionId = R.string.artwork4_description
        )
    )
}
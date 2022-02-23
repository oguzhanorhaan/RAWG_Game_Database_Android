package com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model

import android.os.Parcelable
import androidx.core.text.HtmlCompat
import com.oguzhanorhan.rawggamedatabaseandroid.common.formatDate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Games(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Game>? = null
) : Parcelable

@Parcelize
data class Game(
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null,
    val added: Int? = null,
    val name_original: String? = null,
    val description: String? = null,
    val rating: Float? = null,
    val rating_top: Int? = null,
    val ratings_count: Int? = null,
    val released: String? = null,
    val tba: Boolean? = null,
    val background_image: String? = null,
    val website: String? = null,
    val developers: List<GamesDevelopers>? = null,
    val short_screenshots: List<GamesShortScreenshots>? = null
) : Parcelable {
    val formattedDate: String get() = released?.formatDate() ?: ""
    val formattedDescription: String get() = (HtmlCompat.fromHtml(description ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)).toString()
}

@Parcelize
data class GamesShortScreenshots(
    val id: Int? = null,
    val image: String? = null
) : Parcelable

@Parcelize
data class GamesDevelopers(
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null
) : Parcelable

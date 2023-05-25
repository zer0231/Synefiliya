package com.zero.synefiliya.fragments.dashboardFragment.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.zero.synefiliya.utils.Constants.Companion.MOVIE_COLUMN

@Entity(tableName = MOVIE_COLUMN)
data class MovieDetail(
    @PrimaryKey
    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("imdb_code") var imdbCode: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("title_english") var titleEnglish: String? = null,
    @SerializedName("title_long") var titleLong: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("year") var year: Int? = null,
    @SerializedName("rating") var rating: Int? = null,
    @SerializedName("runtime") var runtime: Int? = null,
//    @SerializedName("genres") var genres: ArrayList<String> = arrayListOf(),
    @SerializedName("download_count") var downloadCount: Int? = null,
    @SerializedName("like_count") var likeCount: Int? = null,
    @SerializedName("description_intro") var descriptionIntro: String? = null,
    @SerializedName("description_full") var descriptionFull: String? = null,
    @SerializedName("yt_trailer_code") var ytTrailerCode: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("mpa_rating") var mpaRating: String? = null,
    @SerializedName("background_image") var backgroundImage: String? = null,
    @SerializedName("background_image_original") var backgroundImageOriginal: String? = null,
    @SerializedName("small_cover_image") var smallCoverImage: String? = null,
    @SerializedName("medium_cover_image") var mediumCoverImage: String? = null,
    @SerializedName("large_cover_image") var largeCoverImage: String? = null,
//    @SerializedName("torrents") var torrents: ArrayList<TorrentDetail> = arrayListOf(),
    @SerializedName("date_uploaded") var dateUploaded: String? = null,
    @SerializedName("date_uploaded_unix") var dateUploadedUnix: Int? = null

)
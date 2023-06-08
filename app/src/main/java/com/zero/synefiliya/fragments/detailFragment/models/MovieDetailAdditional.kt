package com.zero.synefiliya.fragments.detailFragment.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.zero.synefiliya.fragments.dashboardFragment.models.TorrentDetail
import com.zero.synefiliya.utils.Constants

@Entity(tableName = Constants.MOVIE_COLUMN)
data class MovieDetailAdditional(
    @PrimaryKey @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("imdb_code") var imdbCode: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("title_english") var titleEnglish: String? = null,
    @SerializedName("title_long") var titleLong: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("year") var year: Int? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @Ignore @SerializedName("genres") var genres: ArrayList<String> = arrayListOf(),
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
    @SerializedName("medium_screenshot_image1") var mediumScreenshotImage1: String? = null,
    @SerializedName("medium_screenshot_image2") var mediumScreenshotImage2: String? = null,
    @SerializedName("medium_screenshot_image3") var mediumScreenshotImage3: String? = null,
    @SerializedName("large_screenshot_image1") var largeScreenshotImage1: String? = null,
    @SerializedName("large_screenshot_image2") var largeScreenshotImage2: String? = null,
    @SerializedName("large_screenshot_image3") var largeScreenshotImage3: String? = null,
    @Ignore  @SerializedName("torrents") var torrents: ArrayList<TorrentDetail> = arrayListOf(),
    @SerializedName("date_uploaded") var dateUploaded: String? = null,
    @SerializedName("date_uploaded_unix") var dateUploadedUnix: Int? = null
)

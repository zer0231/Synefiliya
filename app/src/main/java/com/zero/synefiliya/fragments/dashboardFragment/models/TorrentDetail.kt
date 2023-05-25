package com.zero.synefiliya.fragments.dashboardFragment.models

import com.google.gson.annotations.SerializedName

data class TorrentDetail(
    @SerializedName("url") var url: String? = null,
    @SerializedName("hash") var hash: String? = null,
    @SerializedName("quality") var quality: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("seeds") var seeds: Int? = null,
    @SerializedName("peers") var peers: Int? = null,
    @SerializedName("size") var size: String? = null,
    @SerializedName("size_bytes") var sizeBytes: Int? = null,
    @SerializedName("date_uploaded") var dateUploaded: String? = null,
    @SerializedName("date_uploaded_unix") var dateUploadedUnix: Int? = null
)

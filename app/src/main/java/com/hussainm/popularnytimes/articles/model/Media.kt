package com.hussainm.popularnytimes.articles.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hussainm.popularnytimes.utility.IMAGE_KEY
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Int? = null,
    @SerializedName("caption")
    var caption: String? = null,
    @SerializedName("copyright")
    var copyright: String? = null,
    @SerializedName("media-metadata")
    var mediaMetadata: List<MediaMetadata>? = null,
    @SerializedName("subtype")
    var subtype: String? = null,
    @SerializedName("type")
    var type: String? = null
): Parcelable


fun Media.getLastImage(): String? =
    if (type == IMAGE_KEY) mediaMetadata?.lastOrNull()?.url else null

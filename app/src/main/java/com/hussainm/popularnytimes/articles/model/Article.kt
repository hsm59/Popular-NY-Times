package com.hussainm.popularnytimes.articles.model


import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.gson.annotations.SerializedName
import com.hussainm.popularnytimes.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("abstract")
    var `abstract`: String? = null,
    @SerializedName("adx_keywords")
    var adxKeywords: String? = null,
    @SerializedName("asset_id")
    var assetId: Long? = null,
    @SerializedName("byline")
    var byline: String? = null,
    @SerializedName("column")
    var column: String? = null,
    @SerializedName("des_facet")
    var desFacet: List<String>? = null,
    @SerializedName("eta_id")
    var etaId: Int? = null,
    @SerializedName("geo_facet")
    var geoFacet: List<String>? = null,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("media")
    var media: List<Media>? = null,
    @SerializedName("nytdsection")
    var nytdsection: String? = null,
    @SerializedName("org_facet")
    var orgFacet: List<String>? = null,
    @SerializedName("per_facet")
    var perFacet: List<String>? = null,
    @SerializedName("published_date")
    var publishedDate: String? = null,
    @SerializedName("section")
    var section: String? = null,
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("subsection")
    var subsection: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("updated")
    var updated: String? = null,
    @SerializedName("uri")
    var uri: String? = null,
    @SerializedName("url")
    var url: String? = null
) : Parcelable {

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, article: Article) {
            article.getLastMediaUrl()?.let { url ->
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.load(url) {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_image_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                }
            } ?: run {
                imageView.apply {
                    scaleType = ImageView.ScaleType.CENTER_INSIDE
                    load(R.drawable.ic_baseline_broken_image_24)
                }
            }
        }
    }

    /**
     * To fetch the highest resolution image
     */
    private fun getLastMediaUrl(): String? = media?.lastOrNull()?.getLastImage()


}
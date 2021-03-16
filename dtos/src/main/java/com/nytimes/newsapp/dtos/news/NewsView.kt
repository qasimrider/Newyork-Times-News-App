package com.nytimes.newsapp.dtos.news
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsView(
    val id: Long,
    val title: String,
    val abstract: String,
    val authors: String,
    val date: String,
    var largestPicture: PictureView? = null,
    val pictures: List<PictureView>
) :Parcelable {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsView> =
            object : DiffUtil.ItemCallback<NewsView>() {
                override fun areItemsTheSame(
                    @NonNull oldItem: NewsView,
                    @NonNull newItem: NewsView
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    @NonNull oldItem: NewsView,
                    @NonNull newItem: NewsView
                ): Boolean {
                    return true
                }
            }

        fun dummyNews() = NewsView(
            1, "Dummy Title", "Dummy abstract", "Dummy authors", "Dummy date",
            pictures = listOf<PictureView>()
        )
    }
}

@Parcelize
data class PictureView(val url: String, val format: String, val height: Int, val width: Int) :
    Parcelable {
    companion object {
        fun empty() = PictureView("", "", 0, 0)
    }
}

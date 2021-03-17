package com.nytimes.newsapp.dtos.news
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize


object  DummyNews {
val PEOPLE_KILLED =  NewsView(
    100000007658211,
    "8 People Killed in Atlanta-Area Massage Parlor Shootings",
    "Six of the victims were Asian, the authorities said, raising fears that there may have been a racial motivation to the crimes.",
    "By Richard Fausset and Neil Vigdor",
    "2021-03-16",
    pictures = listOf()
    )
}
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


    }
}

@Parcelize
data class PictureView(val url: String, val format: String, val height: Int, val width: Int) :
    Parcelable {
    companion object {
        fun empty() = PictureView("", "", 0, 0)
    }
}

package com.osama.movieshow.data.movie

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorites")
data class Movie(@SerializedName("id")  @PrimaryKey @ColumnInfo(name = "id") var id:String,
                 @SerializedName("poster_path") @ColumnInfo(name = "poster_path") var posterPath:String,
                 @SerializedName("title")  @ColumnInfo(name = "title") var title:String,
                 @SerializedName("vote_average")  @ColumnInfo(name = "vote_average") var voteAverage:Double,
                 @SerializedName("overview")  @ColumnInfo(name = "overview") var overview:String,
                 @SerializedName("release_date")  @ColumnInfo(name = "release_date")var releaseDate:String)
                : Parcelable {


}
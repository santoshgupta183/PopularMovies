package com.santosh.popularmovies.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.createIntList(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, p1: Int) {
        dest.writeByte(if (adult) 1 else 0)
        dest.writeString(backdrop_path)
        dest.writeIntList(genre_ids)
        dest.writeInt(id)
        dest.writeString(original_language)
        dest.writeString(original_title)
        dest.writeString(overview)
        dest.writeDouble(popularity)
        dest.writeString(poster_path)
        dest.writeString(release_date)
        dest.writeString(title)
        dest.writeByte(if (video) 1 else 0)
        dest.writeDouble(vote_average)
        dest.writeInt(vote_count)
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

fun Parcel.createIntList(): List<Int> {
    val size = readInt()
    val output = ArrayList<Int>(size)
    for (i in 0 until size) {
        output.add(readInt())
    }
    return output
}

fun Parcel.writeIntList(input:List<Int>) {
    writeInt(input.size) // Save number of elements.
    return input.forEach(this::writeInt) // Save each element.
}
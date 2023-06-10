package com.dicoding.journie.data.network.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ExploreDestination(
	@field:SerializedName("ExploreDestination")
	val exploreDestination: List<Destination>
) : Parcelable

@Parcelize
data class Destination(

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("Category")
	val category: String? = null,

	@field:SerializedName("image_link")
	val imageLink: String? = null,

	@field:SerializedName("Place_Name")
	val placeName: String? = null,

	@field:SerializedName("Rating")
	val rating: Double? = null,

	@field:SerializedName("Rank")
	val rank: Int? = null,

	@field:SerializedName("Duration")
	val duration: Int? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("Place_Id")
	val placeId: Int? = null,

	@field:SerializedName("Price")
	val price: Int? = null,

	@field:SerializedName("UserId")
	val userId: String? = null,

	@field:SerializedName("Long")
	val long: Double? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null
) : Parcelable

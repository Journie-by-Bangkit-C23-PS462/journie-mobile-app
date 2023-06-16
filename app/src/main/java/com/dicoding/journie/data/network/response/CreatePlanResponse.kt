package com.dicoding.journie.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatePlanResponse(
	@field:SerializedName("data")
	var data: List<List<DestinationRecommendation>>,

	@field:SerializedName("status")
	var status: Boolean,

	@field:SerializedName("plan_id")
	var planID: Int
) : Parcelable

@Parcelize
data class DestinationRecommendation(

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("Category")
	val category: String? = null,

	@field:SerializedName("image_link")
	val imageLink: String? = null,

	@field:SerializedName("Place_Name")
	val placeName: String? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("Duration")
	val duration: Int? = null,

	@field:SerializedName("Place_Id")
	val placeId: Int? = null,

	@field:SerializedName("Price")
	val price: Int? = null,

	@field:SerializedName("Long")
	val long: Double? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null

) : Parcelable

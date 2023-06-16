package com.dicoding.journie.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivePlanResponse(
	@field:SerializedName("data")
	val data: List<List<List<DestinationRecommendation>>>? = null,

	@field:SerializedName("status")
	val status: Boolean
) : Parcelable


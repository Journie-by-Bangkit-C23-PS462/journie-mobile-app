package com.dicoding.journie.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavePlanResponse(
	@field:SerializedName("data")
	val data: String
) : Parcelable



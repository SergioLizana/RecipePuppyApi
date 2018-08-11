package ikigaiworks.recipepuppyapi.api.model

import android.os.Parcel
import android.os.Parcelable

data class ResultsItem(
	val thumbnail: String? = null,
	val ingredients: String? = null,
	val href: String? = null,
	val title: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString())

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(thumbnail)
		parcel.writeString(ingredients)
		parcel.writeString(href)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResultsItem> {
		override fun createFromParcel(parcel: Parcel): ResultsItem {
			return ResultsItem(parcel)
		}

		override fun newArray(size: Int): Array<ResultsItem?> {
			return arrayOfNulls(size)
		}
	}
}

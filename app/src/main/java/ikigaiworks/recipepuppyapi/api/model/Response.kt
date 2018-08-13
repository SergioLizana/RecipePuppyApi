package ikigaiworks.recipepuppyapi.api.model

import android.os.Parcel
import android.os.Parcelable

data class Response(
		var href: String? = null,
		var title: String? = null,
		var version: Double? = null,
		var results: List<ResultsItem?>? = null
) : Parcelable {

	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readValue(Double::class.java.classLoader) as? Double)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(href)
		parcel.writeString(title)
		parcel.writeValue(version)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Response> {
		override fun createFromParcel(parcel: Parcel): Response {
			return Response(parcel)
		}

		override fun newArray(size: Int): Array<Response?> {
			return arrayOfNulls(size)
		}
	}
}

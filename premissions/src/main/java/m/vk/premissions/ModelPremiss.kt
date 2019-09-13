package m.vk.premissions

import android.os.Parcel
import android.os.Parcelable

data class ModelPremiss(
    val requestCode: Int,
    val grantResults: Int,
    val permissions: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(requestCode)
        parcel.writeInt(grantResults)
        parcel.writeString(permissions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPremiss> {
        override fun createFromParcel(parcel: Parcel): ModelPremiss {
            return ModelPremiss(parcel)
        }

        override fun newArray(size: Int): Array<ModelPremiss?> {
            return arrayOfNulls(size)
        }
    }

}
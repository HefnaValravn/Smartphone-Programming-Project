package com.example.smartphoneproject;

import android.os.Parcel;
import android.os.Parcelable;

public class MarkerData implements Parcelable {
    String title;
    double lat;
    double lng;

    public MarkerData(String title, double lat, double lng) {
        this.title = title;
        this.lat = lat;
        this.lng = lng;
    }

    protected MarkerData(Parcel in) {
        title = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<MarkerData> CREATOR = new Creator<>() {
        public MarkerData createFromParcel(Parcel in) { return new MarkerData(in); }
        public MarkerData[] newArray(int size) { return new MarkerData[size]; }
    };

    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}


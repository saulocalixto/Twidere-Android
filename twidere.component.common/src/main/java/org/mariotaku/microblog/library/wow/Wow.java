package org.mariotaku.microblog.library.wow;

import android.os.Parcel;
import android.os.Parcelable;

import org.mariotaku.restfu.annotation.method.GET;
import org.mariotaku.restfu.annotation.param.Query;

public class Wow implements Parcelable {

    protected Wow(Parcel in) {
    }

    public static final Creator<Wow> CREATOR = new Creator<Wow>() {
        @Override
        public Wow createFromParcel(Parcel in) {
            return new Wow(in);
        }

        @Override
        public Wow[] newArray(int size) {
            return new Wow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

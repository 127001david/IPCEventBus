package com.rightpoint.ipceventbus.msg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description：
 * @author Wonder Wei
 * Create date：2020/6/4 10:52 AM 
 */
public class Msg implements Parcelable {
    public int code;

    public Msg() {

    }

    public Msg(int code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        code = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
    }

    protected Msg(Parcel in) {
        this.code = in.readInt();
    }

    public static final Creator<Msg> CREATOR = new Creator<Msg>() {
        @Override
        public Msg createFromParcel(Parcel source) {
            return new Msg(source);
        }

        @Override
        public Msg[] newArray(int size) {
            return new Msg[size];
        }
    };
}

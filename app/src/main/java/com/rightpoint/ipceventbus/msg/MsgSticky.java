package com.rightpoint.ipceventbus.msg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description：
 * @author Wonder Wei
 * Create date：2020/6/8 9:10 AM 
 */
public class MsgSticky implements Parcelable {

    public String msg;

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        msg = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
    }

    public MsgSticky() {
    }

    public MsgSticky(String msg) {
        this.msg = msg;
    }

    protected MsgSticky(Parcel in) {
        this.msg = in.readString();
    }

    public static final Creator<MsgSticky> CREATOR = new Creator<MsgSticky>() {
        @Override
        public MsgSticky createFromParcel(Parcel source) {
            return new MsgSticky(source);
        }

        @Override
        public MsgSticky[] newArray(int size) {
            return new MsgSticky[size];
        }
    };
}

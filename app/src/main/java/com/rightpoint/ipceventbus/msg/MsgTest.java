package com.rightpoint.ipceventbus.msg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description：
 * @author Wonder Wei
 * Create date：2020/6/4 10:52 AM 
 */
public class MsgTest implements Parcelable {
    public int code;

    public MsgTest() {

    }

    public MsgTest(int code) {
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

    protected MsgTest(Parcel in) {
        this.code = in.readInt();
    }

    public static final Creator<MsgTest> CREATOR = new Creator<MsgTest>() {
        @Override
        public MsgTest createFromParcel(Parcel source) {
            return new MsgTest(source);
        }

        @Override
        public MsgTest[] newArray(int size) {
            return new MsgTest[size];
        }
    };
}

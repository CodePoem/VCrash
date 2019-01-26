package com.vdreamers.vcrash.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * device model
 * <p>
 * date 2019/01/24 09:27:39
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DeviceModel implements Parcelable {

    /**
     * device name
     */
    private String model = Build.MODEL;
    /**
     * device brand
     */
    private String brand = Build.BRAND;
    /**
     * device system version
     */
    private String version = String.valueOf(Build.VERSION.SDK_INT);

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.model);
        dest.writeString(this.brand);
        dest.writeString(this.version);
    }

    public DeviceModel() {
    }

    protected DeviceModel(Parcel in) {
        this.model = in.readString();
        this.brand = in.readString();
        this.version = in.readString();
    }

    public static final Creator<DeviceModel> CREATOR = new Creator<DeviceModel>() {
        @Override
        public DeviceModel createFromParcel(Parcel source) {
            return new DeviceModel(source);
        }

        @Override
        public DeviceModel[] newArray(int size) {
            return new DeviceModel[size];
        }
    };
}

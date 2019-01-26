package com.vdreamers.vcrash.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * crash model
 * <p>
 * date 2019/01/24 09:25:38
 *
 * @author <a href="mailto:codepoetdream@gmail.com">Mr.D</a>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CrashModel implements Parcelable {
    /**
     * crash main throwable
     */
    private Throwable ex;
    /**
     * package name
     */
    private String packageName;
    /**
     * crash main msg
     */
    private String exceptionMsg;
    /**
     * crash class name
     */
    private String className;
    /**
     * crash file name
     */
    private String fileName;
    /**
     * crash method name
     */
    private String methodName;
    /**
     * crash line number
     */
    private int lineNumber;
    /**
     * crash type
     */
    private String exceptionType;
    /**
     * crash full msg
     */
    private String fullException;
    /**
     * crash time
     */
    private long time;
    /**
     * crash device info
     */
    private DeviceModel device = new DeviceModel();

    public Throwable getEx() {
        return ex;
    }

    public void setEx(Throwable ex) {
        this.ex = ex;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getFullException() {
        return fullException;
    }

    public void setFullException(String fullException) {
        this.fullException = fullException;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.device = device;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.ex);
        dest.writeString(this.packageName);
        dest.writeString(this.exceptionMsg);
        dest.writeString(this.className);
        dest.writeString(this.fileName);
        dest.writeString(this.methodName);
        dest.writeInt(this.lineNumber);
        dest.writeString(this.exceptionType);
        dest.writeString(this.fullException);
        dest.writeLong(this.time);
        dest.writeParcelable(this.device, flags);
    }

    public CrashModel() {
    }

    protected CrashModel(Parcel in) {
        this.ex = (Throwable) in.readSerializable();
        this.packageName = in.readString();
        this.exceptionMsg = in.readString();
        this.className = in.readString();
        this.fileName = in.readString();
        this.methodName = in.readString();
        this.lineNumber = in.readInt();
        this.exceptionType = in.readString();
        this.fullException = in.readString();
        this.time = in.readLong();
        this.device = in.readParcelable(DeviceModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<CrashModel> CREATOR =
            new Parcelable.Creator<CrashModel>() {
                @Override
                public CrashModel createFromParcel(Parcel source) {
                    return new CrashModel(source);
                }

                @Override
                public CrashModel[] newArray(int size) {
                    return new CrashModel[size];
                }
            };
}

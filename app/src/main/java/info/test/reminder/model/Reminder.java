package info.test.reminder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ravi Tamada on 21/02/17.
 * www.androidhive.info
 */

public class Reminder implements Parcelable {
    private int id;
    private String title;
    private String subject;
    private String message;
    private String timestamp;
    private String picture;
    private int color = -1;

    public Reminder() {
    }

    protected Reminder(Parcel in) {
        id = in.readInt();
        title = in.readString();
        subject = in.readString();
        message = in.readString();
        timestamp = in.readString();
        picture = in.readString();
        color = in.readInt();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(subject);
        dest.writeString(message);
        dest.writeString(timestamp);
        dest.writeString(picture);
        dest.writeInt(color);
    }
}

package com.example.qing.aidl.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qing on 2016/10/31.
 */

public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public String toString() {
        return "{bookId：" + bookId + "；bookName:" + bookName +"}";
    }
}

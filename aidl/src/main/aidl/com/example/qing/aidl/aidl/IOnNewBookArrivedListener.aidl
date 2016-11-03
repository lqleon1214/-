// IOnNewBookArrivedListener.aidl
package com.example.qing.aidl.aidl;

// Declare any non-default types here with import statements
import com.example.qing.aidl.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}

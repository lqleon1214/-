// IBookManager.aidl
package com.example.qing.aidl.aidl;

import com.example.qing.aidl.aidl.Book;
import com.example.qing.aidl.aidl.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}

package com.example.qing.binderpool;

import android.os.RemoteException;

/**
 * Created by qing on 2016/11/2.
 */

public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}

package com.example.qing.restfulcontacts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.qing.restfulcontacts.data.ContactsContract;

/**
 * Created by qing on 16/9/8.
 */

public class BaseActivity extends Activity {

    private static final String TAG = "BASE";
    private static final SparseIntArray STATUS_COLOR_MAP;
    static {
        SparseIntArray a = new SparseIntArray();
        a.put(ContactsContract.STATUS_OK, Color.GREEN);
        a.put(ContactsContract.STATUS_SYNC,Color.YELLOW);
        a.put(ContactsContract.STATUS_DIRTY,Color.RED);
        STATUS_COLOR_MAP = a;
    }

    protected static void setStatusBackground(int status, View view){
        int color = STATUS_COLOR_MAP.get(status);
        view.setBackgroundColor((0 != color) ? color:Color.BLACK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_prefs:
                startActivity(new Intent(this,PrefsActivity.class));
                break;
            default:
                Log.i(TAG,"Unrecognized menu item: " + item);
                return false;
        }
        return true;
    }
}

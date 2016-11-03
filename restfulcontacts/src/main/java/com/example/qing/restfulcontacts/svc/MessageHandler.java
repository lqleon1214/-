package com.example.qing.restfulcontacts.svc;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.example.qing.restfulcontacts.data.ContactsContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qing on 16/9/12.
 */

public class MessageHandler {

    private static final String TAG = "JSON";

    public static final String TAG_FNAME = "firstName";
    public static final String TAG_LNAME = "lastName";
    public static final String TAG_PHONE = "phone";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_LOCATION = "location";

    private static final Map<String,String> MARSHAL_TAB;
    static {
        Map<String,String> m = new HashMap<String,String>();
        m.put(RESTService.FNAME,TAG_FNAME);
        m.put(RESTService.LNAME,TAG_LNAME);
        m.put(RESTService.PHONE,TAG_PHONE);
        m.put(RESTService.EMAIL,TAG_EMAIL);
        MARSHAL_TAB = m;
    }

    private static final Map<String,String> UNMARSHAL_TAB;
    static {
        Map<String,String> m = new HashMap<String,String>();
        m.put(TAG_FNAME, ContactsContract.Columns.FNAME);
        m.put(TAG_LNAME,ContactsContract.Columns.LNAME);
        m.put(TAG_PHONE,ContactsContract.Columns.PHONE);
        m.put(TAG_EMAIL,ContactsContract.Columns.EMAIL);
        m.put(TAG_LOCATION,ContactsContract.Columns.REMOTE_ID);
        UNMARSHAL_TAB = m;
    }

    /**
     * 生成JSON字符串
     * @param args
     * @return
     * @throws JSONException
     */
    public String marshal(Bundle args) throws JSONException {
        JSONObject payload = new JSONObject();

        if (args.containsKey(RESTService.FNAME)) {
            payload.put(MARSHAL_TAB.get(RESTService.FNAME),args.getString(RESTService.FNAME));
        }

        if (args.containsKey(RESTService.LNAME)) {
            payload.put(MARSHAL_TAB.get(RESTService.LNAME),args.getString(RESTService.LNAME));
        }

        if (args.containsKey(RESTService.PHONE)) {
            payload.put(MARSHAL_TAB.get(RESTService.PHONE),args.getString(RESTService.PHONE));
        }

        if (args.containsKey(RESTService.EMAIL)) {
            payload.put(MARSHAL_TAB.get(RESTService.EMAIL) ,args.getString(RESTService.EMAIL));
        }

        return payload.toString();
    }

    /**
     * 把输入流直接转换成ContentValues对象
     * @param in
     * @param vals
     * @return
     * @throws IOException
     */
    public ContentValues unmarshal(Reader in,ContentValues vals) throws IOException{
        JsonReader reader = null;
        try {
            reader = new JsonReader(in);
            unmarshalContact(reader,vals);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (Exception e) {

                }
            }
        }
        return  vals;
    }

    /**
     * JSON对象解析的方法,自己又有新的东西学了
     * @param in
     * @param vals
     * @throws IOException
     */
    public void unmarshalContact(JsonReader in, ContentValues vals) throws  IOException {
        in.beginObject();
        while (in.hasNext()) {
            String key = in.nextName();
            String val = in.nextString();
            String col = UNMARSHAL_TAB.get(key);
            if (null == col) {
                Log.w(TAG,"Ignoring unexcepted JSON tag: " + key + " " + val);
                continue;
            }
            if (TAG_LOCATION.equals(key)) {
                val = parseLocation(val);
            }

            vals.put(col,val);
        }
        in.endObject();
    }

    /**
     * 解析地址,但是返回的方法中的最后的方法我感觉很有用,之前没有用过
     * @param val
     * @return
     */
    private String parseLocation(String val) {
        return Uri.parse(val).getLastPathSegment();
    }
}

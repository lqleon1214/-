package com.example.qing.restfulcontacts.utils;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qing on 16/9/12.
 */

public class ColumnMap {

    public static enum Type{
        BOOLEAN,BYTE,BYTEARRAY,DOUBLE,FLOAT,INTEGER,LONG,SHORT,STRING
    };

    public static class Builder {
        private final Map<String,ColumnDef> colMap = new HashMap<String,ColumnDef>();

        public Builder addColumn(String virtCol,String actCol,Type type) {
            colMap.put(virtCol,new ColumnDef(actCol,type));
            return this;
        }

        public ColumnMap build(){
            return new ColumnMap(colMap);
        }
    }

    private static class ColumnDef {
        private final String name;
        private final Type type;

        public ColumnDef(String name,Type type) {
            this.name = name;
            this.type = type;
        }

        public void copy(String srcCol, ContentValues src,ContentValues dst) {
            switch (type) {
                case BOOLEAN:
                    dst.put(name,src.getAsBoolean(srcCol));
                    break;
                case BYTE:
                    dst.put(name,src.getAsByte(srcCol));
                    break;
                case BYTEARRAY:
                    dst.put(name,src.getAsByteArray(srcCol));
                    break;
                case DOUBLE:
                    dst.put(name,src.getAsDouble(srcCol));
                    break;
                case FLOAT:
                    dst.put(name,src.getAsFloat(srcCol));
                    break;
                case INTEGER:
                    dst.put(name,src.getAsInteger(srcCol));
                    break;
                case LONG:
                    dst.put(name,src.getAsLong(srcCol));
                    break;
                case SHORT:
                    dst.put(name,src.getAsShort(srcCol));
                    break;
                case STRING:
                    dst.put(name,src.getAsString(srcCol));
                    break;
            }
        }
    }

    private final Map<String,ColumnDef> colMap;

    ColumnMap(Map<String,ColumnDef> colMap) {
        this.colMap = colMap;
    }

    public ContentValues translateCols(ContentValues vals) {
        ContentValues newVals = new ContentValues();
        for (String colName : vals.keySet()) {
            ColumnDef colDef = colMap.get(colName);
            if (null == colDef) {
                throw new IllegalArgumentException("Unrecognized column;" + colName);
            }
            colDef.copy(colName,vals,newVals);
        }
        return newVals;
    }
}

package com.example.qing.restfulcontacts.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qing on 16/9/12.
 */

public class ProjectionMap {

    public static class Builder{
        Map<String,String> colMap = new HashMap<String,String>();

        public Builder addColumn(String virtCol,String actCol){
            colMap.put(virtCol,actCol + " AS" + virtCol);
            return this;
        }

        public Builder addColumn(String virtCol,String actTab,String actCol) {
            return addColumn(virtCol,actTab + "." + actCol);
        }

        public ProjectionMap build(){
            return new ProjectionMap(colMap);
        }
    }

    private final Map<String,String> colMap;

    ProjectionMap(Map<String,String> colMap) {
        this.colMap = Collections.unmodifiableMap(colMap);
    }

    public Map<String,String> getProjectionMap(){
        return colMap;
    }
}

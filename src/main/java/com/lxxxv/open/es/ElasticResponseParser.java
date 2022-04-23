package com.lxxxv.open.es;

import java.util.ArrayList;
import java.util.Map;

public class ElasticResponseParser {
    public ArrayList<Object> exportHits(Map<String, Object> response) {
        return this.getHits(response);
    }

    public ArrayList<Object> exportFields(ArrayList<Object> hits) {
        assert hits != null;

        ArrayList<Object> fields = new ArrayList<>();
        for (Object hit : hits) {
            Map<String, Object> hitMap = this.typeCaseMap(hit);
            assert hitMap != null;
            fields.add(hitMap.get("fields"));
        }

        return fields;
    }


    private ArrayList<Object> getHits(Map<String, Object> response) {
        assert response != null;
        Object objHits = response.get("hits");
        assert objHits != null;
        Map<String, Object> mapObject = this.typeCaseMap(objHits);
        assert mapObject != null;
        objHits = mapObject.get("hits");
        assert objHits != null;
        return this.typeCaseArrayList(objHits);
    }

    private Map<String, Object> getFields(Object hit) {
        assert hit != null;

        if (this.isMapType(hit)) {
            Map<String, Object> mapObject = (Map<String, Object>) hit;
            Object fields = mapObject.get("fields");
            if (this.isMapType(fields)) {
                return (Map<String, Object>) fields;
            }
        }

        return null;
    }

    public Map<String, Object> typeCaseMap(Object data) {
        if (this.isMapType(data)) {
            return (Map<String, Object>) data;
        }

        return null;
    }

    public ArrayList<Object> typeCaseArrayList(Object data) {
        if (this.isArrayListType(data)) {
            return (ArrayList<Object>) data;
        }

        return null;
    }


    private boolean isMapType(Object data) {
        return this.isMatchGenericType(data, "Map");
    }

    private boolean isArrayListType(Object data) {
        return this.isMatchGenericType(data, "ArrayList");
    }

    private boolean isMatchGenericType(Object data, String typeName) {
        assert data != null;
        return (data.getClass().toString().contains("java.util.")) && (data.getClass().toString().contains(typeName));
    }
}

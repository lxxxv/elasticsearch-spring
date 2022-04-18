package com.lxxxv.open.es;

import java.util.ArrayList;
import java.util.Map;

public class ElasticCallback
{
    private final String request;
    private final Map<String, Object> response;
    private final ArrayList<Object> fields;

    public ElasticCallback(String request, Map<String, Object> response, ArrayList<Object> fields)
    {
        this.request = request;
        this.response = response;
        this.fields = fields;
    }

    public String getRequest()
    {
        return request;
    }

    public Map<String, Object> getResponse()
    {
        return response;
    }

    public ArrayList<Object> getFields()
    {
        return fields;
    }
}

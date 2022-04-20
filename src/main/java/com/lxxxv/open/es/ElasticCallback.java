package com.lxxxv.open.es;

import java.util.ArrayList;
import java.util.Map;

public class ElasticCallback
{
    private final String request;
    private final Map<String, Object> response;
    private final ArrayList<String> ids;
    private final ArrayList<Object> fields;

    public ElasticCallback(String request, Map<String, Object> response, ArrayList<String> ids, ArrayList<Object> fields)
    {
        this.request = request;
        this.response = response;
        this.ids = ids;
        this.fields = fields;
    }

    public String getRequest()
    {
        return this.request;
    }

    public Map<String, Object> getResponse()
    {
        return this.response;
    }

    public ArrayList<Object> getFields()
    {
        return this.fields;
    }

    public ArrayList<String> getIds()
    {
        return this.ids;
    }
}

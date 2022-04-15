package com.lxxxv.open.es;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.File;

public class ElasticsearchBulk
{
    public void JSONFileLoad(String JSONFilePath)
    {
        if (!isFileExists(JSONFilePath))
        {
            return;
        }

        try
        {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader(JSONFilePath));

            JSONArray jsonArray = (JSONArray) obj;

            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray)
            {
                this.parseJSONObject(jsonObject);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void parseJSONObject(JSONObject employee)
    {

    }

    private boolean isFileExists(String FilePath)
    {
        File file = new File(FilePath);
        if (file.exists())
        {
            return !file.isDirectory();
        }
        else
        {
            return false;
        }
    }
}

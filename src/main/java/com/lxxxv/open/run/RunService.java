package com.lxxxv.open.run;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import com.lxxxv.open.es.ElasticsearchBuilder;
import com.lxxxv.open.es.ElasticsearchBulk;

@Service
public class RunService
{
    @PostConstruct
    public void constructor()
    {
        System.out.println("creator : " + Thread.currentThread().getName() + " " + "create : " + this);
        this.Test();
    }

    public String call()
    {
        return "creator : " + Thread.currentThread().getName() + " " + "create : " + this.toString();
    }

    public void Test()
    {
        ElasticsearchBulk elasticsearchBulk = new ElasticsearchBulk();
        elasticsearchBulk.JSONFileLoad("D:\\mockaroo\\100.json");
    }
}

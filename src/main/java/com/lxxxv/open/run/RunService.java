package com.lxxxv.open.run;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RunService
{
    @PostConstruct
    public void constructor()
    {
        System.out.println("creator : " + Thread.currentThread().getName() + " " + "create : " + this);
    }

    public String call()
    {
        return "creator : " + Thread.currentThread().getName() + " " + "create : " + this.toString();
    }
}

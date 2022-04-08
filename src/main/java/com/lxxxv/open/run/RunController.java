package com.lxxxv.open.run;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RunController
{
    private final RunService runService;

    @PostConstruct
    public void constructor()
    {
        System.out.println("creator : " + Thread.currentThread().getName() + " " + "create : " + this);
    }

    @GetMapping("hello")
    public String helloworld()
    {
        return "hello world";
    }

    @GetMapping("call")
    public String call()
    {
        return runService.call();
    }
}

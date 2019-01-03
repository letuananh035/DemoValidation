package com.validation.demo.demovalidation.Custom;

import core.IValidation;
import core.annotation.MinDouble;
import core.annotation.NotEmpty;
import core.annotation.NotNull;
import core.annotation.Regex;
import core.annotation.Size;
import core.result.ResultItemObserver;

public class Customer extends IValidation
{

    @NotEmpty
    public String name;
    @NotNull
    @NotEmpty
    @Regex(regex = "\\d+")
    public String phone;

    @MinDouble(min = 1)
    public int age;

    @NotEmpty
    @Email
    public String email;


    @MinDouble(min = 3, target = "$.age",message = "Ban cua ban cua ban qua nho")
    public Customer friend;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void setupValidation(ResultItemObserver resultItemObserver) {

    }
}


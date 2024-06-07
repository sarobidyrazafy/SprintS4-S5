package controller;

import Annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Controller1 {

    @Get(value = "/test")
    public String method1() {
        return "HELLOO!";
    }

    @Get(value = "/date")
    public String method3() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }

    @Get(value = "/date1")
    public java.util.Date method4() {
        return new java.util.Date();
    }
}
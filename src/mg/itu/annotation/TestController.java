package mg.itu.controller;

import mg.itu.annotation.*;

@AnnotationController
public class TestController {

    @GET("/test")
    public void testMethod() {
        // Logic for handling /test URL
    }
}
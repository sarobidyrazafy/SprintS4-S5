package Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//Controller appliquée aux classes
@Target(ElementType.TYPE)
public @interface Controller { }
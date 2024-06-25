package Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Get {
    //Attribut pour spécifier l'URL associée à la méthode
    String value() default "";
}


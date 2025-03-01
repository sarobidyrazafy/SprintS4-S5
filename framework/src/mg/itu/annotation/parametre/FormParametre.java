package mg.itu.annotation.parametre;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormParametre {
    String value();
}

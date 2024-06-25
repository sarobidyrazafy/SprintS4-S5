package Annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {
    //Attribut pour spécifier le nom du paramètre de la requête
    String name();
}

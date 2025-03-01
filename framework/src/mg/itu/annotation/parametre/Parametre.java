package mg.itu.annotation.parametre;
import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Parametre {
    String name();
}

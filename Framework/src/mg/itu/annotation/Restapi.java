import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // appliquée aux classes et interfaces
public @interface RestApi {
    String url() default ""; // URL de base de l'API

    String method() default "GET"; // Méthode HTTP par défaut
}
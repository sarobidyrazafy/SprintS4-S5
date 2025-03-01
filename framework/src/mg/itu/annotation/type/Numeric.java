package mg.itu.annotation.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Numeric {
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
    int precision() default 15; 
    int scale() default 2;     
}

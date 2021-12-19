package info.behzadian.repository.annotation;

import java.lang.annotation.*;

//This annotation is used to modify TYPE elements, that is, modify fields.
@Target(ElementType.FIELD)
//The annotation information is retained at runtime
@Retention(RetentionPolicy.RUNTIME)
//This annotation is included in Javadoc
@Documented
public @interface Column {
    public String columnName() default "";
}

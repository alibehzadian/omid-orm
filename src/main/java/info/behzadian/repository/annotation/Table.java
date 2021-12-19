package info.behzadian.repository.annotation;

import java.lang.annotation.*;

//This annotation is used to modify TYPE elements, that is, modify classes and interfaces.
@Target(ElementType.TYPE)
//The annotation information is retained at runtime
@Retention(RetentionPolicy.RUNTIME)
//This annotation is included in Javadoc
@Documented
public @interface Table {
    public String tableName() default "";
}

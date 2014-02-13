package lib;

import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFormInfo {
    int    seq() default 1;
    String prompt() default "";
    int size() default 0;
    String options() default "";
    String inputType() default "text";
    int maxLength() default 50;



}

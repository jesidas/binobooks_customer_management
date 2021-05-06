package com.binobook.annoation;

import java.lang.annotation.*;

/**
 * Define the permission code of the corresponding resource required by the method
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermission {
    // permission code
    String code() default "";
}

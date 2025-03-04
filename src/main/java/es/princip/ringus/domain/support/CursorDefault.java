package es.princip.ringus.domain.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CursorDefault {
    String value() default "cursor";
    Class<? extends Cursor> type() default Cursor.class;
}

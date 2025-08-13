package hello.core.annotation;

// custom annotation 만들기

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// ctrl + b: 인터페이스나 클래스의 코드 추적

// @Qualifier.class에서 @Target, @Retention, @Inherited, @Documented복사
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy
{

}

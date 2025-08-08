package hello.core.scan.filter;

import java.lang.annotation.*;

// MyIncludeComponent라는 Annotation이 붙으면 컴포넌트 스캔에 추가한다

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent
{

}

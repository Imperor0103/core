package hello.core.scan.filter;

import java.lang.annotation.*;

// MyExcludeComponent라는 Annotation이 붙으면 컴포넌트 스캔에서 제외한다

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent
{

}

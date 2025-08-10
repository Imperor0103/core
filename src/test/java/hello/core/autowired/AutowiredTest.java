package hello.core.autowired;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

// 옵션 처리

// shift + f6: 클래스명, 파일명 변경)
public class AutowiredTest
{
    @Test
    void AutowiredOption()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean
    {
        // required = false: 설정으로 주입할 빈이 없어도 동작
        // 자동 주입할 bean이 없으면 메서드 호출 자체가 안된다
        // Member는 spring bean이 아니다. 따라서 setNoBean1 메서드는 호출되지 않는다
        @Autowired(required = false)
        public void setNoBean1(Member noBean1)
        {
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동 주입할 bean이 없어도 호출은 되지만 그때는 null로 들어온다
        @Autowired
        public void setNoBean2(@Nullable Member noBean2)
        {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional은 java 8에서 제공하는 문법
        // 자동 주입할 bean이 없으면 Optional empty, 있으면 bean이 들어간다
        @Autowired
        public void setNoBean3(Optional<Member> noBean3)
        {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

// 강의자료 3.스프링 핵심 원리 이해2- 객체 지향 원리 적용.pdf 참고
// log 출력을 위해 src/main/resources/logback.xml 추가

// AutoAppConfig 컴포넌트 스캔, 의존관계 자동 주입 테스트
public class AutoAppConfigTest
{
    @Test
    void basicScan()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

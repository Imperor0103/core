package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest
{
    @Test
    void statefulServiceSingleton()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ctrl + shift + ↑↓: 해당 line을 화살표 방향으로 이동
        // ThreadA: A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB: B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice); // 20000원 출력(문제발생)
        // price는 공유필드인데 특정 클라이언트가 값을 변경하는 것이 문제
        // 이러한 문제가 발생하지 않도록 sprind의 Bean은 항상 무상태(stateless)로 설계해야 한다

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig
    {
        @Bean
        public StatefulService statefulService()
        {
            return new StatefulService();
        }
    }
}
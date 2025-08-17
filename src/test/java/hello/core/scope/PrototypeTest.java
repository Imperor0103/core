package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest
{
    @Test
    void prototypeBeanFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // PrototypeBean.init 호출
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // PrototypeBean.init 호출
        // 위에서 생성한 PrototypeBean1, 2는 다르니까 생성할때마다 init을 호출하는것
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);  // prototypeBean1, 2의 주소가 다르기 때문에 통과

        // 닫을 때 수동으로 destroy를 호출해야 한다
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close(); // close할때 자동으로 destroy 호출하지 않는다
    }

    @Scope("prototype")
    static class PrototypeBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

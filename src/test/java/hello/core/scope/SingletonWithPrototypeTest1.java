package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1
{
    @Test
    void prototypeFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1); // 2가 아니다
    }

    @Test
    void singletonClientUsePrototype()
    {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);    // 둘 다 자동 bean 등록

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    // singleton bean이면 spring이 자동 등록한다
    @Scope("singleton")
    static class ClientBean
    {
        // 필드 주입으로 테스트(생성자 주입으로 테스트 해도 된다)
        // java standard인 jsr-330 provider 사용
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;  // jakarta.inject에서 제공하는 Provider를 사용


        public int logic()
        {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean
    {
        private int count = 0;

        public void addCount()
        {
            count++;
        }
        public int getCount()
        {
            return count;
        }

        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

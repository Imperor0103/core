package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest
{
    @Test
    public void lifeCycleTest()
    {
        // ctrl + b: 정의로 이동
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // ConfigurableApplicationContext에 정의된 close메서드로 닫아야 한다
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig
    {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient()
        {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest
{

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean()
    {
        // ctrl + alt + v: 자동완성
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames)
        {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name =" + beanDefinitionName + " object = " + bean);
            // 모든 bean이 출력된다
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean()
    {
        // ctrl + alt + v: 자동완성
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames)
        {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION: spring 내부의 bean이 아니라 사용자가 직접 등록한 bean
            // ROLE_INFRASTRUCTURE: spring이 내부에서 사용하는 bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name =" + beanDefinitionName + " object = " + bean);
                // 사용자가 등록한 bean(현재 5개)만 출력된다
            }
        }
    }


}

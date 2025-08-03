package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest
{
    // spring은 BeanDefinition으로 spring bean의 설정 메타 정보를 추상화한다

    // spring에 bean을 등록하는 방법
    // 1. 과거의 xml 기반에서는 xml 파일에 bean을 "직접" 등록한다(appConfig.xml 참고)
    // 2. annotation 기반에서는 factory 메서드를 통해 bean을 등록(AppConfig 참고): 코드를 조작할 수 있다는 장점이 있다


    // 2. annotation 기반: JavaConfig에서는 외부에서 factory 메서드를 호출하여 생성(AppConfig는 factory 메서드이다)
    // 출력하면 bean에 대한 클래스 정보가 모두 드러나지는 않는다
    AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(AppConfig.class);

    // 1. xml 기반: 출력하면 bean에 대한 클래스가 모두 드러난다
//    GenericXmlApplicationContext ac  = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean()
    {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames)
        {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                System.out.println("beanDefinitionName: " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}

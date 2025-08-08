package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 자동으로 spring bean을 등록한다

// 설정 정보이므로 @Configuration
// @ComponentScan: @Bean을 모두 찾아서 등록한다

// 예제 코드를 지우고 하는 것이 가장 좋지만, 이를 살리고 제외하는 방법도 알아보자
// excludeFilters에 있는 type은 제외한다
// 사용자가 AppConfig에 정의한 bean은 수동으로 등록해야 하므로(충돌 방지) Configuration.class를 하여 자동 등록 대상에서 제외한다
// ctrl + e: 다른 파일 검색
// l shift 2번:  해당 키워드를 가진 모든 파일 검색
// @Configuration 검색하면 ApplicationContextExtendsFindText 파일을 찾을 수 있다

// basePackages 탐색할 패키지의 시작 위치를 지정해서 이 패키지를 포함하여 하위 패키지를 모두 탐색
// basePackageClasses 지정한 클래스가 있는 패키지를 탐색의 시작 위치로 지정
// 예를 들어 basePacakages를 hello.core.member
// basePackageClasses를 AutoAppConfig.class라고 하면
// AutoAppConfig.class는 hello.core 패키지에 있는 클래스이므로 hello.core 패키지를 시작 위치로 지정
// 만약 basePackageClasses를 지정하지 않으면 @ComponentScan을 붙인 클래스가 있는 패키지를 시작위치로 지정
// basePackages와 basePackageClasses를 지정하지 않고 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이 관례
// 관례대로 사용하는 것을 권장

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig
{
    
}

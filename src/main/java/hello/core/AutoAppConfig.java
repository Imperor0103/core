package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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
// spring boot를 사용하면 시작 정보인 @SpringBootApplication을 이 프로젝트의 시작 루트 위치에 두는 것이 관례
// 이 설정 안에 @ComponentScan이 들어 있다
// @SpringBootApplication은 프로젝트 만들 때 자동으로 생성되었던 CoreApplication 안에 들어있다
@Configuration
@ComponentScan(

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig
{
    // 같은 이름의 Bean을 컴포넌트에 등록하면?
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
        // 이렇게 만든 이유는
        // MemoryMemberRepository의 정의를 보면 @Component가 붙어 있으므로
        // MemoryMemberRepository의 이름은 첫글자 M이 소문자로 바뀐 memoryMemberRepository이 된다
        // 그러면 memoryMemberRepository는 2개가 된다
        // 그래도 2개 모두 등록에 성공하는데
        // 출력메세지에 Overriding bean definition이 붙어 있음을 알 수 있다
        // 이 경우 자동 등록되는 Bean보다 수동 등록하는 Bean이 우선권을 가진다
        // 수동 Bean이 자동 Bean을 override 한다
        // 하지만 이건 개발자의 의도로 발생하는 경우보다, 여러 설정이 꼬여서 만들어진 경우가 많으므로
        // 고치기 어려운 버그가 만들어진다
        // 따라서 최근의 spring boot에서는 수동 Bean과 자동 Bean이 충돌하면 오류가 발생하도록 기본 값을 변경하였다
        // spring boot인 CoreApplication을 실행하면 충돌이 발생한다        
        // application.properties에
        // spring.main.allow-bean-definition-overriding=true 를 추가하면 더이상 오류가 발생하지 않는다
        // 해당 내용은 출력메세지에서 복붙 가능하다
    }
    
}

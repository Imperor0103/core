package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;    // spring으로 변환

// 전체 테스트: project(Alt + 1) > test 폴더 우클릭 -> Run 'All Tests'

// ctrl + E: 최근 열었던 파일 보여준다

// 애플리케이션에 대한 환경 구성은 여기서 한다
// 연극에서 배우 섭외 부분을 분리(배우 섭외는 여기서 한다)
@Configuration
public class AppConfig
{
    // @Bean memberService호출 -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // ctrl + N: 클래스 이동

    // factory 메서드를 통해 bean을 등록하는 방법

    // 예상 출력 메세지(순서는 보장하지 않는다)
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제 출력 메세지
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // 1번씩만 호출되는 이유?
    // spring은 어떤 방법을 써서라도 singleton을 보장해주므로
    // 새로 생성은 처음 1번만 되면서 그때만 메서드를 호출하기 때문에 메서드 내의 메세지는 1번만 호출된다
    // 그걸 관리하는게 spring의 @Configuration이다
    // 위에서 @Configuration을 comment 처리하고
    // ConfigurationSingletonTest.configurationTest을 우클릭 -> Run 하면
    // 오류는 생기지만, memberService, orderService, memberRepository 모두 다른 것들이 등록되는 것을 확인할 수 있다
    // spring bean이 아니라 직접 new로 만든 것이기 때문이다


    // spring으로 변환
    @Bean   
    public MemberService memberService()
    {
        // ctrl + alt + m: 리팩터링

        // soutm: sout + 호출한 함수 이름까지 자동완성
        System.out.println("AppConfig.memberService");

        // 생성자 주입: 생성자를 통해 memberRepository에 뭐가 들어갈지 선택
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        System.out.println("AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService()
    {
        System.out.println("AppConfig.orderService");

        // 마찬가지로 생성자 주입
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy()
    {
        // OCP원칙: 할인정책 변경시 OrderServiceImpl을 변경하지 않고 discountPolicy 메서드의 내부만 변경해주면 된다

        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

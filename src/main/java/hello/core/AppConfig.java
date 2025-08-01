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

// ctrl + E: 최근 열었던 파일 보여준다

// 애플리케이션에 대한 환경 구성은 여기서 한다
// 연극에서 배우 섭외 부분을 분리(배우 섭외는 여기서 한다)
@Configuration
public class AppConfig
{
    // spring으로 변환
    @Bean   
    public MemberService memberService()
    {
        // ctrl + alt + m: 리팩터링

        // 생성자 주입: 생성자를 통해 memberRepository에 뭐가 들어갈지 선택
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService()
    {
        // 마찬가지로 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy()
    {
        // OCP원칙: 할인정책 변경시 OrderServiceImpl을 변경하지 않고 discountPolicy 메서드의 내부만 변경해주면 된다

        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

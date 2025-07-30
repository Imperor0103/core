package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// ctrl + E: 최근 열었던 파일 보여준다

// 애플리케이션에 대한 환경 구성은 여기서 한다
// 연극에서 배우 섭외 부분을 분리(배우 섭외는 여기서 한다)
public class AppConfig
{
    public MemberService memberService()
    {
        // 생성자 주입: 생성자를 통해 memberRepository에 뭐가 들어갈지 선택
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService()
    {
        // 마찬가지로 생성자 주입
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}

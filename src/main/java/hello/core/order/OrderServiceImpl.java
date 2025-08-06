package hello.core.order;

import hello.core.discount.DiscountPolicy;

import hello.core.member.Member;
import hello.core.member.MemberRepository;


public class OrderServiceImpl implements OrderService
{
    // DIP 원칙 적용
    private final MemberRepository memberRepository;    // 인터페이스에만 의존한다(DIP 원칙)
    // 배우가 직접 담당 배우를 섭외하는 것과 같다(DIP 위반)
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP 원칙 적용
    private final DiscountPolicy discountPolicy;    // 인터페이스에만 의존한다(DIP 원칙)

    // 생성자를 통해 memberRepository와 discountPolicy에 뭐가 들어갈지 선택
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy)
    {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice)
    {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() { return memberRepository; }
}

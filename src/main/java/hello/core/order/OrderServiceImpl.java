package hello.core.order;

import hello.core.discount.DiscountPolicy;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// ctrl + shift + t: 해당 클래스의 테스트 파일 만든다

// 의존관계 자동 주입을 위해 @Component
@Component
//@RequiredArgsConstructor    // final이 붙은 필드의 생성자를 자동 생성해주는 lombok 기능, ctrl + f12를 통해 확인가능
public class OrderServiceImpl implements OrderService
{
    // DIP 원칙 적용
    private final MemberRepository memberRepository;    // 인터페이스에만 의존한다(DIP 원칙), 생성자 주입
    // 생성자 주입의 장점은
    // 1.불변하게 설계할 수 있다
    // 2.누락을 컴파일 오류로 쉽게 찾을 수 있다
    // 3.final을 쓸 수 있다
    // final을 사용하면 처음에 값할당을 해야하는데
    // = 으로 할당하든가
    // 생성자에 할당해야한다
    // 값 변경할 필요가 없는 것들은 final 사용하면 값을 할당하지 않았을 때 컴파일 오류를 발생시켜 바로 확인할 수 있다
    // 나머지 주입 방식(수정자, 메서드, 필드)은 생성자 이후에 호출되므로 필드에 final 키워드를 사용할 수 없다(java 문법)

//    private MemberRepository memberRepository;    // 수정자, 일반 메서드 주입을 위해 final 제거
//    @Autowired private MemberRepository memberRepository;    // 필드 주입은 쓰지마라, new로 생성해서 만들고 setter가 필요한데, 
//    new로 생성하는 것은 Autowired가 안된다! 
//    따라서 setter에 @Autowired를 하는 방식을 사용한다

    // 배우가 직접 담당 배우를 섭외하는 것과 같다(DIP 위반)
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP 원칙 적용
    private final DiscountPolicy discountPolicy;    // 인터페이스에만 의존한다(DIP 원칙), 생성자 주입
//    private DiscountPolicy discountPolicy;    // 수정자, 일반 메서드 주입을 위해 final 제거
//    @Autowired private DiscountPolicy discountPolicy;    // 필드 주입 비추천


//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository)
//    {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy)
//    {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자를 통해 memberRepository와 discountPolicy에 뭐가 들어갈지 선택
    // 의존관계 자동 주입을 위해 @Autowired

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy)
    {
        System.out.println("1. OrderServiceImpl.OrderserviceImpl");
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 주입 테스트
//    @Autowired
//    public void Init(MemberRepository memberRepository, DiscountPolicy discountPolicy)
//    {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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

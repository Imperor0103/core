package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary    // 의존관계 주입 시 우선 선택된다
// Primary를 사용하는 경우 예시
// 메인 데이터베이스가 90%, 보조 데이터베이스가 10% 라고 하면,
// 각각을 @Qualifier로 등록하면 너무 번거로워서
// 메인에 @Primary를 붙이면 기본적으로 메인 데이터베이스가 자동 선택되고,
// 보조 데이터베이스를 써야할 경우에만 @Qualifier를 붙이면 되므로(@Primary 보다 @Qualifier가 우선순위가 높기 때문),
// 번거로움이 많이 줄어든다
public class RateDiscountPolicy implements  DiscountPolicy
{
    private int discountPercent = 10;


    // ctrl + shift + t: 새로운 test 만들기
    @Override
    public int discount(Member member, int price)
    {
        if (member.getGrade() == Grade.VIP)
        {
            return price * discountPercent /  100;
        }
        else
        {
            return 0;
        }

    }
}

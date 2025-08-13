package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // custom annotation
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

package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberApp
{
    public static void main(String[] args)
    {
        // ctrl + / : annotation
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();    // AppConfig에서 가져온다

        // spring 변환
        // ApplicationContext: spring container
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Appconfig의 환경설정 정보를 이용하여 Appconfig에서 @Bean이 붙은 것들을 spring container에 객체 생성한다

        MemberService memberService =  applicationContext.getBean("memberService", MemberService.class);

        // 직접할당: DIP에 위배
        // MemberService memberService = new MemberServiceImpl();

        // ctrl + alt + v
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}

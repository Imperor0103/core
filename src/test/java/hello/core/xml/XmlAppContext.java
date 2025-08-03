package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// xml파일 생성할 때 우클릭 -> New -> XML Configuration File -> Spring Config 선택
// 줄바꿀때 ctrl + shift + enter하면 /> 자동 생성해서 줄 끝내고 다음줄로 커서 이동

public class XmlAppContext
{
    @Test
    void xmlAppContext()
    {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

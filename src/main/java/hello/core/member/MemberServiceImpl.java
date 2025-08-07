package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MemberServiceImpl implements MemberService
{
    // MemoryRepository에 대한 코드는 없다
    // MemberRepository 인터페이스만 존재한다
    private final MemberRepository memberRepository;    // 추상화에만 의존(DIP 원칙)

    // 생성자를 통해 memberRepository에 뭐가 들어갈지 선택
    // AppConfig를 사용할 때는 직접 @Bean을 설정 정보를 작성, 의존관계도 명시했다
    // AutoAppConfig를 사용할 때는 AutoAppConfig안에 설정 정보가 없어서 의존관계 주입도 이 클래스에서 해야 한다
    // 이때 @Autowired 키워드를 사용한다
    // @Autowired가 있으면 MemberRepository에 맞는 것을 찾아 와서 의존관계를 자동으로 연결해서 주입
    @Autowired // ac.getBean(MemberRepository.class) 처럼 기능한다
    public MemberServiceImpl(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member)
    {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) { return memberRepository.findById(memberId); }

    // 테스트 용도: memberRepository가 생성될 때 spring이 singleton을 어떻게 관리하는지 확인하기 위해
    public MemberRepository getMemberRepository() { return memberRepository; }
}

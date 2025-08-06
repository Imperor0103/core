package hello.core.member;

public class MemberServiceImpl implements MemberService
{
    // MemoryRepository에 대한 코드는 없다
    // MemberRepository 인터페이스만 존재한다
    private final MemberRepository memberRepository;    // 추상화에만 의존(DIP 원칙)

    // 생성자를 통해 memberRepository에 뭐가 들어갈지 선택
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

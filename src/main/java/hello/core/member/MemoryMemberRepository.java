package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// AutoAppConfig에서 자동 등록을 하기 위해 @Component 붙인다
@Component
public class MemoryMemberRepository implements MemberRepository
{
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member)
    {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId)
    {
        return store.get(memberId);
    }
}

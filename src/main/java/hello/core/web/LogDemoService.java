package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

// Spring은 request 스코프 빈을 관리하기 위해 ThreadLocal 이라는 기술을 사용합니다.
// ThreadLocal은 스레드마다 독립적인 저장소를 제공하는 특별한 자료구조입니다.
//
//Spring의 request 스코프 빈은 다음과 같은 방식으로 동작합니다:
//요청 시작: 클라이언트의 HTTP 요청이 들어오면 Spring은 MyLogger와 같은 request 스코프 빈을 생성합니다.
//
//스레드 저장소에 보관: Spring은 이 빈 인스턴스를 요청을 처리하는 현재 스레드의 ThreadLocal 저장소에 보관합니다.
// 마치 각 스레드가 자신만의 개인 사물함을 가지고 그 안에 요청 관련 객체를 넣어두는 것과 같습니다.
//
//동일 객체 접근: LogDemoController나 LogDemoService에서 ObjectProvider.getObject()를 호출할 때,
// Spring은 현재 요청을 처리하고 있는 스레드의 ThreadLocal 저장소를 확인하여 그 안에 보관된 MyLogger 인스턴스를 찾아 반환합니다.
//
//요청 종료: 요청 처리가 끝나면, Spring은 ThreadLocal 저장소에 있던 MyLogger 인스턴스를 제거합니다.
// 이러한 메커니즘 덕분에 동시에 여러 요청이 들어와도 각 스레드는 다른 스레드의 저장소에 접근하지 않고, 자신의 ThreadLocal에 있는 고유한 객체만 사용합니다.
// 결과적으로, 각 요청은 완전히 독립된 MyLogger 인스턴스를 가지고 작업을 수행하게 됩니다.
@Service
@RequiredArgsConstructor
public class LogDemoService
{
    private final MyLogger myLogger;

    public void logic(String id)
    {
        myLogger.log("service id =  " + id);
    }
}

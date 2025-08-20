package hello.core.web;


import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController
{
    private final LogDemoService logDemoService;

    // 기존에 발생한 문제:
    // 기존에 발생했던 문제는 스코프 불일치다
    // LogDemoController와 LogDemoService는 Spring 애플리케이션 시작 시점에 생성되는 싱글톤(Singleton) 빈이다
    // 반면에 MyLogger는 HTTP 요청이 들어올 때마다 생성되는 요청(Request) 스코프 빈이다
    // Spring이 애플리케이션 시작 시점에 싱글톤 빈을 초기화하면서 MyLogger 빈을 주입하려고 할 때,
    // MyLogger 빈은 아직 생성되지 않았기 때문에 주입할 수 없어 오류가 발생했던 것이다.
    // 마치 "아직 존재하지도 않는 택배를 지금 당장 보내라"고 하는 것과 같다
    private final ObjectProvider<MyLogger>  myLoggerProvider;
    // 1.ObjectProvider를 사용하여 spring 실행할때 request 스코프 빈을 생성
    // ObjectProvider는 "지금 당장 빈을 주입해달라"는 요청 대신, "빈을 찾아올 수 있는 방법(provider)"을 주입한다
    // 따라서 싱글톤 빈인 LogDemoController와 LogDemoService는 스프링 시작 시점에
    // MyLogger 인스턴스 자체가 아닌, MyLogger를 찾아서 가져올 수 있는 ObjectProvider를 의존성 주입받는다
    // ObjectProvider가 빈의 조회를 필요한 시점(HTTP 요청이 들어온 시점)까지 지연시켰기 때문에 스코프 불일치 문제를 해결한 것
    // 덕분에 싱글톤 빈과 요청 스코프 빈이 안전하게 협력할 수 있게 되었다
    // CoreApplication 실행하면 디버깅 비슷한 상태가 되는데
    // http://localhost:8080/log-demo 들어가서 F5 등 다른 입력을 주면
    // terminal에 요청이 출력된다!

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException
    {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();   //
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

        // 동시에 요청이 들어오더라도, 그 요청마다 객체를 따로 관리한다
        // 그것을 확인하기 위한 sleep
        Thread.sleep(10);

        logDemoService.logic("testID");
        return "OK";
    }
}

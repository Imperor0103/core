package hello.core.lifecycle;

// 테스트를 위한 가짜 네트워크

// java EE가 Eclipse 재단으로 넘어가면서 패키지가 javax -> jakarta로 바뀌었다
// 기능 차이는 없다
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient
{
    private String url;

    public NetworkClient()
    {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message)
    {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect()
    {
        System.out.println("close: " + url);
    }

    @PostConstruct
    // property의 초기화(의존관계 주입)가 끝나면 호출되는 메서드
    public void init() throws Exception
    {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close()
    {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

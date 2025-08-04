package hello.core.singleton;

public class SingletonService
{
    private static final SingletonService instrance = new SingletonService();
    public static SingletonService getInstance()
    {
        return instrance;
    }
    // private 생성자: 외부에서 접근을 막는다
    private SingletonService()
    {
    }
    // 단점
    // 1.구현하는 코드 자체가 많이 들어간다(위의 모든 내용이 필수)
    // 2.의존 관계상 클라이언트가 구체 클래스에 의존하여 DIP를 위반
    // 3.private 생성자를 사용하여 자식 클래스를 만들기 어렵다(유연성이 떨어진다)
    
    public void logic()
    {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

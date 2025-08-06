package hello.core.singleton;

// ctrl + shift + t -> Create New Test -> JUnit5 -> 새로운 이름의 클래스 생성 가능
public class StatefulService
{
    private int price; // 상태를 유지하는 필드 10000 -> 20000

    public void order(String name, int price)
    {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }
    public int getPrice()
    {
        return price;
    }
}

package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok을 사용하면 getter, setter 등의 코드를 작성하지 않아도 된다
@Getter
@Setter
@ToString
public class HelloLombok
{
    private  String name;
    private int age;

    public static void main(String[] args)
    {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");
//        String name = helloLombok.getName();
//        System.out.println("name = " + name);


        System.out.println("helloLombok = " + helloLombok);
    }
}

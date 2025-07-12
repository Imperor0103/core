package hello.core.member;


public class Member
{
    private Long id;
    private String Name;
    private Grade grade;

    // generate 단축키: alt + insert
    // generate를 통해 java class, interface, enum 등 생성한다
    public Member(Long id, String name, Grade grade)
    {
        this.id = id;
        Name = name;
        this.grade = grade;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public Grade getGrade()
    {
        return grade;
    }

    public void setGrade(Grade grade)
    {
        this.grade = grade;
    }
}

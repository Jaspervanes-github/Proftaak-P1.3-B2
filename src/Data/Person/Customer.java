package Data.Person;

public class Customer {

    private String name;
    private String age;

    public Customer(){
        this.name = "";
        this.age = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }
}

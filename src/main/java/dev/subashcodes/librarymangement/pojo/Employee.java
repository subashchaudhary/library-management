package dev.subashcodes.librarymangement.pojo;

public class Employee {

    private String name;
    private String address;
    private String phone;
    private String email;



    public Employee name(String name) {
        this.name = name;
        return this;
    }
    public Employee address(String address) {
        this.address = address;
        return this;
    }

    public Employee phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

}


 class Test {


    public Employee createEmployObj(){


        Employee emp = new Employee()
                .name("John Doe")
                .address("123 Main St")
                .phone("555-1234")
                .email("test@email.com");


        return emp;
    }
}

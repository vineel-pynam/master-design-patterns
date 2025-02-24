package creational_patterns.java.builder;

// Person Class
class Person{
    private String name;
    private Integer age;
    private String email;
    private String description;
    // more fields

    Person(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.description = builder.description;
        this.email = builder.email;
    }

    public String getName(){
        return this.name;
    }

    public Integer getAge(){
        return this.age;
    }


    public String getEmail(){
        return this.email;
    }

    public String getDescription(){
        return this.description;
    }

    public void display(){
        System.out.println(
            "[Name]: " + this.name + "\n" +
            "[Age]: " + this.age + "\n" +
            "[Email]: " + this.email + "\n" +
            "[Description]: " + this.description
        );
        System.out.println();
    }

    // Builder Class
    // Helps us to set values for Person. Gives flexibility to set only required values, remaining will be null or empty.
    public static class Builder{
        private String name;
        private Integer age;
        private String email;
        private String description;

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(Integer age){
            this.age = age;
            return this;
        }


        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Person build(){
            return new Person(this);
        }

    }

}

// Client
class SimpleBuilderPattern {
    public static void main(String args[]){

        // Person 1
        Person vineel = new Person.Builder()
                            .setName("Vineel Pynam")
                            .setAge(25)
                            .setDescription("A software Engineer")
                            .setEmail("vineel@gmail.com")
                            .build();

        vineel.display();

         // Person 2
        Person dhoni = new Person.Builder()
                            .setName("MS Dhoni")
                            .setAge(44)
                            .setDescription("Indian Cricketer")
                            .setEmail("dhoni@gmail.com")
                            .build();

        dhoni.display();
    }
}

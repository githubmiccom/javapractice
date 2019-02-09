package springbook.user.domain;

public class User {
    String id;
    String name;
    String password;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(String password){
        return this.password;
    }
}

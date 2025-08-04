package ru.stqa.mantis.model;

public record User(String username, String email) {
    public User(){
        this("", "");
    }

    public User withUsername(String username){
        return new User(username, this.email);
    }

    public User withEmail(String email){
        return new User(this.username, email);
    }

}

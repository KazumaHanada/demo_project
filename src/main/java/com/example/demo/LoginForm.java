package com.example.demo;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginForm implements Serializable {

    @NotBlank
    private String userName;
    public void setUserName(String userName){ this.userName = userName; }
    public String getUserName(){ return userName; }

    @NotBlank
    private String passWord;
    public void setPassWord(String passWord){ this.passWord = passWord; }
    public String getPassWord(){ return passWord; }
}

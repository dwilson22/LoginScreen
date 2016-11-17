package ca.danieljameswilson.loginscreen;

/**
 * Created by Daniel on 10/20/2016.
 */

public class LoginManager {
    private String username;
    private String password;

    public LoginManager(String username, String password){
        this.username=username;
        this.password=password;
    }

    public boolean isLoginSuccessful(){
        return(username.equals("admin") && password.equals("password"));
    }
}

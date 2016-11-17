package ca.danieljameswilson.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = "LoginApp";
    private int attempts;
    private Button loginButton;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLogin);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        attempts = 3;

       passwordEditText.setOnKeyListener(new View.OnKeyListener(){

           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if (event.getAction() == KeyEvent.ACTION_DOWN)
               {
                   switch (keyCode)
                   {
                       case KeyEvent.KEYCODE_DPAD_CENTER:
                       case KeyEvent.KEYCODE_ENTER:
                           loginButton.performClick();
                           return true;
                       default:
                           break;
                   }
               }
               return false;
           }
           // TODO Auto-generated method stub

       });
    }


    private View.OnClickListener onClickLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText userNameEditText = (EditText) findViewById(R.id.usernameEditText);
            TextView errorTextView = (TextView) findViewById(R.id.errorTextView);

            String username = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            LoginManager loginManager = new LoginManager(username, password);

            if(loginManager.isLoginSuccessful()){
                //success
                attempts = 3;
                errorTextView.setVisibility(View.INVISIBLE);

            }else{
                //failure
                attempts--;
                String errormsg = getString(R.string.errorMsg)+String.valueOf(attempts)+getString(R.string.attempts);
                errorTextView.setText(errormsg);
                errorTextView.setVisibility(View.VISIBLE);
                if(attempts==0){
                    loginButton.setVisibility(View.INVISIBLE);
                }
            }
        }
    };
}

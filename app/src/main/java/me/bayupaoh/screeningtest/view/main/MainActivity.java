package me.bayupaoh.screeningtest.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.util.AppConstant;
import me.bayupaoh.screeningtest.view.chooseventguest.ChooseEventGuessActivity;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_edt_name)
    EditText mEdtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_btn_next)
    public void onClickNext(){
        if (!TextUtils.isEmpty(mEdtName.getText().toString())) {
            if(isPalindrome(mEdtName.getText().toString())){
                Toast.makeText(getApplicationContext(),"Its Palindrome!!",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Not Palindrome!!",Toast.LENGTH_LONG).show();
            }

            Intent intent = new Intent(getApplicationContext(), ChooseEventGuessActivity.class);
            intent.putExtra(AppConstant.PARAM_NAME,mEdtName.getText().toString());
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.main_message_error),Toast.LENGTH_LONG).show();
        }
    }

    public boolean isPalindrome(String param){
        return param.replace(" ","").equalsIgnoreCase(new StringBuilder(param.replace(" ","")).reverse().toString())?true:false;
    }

}

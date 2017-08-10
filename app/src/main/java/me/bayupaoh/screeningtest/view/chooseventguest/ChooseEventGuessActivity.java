package me.bayupaoh.screeningtest.view.chooseventguest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.util.AppConstant;
import me.bayupaoh.screeningtest.view.event.EventActivity;
import me.bayupaoh.screeningtest.view.guest.GuestActivity;

public class ChooseEventGuessActivity extends AppCompatActivity {

    @BindView(R.id.chooseevent_txt_name)
    TextView mTxtName;
    @BindView(R.id.chooseevent_btn_event)
    TextView mBtnEvent;
    @BindView(R.id.chooseevent_btn_guest)
    TextView mBtnGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_event_guess);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(getIntent().getStringExtra(AppConstant.PARAM_NAME))){
            mTxtName.setText("nama saya : "+getIntent().getStringExtra(AppConstant.PARAM_NAME));
        }
    }

    @OnClick(R.id.chooseevent_btn_event)
    public void onClickEvent(){
        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
        startActivityForResult(intent,AppConstant.REQ_CODE_EVENT);
    }

    @OnClick(R.id.chooseevent_btn_guest)
    public void onClickGuest(){
        Intent intent = new Intent(getApplicationContext(), GuestActivity.class);
        startActivityForResult(intent,AppConstant.REQ_CODE_GUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppConstant.REQ_CODE_EVENT){
            if(resultCode == RESULT_OK){
                mBtnEvent.setText(data.getStringExtra(AppConstant.PARAM_EVENT));
            }
        }else if(requestCode == AppConstant.REQ_CODE_GUEST){
            if(resultCode == RESULT_OK){
                mBtnGuest.setText(data.getStringExtra(AppConstant.PARAM_GUEST_NAME));
                int date = Integer.parseInt(data.getStringExtra(AppConstant.PARAM_GUEST_BIRTHDAY).substring(9));

                if((date % 2 == 0) && (date % 3 == 0)){
                    Toast.makeText(getApplicationContext(),"iOS",Toast.LENGTH_LONG).show();
                }else if(date % 2 == 0){
                    Toast.makeText(getApplicationContext(),"Blackberry",Toast.LENGTH_LONG).show();
                }else if(date % 3 == 0){
                    Toast.makeText(getApplicationContext(),"Android",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Feature Phone",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

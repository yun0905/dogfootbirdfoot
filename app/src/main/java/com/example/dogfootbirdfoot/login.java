package com.example.dogfootbirdfoot;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    TextView mT_View1;
    TextView mT_View2;

    EditText mE_Text1;
    EditText mE_Text2;

    Spinner mSpinner;

    Button mBtn1;

    TextView mDebug_T_Name;
    TextView mDebug_T_Id;
    TextView mDebug_T_Dep;

    String id;
    String name;
    String dep;
    String[] department = new String[]{"학과 1", "학과 2", "학과 3", "학과 4", "학과 5", "학과 6"};
    Integer num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mT_View1 = (TextView) findViewById(R.id.tv1);
        mT_View2 = (TextView) findViewById(R.id.tv2);
        mBtn1 = (Button) findViewById(R.id.b1);
        mE_Text1 = (EditText) findViewById(R.id.et1);
        mE_Text2 = (EditText) findViewById(R.id.et2);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, department);
        mSpinner.setAdapter(adapter);
        mDebug_T_Id = (TextView) findViewById(R.id.id);
        mDebug_T_Name = (TextView) findViewById(R.id.name);
        mDebug_T_Dep = (TextView) findViewById(R.id.dep);


        /*--------------------로그인--------------------*/

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이름 에러 판별
                boolean nameError = true;
                try {
                    if (!mE_Text1.getText().toString().isEmpty()) {
                        nameError = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 학번 에러 판별
                boolean idError = true;
                try {
                    String inputText = mE_Text2.getText().toString().trim();
                    if (isNumeric(inputText)) {
                        int num = Integer.parseInt(inputText);

                        if (num >= 10000000 && num <= 99999999) {
                            idError = false;
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // 이름 에러
                if (nameError == true){
                    Toast.makeText(login.this, "이름을 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }

                // 학번 에러
                if (idError == true){
                    Toast.makeText(login.this, " 학번을 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }

                // 이름 학번 학과 저장
                if (nameError == false && idError == false){
                    name = mE_Text1.getText().toString();
                    mDebug_T_Name.setText(name);

                    id = mE_Text2.getText().toString().trim();
                    mDebug_T_Id.setText(id);

                    dep = mSpinner.getSelectedItem().toString();
                    mDebug_T_Dep.setText(dep);
                }
            }
        });
    }

    // 숫자인지 판별
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

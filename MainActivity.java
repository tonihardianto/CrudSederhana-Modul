package com.hrdapr.crudandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private EditText input;
    private Button btnCreate, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi component
        text = findViewById(R.id.text);
        input = findViewById(R.id.input);
        btnCreate = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().trim().equals("")){
                    if (!input.getText().toString().trim().equals("")){
                        new
                                CrudDao(MainActivity.this).createData(input.getText().toString());
                        input.setText("");
                        sukses("Create");
                    } else {
                        input.setError("Jangan Kosong Woy");
                    }
                } else {
                    Toast.makeText(MainActivity.this, " Text udah ada isinya, Silahkan Update/Delete",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().trim().equals("")){
                    if (!input.getText().toString().trim().equals("")){
                        new
                                CrudDao(MainActivity.this).updateData(input.getText().toString(),
                                text.getText().toString());
                        sukses("Update");
                    } else {
                        input.setError("Jangan Kosong Woy");
                    }
                } else {
                    Toast.makeText(MainActivity.this, " Text udah ada isinya, Silahkan Update/Delete",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text.getText().toString().trim().equals("")) {
                    new
                            CrudDao(MainActivity.this).deleteData(text.getText().toString());
                    input.setText("");
                    sukses("Delete");
                } else {
                    Toast.makeText(MainActivity.this, " Text udah ada isinya, Silahkan Update/Delete",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        showText();
    }
    private void showText(){
        text.setText(new CrudDao(this).getData());
    }
    private void sukses(String info){
        showText();
        Toast.makeText(MainActivity.this, info + "Berhasil", Toast.LENGTH_LONG);
    }
}



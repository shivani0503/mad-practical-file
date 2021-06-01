package com.example.app_uses_database_arushi_2817127;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText E1, E2;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        E1 = (EditText)findViewById(R.id.studname);
        E2 = (EditText)findViewById(R.id.studroll);
    }

    public void insert(View view) {
        mydb.insertdata(E1.getText().toString(), E2.getText().toString());
        Toast.makeText(this,"Data Inserted", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        E1.setText("");
        E2.setText("");
    }

    public void viewdata(View view) {
        Cursor c = mydb.getalldata();
        if(c.getCount()==0){
            showdialog("ALert", "No Data Found");
        }
        else{
            StringBuffer bf= new StringBuffer();
            while(c.moveToNext()){
                bf.append("Name:"+c.getString(0)+"\n");
                bf.append("Rollno:"+c.getString(1)+"\n");
            }
            showdialog("Data",bf.toString());
        }
    }
    public void showdialog(String title, String msg){
        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setCancelable(true);
        bu.setTitle(title);
        bu.setMessage(msg);
        bu.show();
    }
}

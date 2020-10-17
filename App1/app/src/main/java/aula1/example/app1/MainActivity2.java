package aula1.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    public static ArrayList<String> arraylist = new ArrayList<String>();
    public static HashMap<String,String> hashmap = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView list = (ListView)findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arraylist);
        list.setAdapter(adapter);
        Intent i = getIntent();
        System.out.println(" nao entrou");
        if(i.getStringExtra("action").equals("newContact")) {
            System.out.println("entrou");
            String Name= i.getStringExtra("Name");
            System.out.println(Name);
            String Number = i.getStringExtra("Number");
            adapter.add(Name);
            hashmap.put(Name,Number);
        }
    }
    public void back(View v){
        Intent i = new Intent(v.getContext(),MainActivity.class);
        startActivityForResult(i,1);
    }
}
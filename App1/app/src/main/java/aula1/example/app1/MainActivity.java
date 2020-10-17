package aula1.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dial(View v){
        TextView t1 = (TextView)findViewById(R.id.textView1);
        if(!(t1.getText().length()>17)){
            t1.append(((Button) v).getText());
        }
    }
    public void erase(View v){
        TextView t1 = (TextView)findViewById(R.id.textView1);
        if((t1.getText().length()>0)){
            t1.setText(t1.getText().subSequence(0,t1.getText().length()-1));
        }
    }
    public void popup(View v){
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        if(tv1.getText()!="") {
            ConstraintLayout popup = (ConstraintLayout)findViewById(R.id.Popup1);
            popup.setVisibility(View.VISIBLE);
        }
    }
    public void save(View v){
        EditText tb = (EditText)findViewById(R.id.editTextTextPersonName);
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        if(!tb.getText().toString().isEmpty()){
            Intent i = new Intent(v.getContext(),MainActivity2.class);
            i.putExtra("action", "newContact");
            i.putExtra("Name",tb.getText().toString());
            i.putExtra("Number", tv1.getText().toString());
            startActivityForResult(i,1);
        }else{

        }
    }
    public void contacts(View v){
        Intent i = new Intent(v.getContext(),MainActivity2.class);
        i.putExtra("action", "Contacts");
        startActivityForResult(i,1);
    }
}
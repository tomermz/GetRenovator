package com.example.getrenovator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }


    public void loginFunc(View view){

        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "login ok" , Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(MainActivity.this, "login fail" , Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }

    public void regFunc(View view) {

        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
        String confirmPass = ((EditText) findViewById(R.id.editTextTextPassword3)).getText().toString();

        if(!password.equals(confirmPass)){
            Toast.makeText(MainActivity.this, "The passwords must match.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "reg ok", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(MainActivity.this, "reg fail", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    public void addData(){

        String firstName = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextTextPersonName2)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();

        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
int selected=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=findViewById(selected);
        UserType type = UserType.valueOf(radioButton.getText().toString());

        Person person = new Person(firstName, lastName, email, phone, type);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(person.getPhone());

        myRef.setValue(person);
    }

    public void getData(){

        Person person = new Person();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(person.getMail());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);

                Toast.makeText(MainActivity.this , value.getFirst_name() , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

}
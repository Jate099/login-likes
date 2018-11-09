package icesi.movil.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {


    private Button btn_facebook;
    private Button btn_iniciarsesion;
    EditText et_correo;
    EditText et_contrasena;

    FirebaseDatabase db;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        btn_iniciarsesion=findViewById(R.id.btn_iniciar);
        et_correo=findViewById(R.id.et_correo);
        et_contrasena=findViewById(R.id.et_contrasena);

        if(auth.getCurrentUser() != null){
            Intent i = new Intent(this, Main.class);
            startActivity(i);
            finish();
            return;
        }


        btn_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUsuario(et_correo.getText().toString(), et_contrasena.getText().toString());

            }
        });
    }


    private void loginUsuario(String email, String pass){
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent i = new Intent(Login.this, Main.class);
                    startActivity(i);
                    finish();

                    Toast.makeText(Login.this, "Login correcto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

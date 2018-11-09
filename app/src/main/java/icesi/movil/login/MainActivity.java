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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import icesi.movil.login.model.Usuario;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseAuth auth;

    private EditText et_nombre;
    private EditText et_age;
    private EditText et_email;
    private EditText et_pass;
    private Button btn_reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(this, Main.class);
            startActivity(i);
            finish();
            return;
        }


//referencia una carperta... hijo

        et_nombre = findViewById(R.id.et_nombre);
        et_age = findViewById(R.id.et_age);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        btn_reg = findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario(
                        "", et_nombre.getText().toString(), et_age.getText().toString(), et_email.getText().toString()
                );
                registrarUsuario(usuario);
            }
        });

        //DatabaseReference reference= db.getReference().child("estudiantes");
        //reference.setValue("Cristian");

    }


    public void registrarUsuario(final Usuario usuario) {
        if (et_pass.getText().toString().length() <= 5) {
            Toast.makeText(this, "La contraseña debe ser de longitud 6 o mayor", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(usuario.getEmail(), et_pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    usuario.setUid(auth.getCurrentUser().getUid());
                    DatabaseReference reference = db.getReference().child("usuarios").child(usuario.getUid());
                    reference.setValue(usuario);
//para la otra actividad perfil


                    Intent i = new Intent(MainActivity.this, Main.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

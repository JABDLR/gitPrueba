package mx.ipn.escom.SEFMD.proyecto;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.Reference;

public class MainActivity extends AppCompatActivity {
    EditText jET_LI_UserName, jET_LI_Password;
    Button jBN_LI_Logear, jBN_LI_FgPassword, jBN_LI_Registrar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jET_LI_UserName = (EditText) findViewById(R.id.xET_LI_UserName);
        jET_LI_Password = (EditText) findViewById(R.id.xET_LI_Password);
        jBN_LI_Logear = (Button) findViewById(R.id.xBN_LI_Logear);
        jBN_LI_FgPassword = (Button) findViewById(R.id.xBN_LI_FgPassword);
        jBN_LI_Registrar = (Button) findViewById(R.id.xBN_LI_Registrar);

        jBN_LI_Logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUser();
                //Logear
            }
        });

        jBN_LI_FgPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRecuperarContrasena();
            }
        });

        jBN_LI_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistrar();
            }
        });
    }

    public void irRegistrar(){
        Intent viewRegistrar = new Intent(this, RegistroCliente.class);
        startActivity(viewRegistrar);
    }

    public void irRecuperarContrasena(){
        Intent viewRecuperarContrasena = new Intent(this, RecuperarContrasena.class);
        startActivity(viewRecuperarContrasena);
    }
    public void validarUser(){

        //Para Clientes
        try {
            CollectionReference clienteUs = db.collection("Clientes");

            clienteUs.whereEqualTo("UserName", jET_LI_UserName.getText().toString())
                    .whereEqualTo("Password", jET_LI_Password.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Toast.makeText(MainActivity.this, "Soy Cliente", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch (Exception e){ }
        //Para Repartidores
        try {
            CollectionReference clienteUs = db.collection("Repartidor");

            clienteUs.whereEqualTo("UserName", jET_LI_UserName.getText().toString())
                    .whereEqualTo("Password", jET_LI_Password.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Toast.makeText(MainActivity.this, "Soy Repartidor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch (Exception e){ }
        //Para Cajeros
        try {
            CollectionReference clienteUs = db.collection("Cajero");

            clienteUs.whereEqualTo("UserName", jET_LI_UserName.getText().toString())
                    .whereEqualTo("Password", jET_LI_Password.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Toast.makeText(MainActivity.this, "Soy Cajero", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch (Exception e){ }
        //Para Administrador
        try {
            CollectionReference clienteUs = db.collection("Administrador");

            clienteUs.whereEqualTo("UserName", jET_LI_UserName.getText().toString())
                    .whereEqualTo("Password", jET_LI_Password.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Toast.makeText(MainActivity.this, "Soy Administrador", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch (Exception e){ }
    }
}
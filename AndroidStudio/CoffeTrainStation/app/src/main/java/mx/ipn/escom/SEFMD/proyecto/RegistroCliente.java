package mx.ipn.escom.SEFMD.proyecto;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class RegistroCliente extends AppCompatActivity {
    EditText jET_RegistroC_Nambre, jET_RegistroC_ApellidoP, jET_RegistroC_UserName, jET_RegistroC_email, jET_RegistroC_phone, jET_RegistroC_password, jET_RegistroC_passwordCon;
    Button jBN_RegistroC_Registrar;
    CheckBox jCB_Confirm;

    String nombre, apellidoPaterno, userName, email, phone, password, passwordCon;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            //"(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 8 characters
            "$");

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);

        jET_RegistroC_Nambre = (EditText) findViewById(R.id.xET_RegistroC_Nambre);
        jET_RegistroC_ApellidoP = (EditText) findViewById(R.id.xET_RegistroC_ApellidoP);
        jET_RegistroC_UserName = (EditText) findViewById(R.id.xET_RegistroC_UserName);
        jET_RegistroC_email = (EditText) findViewById(R.id.xET_RegistroC_email);
        jET_RegistroC_phone = (EditText) findViewById(R.id.xET_RegistroC_phone);
        jET_RegistroC_password = (EditText) findViewById(R.id.xET_RegistroC_password);
        jET_RegistroC_passwordCon = (EditText) findViewById(R.id.xET_RegistroC_passwordCon);
        jBN_RegistroC_Registrar = (Button) findViewById(R.id.xBN_RegistroC_Registrar);
        jCB_Confirm = (CheckBox) findViewById(R.id.xCB_Confirm);

        jBN_RegistroC_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean passwordConfirm;

                getPersonalInformation();
                passwordConfirm = validarPassword();

                if(passwordConfirm){
                    //Acciones para registrar
                    registrarCliente();
                }

            }
        });
    }

    private void registrarCliente(){
        Map<String, Object> Usuarios = new HashMap<>();
        Usuarios.put("Nombre", nombre);
        Usuarios.put("Apellido Paterno", apellidoPaterno);
        Usuarios.put("UserName", userName);
        Usuarios.put("Email", email);
        Usuarios.put("Telefono", phone);
        Usuarios.put("Password", password);

        db.collection("Clientes").document(email).set(Usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegistroCliente.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistroCliente.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPersonalInformation(){
        nombre = jET_RegistroC_Nambre.getText().toString();
        apellidoPaterno = jET_RegistroC_ApellidoP.getText().toString();
        userName = jET_RegistroC_UserName.getText().toString();
        email = jET_RegistroC_email.getText().toString();
        phone = jET_RegistroC_phone.getText().toString();
        password = jET_RegistroC_password.getText().toString();
        passwordCon = jET_RegistroC_passwordCon.getText().toString();
    }

    private boolean validarPassword(){
        if (!PASSWORD_PATTERN.matcher(password).matches()){
            Toast.makeText(this, "La contraseña no cumple con los requisito", Toast.LENGTH_SHORT).show();
            return false;
        }else if(Objects.equals(password, passwordCon)){
            jCB_Confirm.setChecked(true);
            return true;
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void dataDuplicado(){
        //Para correo
        try {
            db.collection("Usuario").whereEqualTo("Email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistroCliente.this, "Email duplicado", Toast.LENGTH_SHORT).show();
                    }else {

                    }
                }
            });
        }catch (Exception e){ }
        //Para UserName
        try {
            db.collection("Usuario").whereEqualTo("UserName", userName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistroCliente.this, "UserName duplicado", Toast.LENGTH_SHORT).show();
                    }else {

                    }
                }
            });
        }catch (Exception e){ }

    }
}
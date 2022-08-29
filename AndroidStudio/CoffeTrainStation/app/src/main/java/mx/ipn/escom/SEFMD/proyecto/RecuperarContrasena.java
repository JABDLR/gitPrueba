package mx.ipn.escom.SEFMD.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecuperarContrasena extends AppCompatActivity {
    EditText jET_RecPasW_mail;
    Button jBN_RecPasW_RecPasW, jBN_RecPasW_Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        jET_RecPasW_mail = (EditText) findViewById(R.id.xET_RecPasW_mail);
        jBN_RecPasW_RecPasW = (Button) findViewById(R.id.xBN_RecPasW_RecPasW);
        jBN_RecPasW_Cancelar = (Button) findViewById(R.id.xBN_RecPasW_Cancelar);

        jBN_RecPasW_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irLogin();
            }
        });
    }

    public void irLogin(){
        Intent viewirLogin = new Intent(this, MainActivity.class);
        startActivity(viewirLogin);
    }
}
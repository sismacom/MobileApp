package spm.sismacom.frontend.appturimo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import spm.sismacom.frontend.appturimo.contoller.LoginController;
import spm.sismacom.frontend.appturimo.modelo.Cotizacion;
import spm.sismacom.frontend.appturimo.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);



        LoginController.cargaUsuarios();
        Button btnlogin=(Button)findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=""+((TextView)findViewById(R.id.txtNombreUsario)).getText();
                String pass=""+((TextView)findViewById(R.id.txtPassUsuario)).getText();
                Intent intento=null;
                Usuario user=LoginController.login(username,pass);
                if(user!=null){
                    if(user.getTipoUsuario().equalsIgnoreCase("administrador")){
                        intento= new Intent(MainActivity.this, AdminPanelAct.class);
                        startActivity(intento);
                    }else{
                        intento= new Intent(MainActivity.this, ClientePanelAct.class);
                        startActivity(intento);
                    }
                }else{

                    AlertDialog.Builder dialogo= new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Error de usuario");
                    dialogo.setMessage("Usuario y/o contraseña invalidos");
                    dialogo.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    dialogo.show();
                }
            }
        });
    }
}
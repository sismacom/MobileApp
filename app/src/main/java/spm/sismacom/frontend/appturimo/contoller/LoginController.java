package spm.sismacom.frontend.appturimo.contoller;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import spm.sismacom.frontend.appturimo.AdminPanelAct;
import spm.sismacom.frontend.appturimo.modelo.Usuario;

public class LoginController {

    private static List<Usuario> usuarios=new ArrayList<>();

    public static Usuario login(String nombre, String pass){
        Usuario usuariofinded=null;
        for (Usuario user :usuarios) {
            if(user.getNombreUsuario().equalsIgnoreCase(nombre) && user.getPassUsuario().equalsIgnoreCase(pass)){
                usuariofinded=user;
            }
        }
        return usuariofinded;
    }

    public static void cargaUsuarios(){
        usuarios.add(new Usuario("admin","1234","administrador"));
        usuarios.add(new Usuario("user","1234","cliente"));
    }


}

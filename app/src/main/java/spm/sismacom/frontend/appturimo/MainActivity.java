package spm.sismacom.frontend.appturimo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import spm.sismacom.frontend.appturimo.contoller.LoginController;
import spm.sismacom.frontend.appturimo.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final String TAG="login user...";
    private final int RC_SIGN_IN=9010;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        //LoginController.cargaUsuarios();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(String.valueOf(R.string.request_google))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();



        Button btnloginGoogle=(Button)findViewById(R.id.btnIngGoogle);
        btnloginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        Button btnlogin=(Button)findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=""+((TextView)findViewById(R.id.txtNombreUsario)).getText();
                String pass=""+((TextView)findViewById(R.id.txtPassUsuario)).getText();
                Toast.makeText(MainActivity.this, "Usuario: "+username+" Pass: "+pass,
                        Toast.LENGTH_SHORT).show();

                loginWithEmail(username,pass);


                /*String username=""+((TextView)findViewById(R.id.txtNombreUsario)).getText();
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
                }*/
            }
        });
    }

    private void loginWithEmail(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intento= new Intent(MainActivity.this, ClientePanelAct.class);
                            startActivity(intento);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intento= new Intent(MainActivity.this, ClientePanelAct.class);
                            startActivity(intento);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //updateUI(null);
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

    private void cerrarSesionFirebase(){
        FirebaseAuth.getInstance().signOut();

    }

}
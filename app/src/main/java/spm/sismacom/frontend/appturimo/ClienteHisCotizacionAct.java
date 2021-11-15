package spm.sismacom.frontend.appturimo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClienteHisCotizacionAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);



        Button regresarCliente=(Button)findViewById(R.id.regresarCliente);

        regresarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasartoHist =new Intent(ClienteHisCotizacionAct.this,ClientePanelAct.class);
                startActivity(pasartoHist);
            }
        });

    }
}

package spm.sismacom.frontend.appturimo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spm.sismacom.frontend.appturimo.adaptadores.AdaptadorCotizacion;
import spm.sismacom.frontend.appturimo.modelo.Cotizacion;
import spm.sismacom.frontend.appturimo.modelo.DestinoTuristico;
import spm.sismacom.frontend.appturimo.utils.DatePicker;
import spm.sismacom.frontend.appturimo.utils.SpinAdapter;

public class ClientePanelAct<i> extends AppCompatActivity {

    private DestinoTuristico destinoSeleccionado;
    ArrayList<Cotizacion> registros = new ArrayList<Cotizacion>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_panel);
        ArrayList<DestinoTuristico> destinos = new ArrayList<>();
        for(int i = 1; i <= 10 ; i++){
            destinos.add(new DestinoTuristico("Destino "+ i, 20000*i));
        }

        Button verHistorial = (Button) findViewById(R.id.btnverhistorial);
        verHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verHistRecycler = new Intent (ClientePanelAct.this,ClienteHisCotizacionAct.class);
                startActivity(verHistRecycler);
            }
        });



        Button cotizar = (Button) findViewById(R.id.btnCotizar);
        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularValorTotal();
            }
        });


        EditText edtcheckin = (EditText) findViewById(R.id.edtCheckIn);
        edtcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swhowDatePicker(edtcheckin);
            }
        });

        EditText edtcheckout = (EditText) findViewById(R.id.edtCheckOut);
        edtcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swhowDatePicker(edtcheckout);
            }
        });


        SpinAdapter adapter = new SpinAdapter(ClientePanelAct.this,
                android.R.layout.simple_spinner_item,
                destinos);
        Spinner selDestino = (Spinner) findViewById(R.id.spinnerDestino);
        selDestino.setAdapter(adapter);

        selDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                destinoSeleccionado = adapter.getItem(position);
                // Here you can do the action you want to...
                //Toast.makeText(ClientePanelAct.this,  "\nName: " + destino,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        AdaptadorCotizacion adaptadorlistaCotizacion = new AdaptadorCotizacion(this, R.layout.layout_cliente_historial_item,
                registros);

        RecyclerView listaCotizaciones = (RecyclerView) findViewById(R.id.lista_hist_recycler);
        listaCotizaciones.setAdapter(adaptadorlistaCotizacion);
        listaCotizaciones.setLayoutManager(new LinearLayoutManager(this)); // gestiona como se va a reciclar el recylcer view
        listaCotizaciones.setHasFixedSize(true);



    }

    private void calcularValorTotal() {
        double total = 0;
        TextView tv=(TextView) findViewById(R.id.edtNumeroPeronas);
        String texto=tv.getText().toString().trim();
        if(!texto.equalsIgnoreCase("")){
            int numPersonas = Integer.parseInt(texto);
            total= total+destinoSeleccionado.getValor()*numPersonas;

            //Toast.makeText(ClientePanelAct.this,  "\nTotal Cotizacion: $" + total,Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialogo= new AlertDialog.Builder(ClientePanelAct.this);
            dialogo.setTitle("Cotizacion: ");
            dialogo.setMessage("Total Cotizacion: $" + total);
            dialogo.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            dialogo.show();
            EditText fsalida=(EditText) findViewById(R.id.edtCheckOut);
            String salidaString = (String) fsalida.getText().toString();
            EditText fentrada=(EditText) findViewById(R.id.edtCheckIn);
            String entradaString = (String) fentrada.getText().toString();
            Spinner destinoSpinner=(Spinner) findViewById(R.id.spinnerDestino);
            String destinoString = (String) destinoSpinner.getSelectedItem().toString();
            Cotizacion cot =new Cotizacion(salidaString, entradaString,  destinoSeleccionado.getNombreDestino(), numPersonas+"",total);
            registros.add(cot);


        }else{
            Toast.makeText(ClientePanelAct.this,  "Error verifique los campos!!!",Toast.LENGTH_SHORT).show();
        }

    }

    private void swhowDatePicker(EditText edt) {
        DatePicker dapic = DatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int year, int mes, int dia) {
                final String selectedDate = dia + " / " + (mes + 1) + " / " + year;

                edt.setText(selectedDate);
            }
        });
        dapic.show(getSupportFragmentManager(), "datePicker");
    }


}
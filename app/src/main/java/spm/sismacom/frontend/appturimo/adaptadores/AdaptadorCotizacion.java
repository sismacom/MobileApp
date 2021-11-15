package spm.sismacom.frontend.appturimo.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import spm.sismacom.frontend.appturimo.CustomOnClickListener;
import spm.sismacom.frontend.appturimo.modelo.Cotizacion;
import spm.sismacom.frontend.appturimo.modelo.CotizacionViewHolder;

public class AdaptadorCotizacion extends RecyclerView.Adapter<CotizacionViewHolder> {

    final private Context contexto;
    final private List<Cotizacion> cotizaciones;
    final private LayoutInflater inflater;
    //final private CustomOnClickListener customClickListener;
    final private int layoutSeleccionado;

    public AdaptadorCotizacion(Context contexto, int layout, ArrayList<Cotizacion> cotizaciones) {
        this.contexto = contexto;
        this.layoutSeleccionado = layout;
        this.cotizaciones = cotizaciones;
        this.inflater = LayoutInflater.from(contexto);
        //this.customClickListener = customClickListener;
    }

    @NonNull
    @Override
    public CotizacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem = inflater.inflate(layoutSeleccionado, null);
        return new CotizacionViewHolder(vistaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CotizacionViewHolder holder, int position) {
        Cotizacion quote = cotizaciones.get(position);
        holder.getTextresult().setText(quote.Mensaje());

    }

    @Override
    public int getItemCount() {
        return cotizaciones.size();
    }
}

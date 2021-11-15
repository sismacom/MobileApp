package spm.sismacom.frontend.appturimo.modelo;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import spm.sismacom.frontend.appturimo.R;

public class CotizacionViewHolder extends RecyclerView.ViewHolder {

    private final TextView Textresult;


    public CotizacionViewHolder(@NonNull View itemView) {
        super(itemView);
        Textresult = (TextView) itemView.findViewById(R.id.recipiente_cotizacion);
    }



    public TextView getTextresult() {
        return Textresult;
    }
}

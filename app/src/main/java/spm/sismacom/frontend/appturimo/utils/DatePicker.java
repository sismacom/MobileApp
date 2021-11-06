package spm.sismacom.frontend.appturimo.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment {

    private int dia;
    private int mes;
    private int year;

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePicker newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePicker fragment = new DatePicker();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Calendar cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), listener, year, mes, dia);
    }

}

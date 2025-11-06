package com.fmu.taskflow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fmu.taskflow.DAO.AppDatabase;
import com.fmu.taskflow.model.Tarefa;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastrarTarefaActivity extends AppCompatActivity {
    private TextInputLayout tipDescricao;
    private TextView txtData;
    private TextView txtHora;
    private Button btnSalvar;
    private Calendar dataHora = Calendar.getInstance();

    public boolean validarDescricao(){
        if (tipDescricao.getEditText().getText().toString().trim().equals("")){
            tipDescricao.setError("A descrição não pode estar VAZIA");
            return false;
        }
        tipDescricao.setError(null);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tipDescricao = (TextInputLayout) findViewById(R.id.tip_descricao);
        txtData = (TextView) findViewById(R.id.txt_data);
        txtHora = (TextView) findViewById(R.id.txt_hora);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CadastrarTarefaActivity.this, d,
                        dataHora.get(Calendar.YEAR),
                        dataHora.get(Calendar.MONTH),
                        dataHora.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(CadastrarTarefaActivity.this, t,
                        dataHora.get(Calendar.HOUR),
                        dataHora.get(Calendar.MINUTE),
                        true)
                        .show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarDescricao()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setDescricao(tipDescricao.getEditText().getText().toString().trim());
                    tarefa.setDataHora(dataHora.getTimeInMillis());
                    AppDatabase.getAppDatabase(CadastrarTarefaActivity.this).tarefaDAO().inserir(tarefa);
                    Intent intent = new Intent();
                    intent.putExtra("Resposta", "OK");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dataHora.set(Calendar.YEAR, year);
            dataHora.set(Calendar.MONTH, month);
            dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
            txtData.setText(sdf.format(dataHora.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dataHora.set(Calendar.HOUR, hourOfDay);
            dataHora.set(Calendar.MINUTE, minute);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            txtHora.setText(sdf.format(dataHora.getTime()));
        }
    };


}
package com.fmu.taskflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fmu.taskflow.model.Tarefa;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AlterarTarefaActivity extends AppCompatActivity {
    private TextInputLayout tilAlterarDescricao;
    private TextView txtAlterarData;
    private TextView txtAlterarHora;
    private Switch swtRealizado;
    private Button btnAlterar;
    private Calendar dataHora = Calendar.getInstance();
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Bundle args = intent.getExtras();


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alterar_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tilAlterarDescricao = (TextInputLayout) findViewById(R.id.til_alterar_descricao);
        txtAlterarData = (TextView) findViewById(R.id.txt_AlterarData);
        txtAlterarHora = (TextView) findViewById(R.id.txt_AlterarHora);
        swtRealizado = (Switch) findViewById(R.id.swtRealizado);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);

    }
}
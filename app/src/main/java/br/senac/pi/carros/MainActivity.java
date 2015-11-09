package br.senac.pi.carros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ThreadPoolExecutor;

import br.senac.pi.carros.domain.Carro;
import br.senac.pi.carros.domain.CarrosDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCadastrarCarro = (Button) findViewById(R.id.btnCadastrarCarro);
        btnCadastrarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNomeCarro = (EditText) findViewById(R.id.edtNomeCarro);
                EditText edtMarcaCarro = (EditText) findViewById(R.id.edtMarcaCarro);
                String nomeCarro = edtNomeCarro.getText().toString();
                String marcaCarro = edtMarcaCarro.getText().toString();
                Carro carro = new Carro();
                carro.setNome(nomeCarro);
                carro.setMarca(marcaCarro);
                CarrosDB carrosDB = new CarrosDB(MainActivity.this);
                if(carrosDB.save(carro) != -1) {
                    Toast.makeText(MainActivity.this, getString(R.string.sucesso), Toast.LENGTH_LONG).show();
                    edtNomeCarro.setText("");
                    edtMarcaCarro.setText("");
                    edtNomeCarro.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

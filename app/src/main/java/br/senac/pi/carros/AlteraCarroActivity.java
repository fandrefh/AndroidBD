package br.senac.pi.carros;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.senac.pi.carros.domain.Carro;
import br.senac.pi.carros.domain.CarrosDB;

public class AlteraCarroActivity extends AppCompatActivity {

    private CarrosDB carrosDB;
    private SQLiteDatabase db;
    private EditText edtAlteraNomeCarro, edtAlteraMarcaCarro;
    private TextView txtIdCarro;
    private String id;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_carro);
        id = getIntent().getStringExtra("id");
        carrosDB = new CarrosDB(this);
        txtIdCarro = (TextView) findViewById(R.id.txtIdCarro);
        edtAlteraNomeCarro = (EditText) findViewById(R.id.edtAlteraNomeCarro);
        edtAlteraMarcaCarro = (EditText) findViewById(R.id.edtAlteraMarcaCarro);
        cursor = carregaCarro(Integer.parseInt(id));
        txtIdCarro.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        edtAlteraNomeCarro.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtAlteraMarcaCarro.setText(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
        findViewById(R.id.btnAlterarCarro).setOnClickListener(alterarCarro());
    }

    private Cursor carregaCarro(int id) {
        db = carrosDB.getWritableDatabase();
        String[] campos = {"_id", "nome", "marca"};
        String whereArgs = String.valueOf(id);
        cursor = db.query("carro", campos, whereArgs, null, null, null, null);
        if(cursor!= null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    private View.OnClickListener alterarCarro() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = carrosDB.getWritableDatabase();
                ContentValues values = new ContentValues();
                String whereArgs = id;
                Log.i("curso", "ID capturado: " + id);
                values.put("nome", edtAlteraNomeCarro.getText().toString());
                values.put("marca", edtAlteraMarcaCarro.getText().toString());
                db.update("carro", values, "_id = " + whereArgs, null);
                db.close();
            }
        };
    }
}

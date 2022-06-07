package com.example.professorallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityTela2 extends AppCompatActivity {
    EditText nomeDept;
    DepartamentoService service;
    DepartamentoResponse departamentoResponseAtualizado;

    private EditText etIdDepartamento;
    private Button btDeletar;
    private Button botaoSalvar;
    private Button botaoEditar;
    private ListView lvListaDepartamento;
    private List<String> lista;
    private ArrayAdapter<String> adapter;
    private DepartamentoService requestDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new RetrofitConfig().criarService();

        lista = new ArrayList<>();


        nomeDept = findViewById(R.id.etNomeDepartamento);
        etIdDepartamento = findViewById(R.id.etIdDepartamento);
        botaoSalvar = findViewById(R.id.btSalvar);
        botaoEditar = findViewById(R.id.btEditar);
        btDeletar = findViewById(R.id.btDelete);
        lvListaDepartamento = findViewById(R.id.lvListaDepartamento);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        lvListaDepartamento.setAdapter(adapter);

        requestDepartamento = new RetrofitConfig().criarService();

        criarDepartamento();
        alterarDepartamento();
        deleteDepartamento();
        executarGetAll();
    }

        private void criarDepartamento() {


        botaoSalvar.setOnClickListener(view -> {

            String conteudo = nomeDept.getText().toString();

            DepartamentoPost novoDepartamento = new DepartamentoPost();
            novoDepartamento.setName(conteudo);

            service.createRequest(novoDepartamento).enqueue(new Callback<DepartamentoResponse>() {
                @Override
                public void onResponse(Call<DepartamentoResponse> call, Response<DepartamentoResponse> response) {
                    departamentoResponseAtualizado = response.body();

                    String id = Integer.toString(departamentoResponseAtualizado.getId());
                    etIdDepartamento.setText(id);
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<DepartamentoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void alterarDepartamento() {

        botaoEditar.setOnClickListener(view -> {
            String editarDepartamento = nomeDept.getText().toString();
            String idDigitadoPeloUsuario = etIdDepartamento.getText().toString();
            DepartamentoPost alterarNomeDepartamento = new DepartamentoPost();
            alterarNomeDepartamento.setName(editarDepartamento);

            int idDepartamento = Integer.parseInt(idDigitadoPeloUsuario);



            service.createRequestPut(alterarNomeDepartamento, idDepartamento)
                    .enqueue(new Callback<DepartamentoResponse>() {
                        @Override
                        public void onResponse(Call<DepartamentoResponse> call, Response<DepartamentoResponse> response) {
                            Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<DepartamentoResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();

                        }

                    });
        });

    }
    private void deleteDepartamento() {


        btDeletar.setOnClickListener(view -> {
            String deleteDepartamentoId = etIdDepartamento.getText().toString();
            int idRecebido = Integer.parseInt(deleteDepartamentoId);


            service.deleteRequest(idRecebido).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Toast.makeText(getApplicationContext(), "Departamento deletado!", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na Request!", Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    private void executarGetAll(){
        requestDepartamento.createRequestGetAll().enqueue(new Callback<List<DepartamentoResponse>>(){
            @Override
            public void onResponse (Call<List<DepartamentoResponse>> call, Response<List<DepartamentoResponse>> response){
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                List<DepartamentoResponse> departamentoLista = response.body();
                for (DepartamentoResponse departamento : departamentoLista){
                    Log.i(">>>Resultado", departamento.getId() + "  " + departamento.getName());
                    lista.add(departamento.getName());

                }

                adapter.notifyDataSetChanged();

            }

}
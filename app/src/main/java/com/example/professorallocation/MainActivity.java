package com.example.professorallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText nomeCurso;
    CursoService service;
    CursoResponse cursoResponseAtualizado;

    private EditText etIdCurso;
    private Button btDeletar;
    private Button botaoSalvar;
    private Button botaoEditar;
    private CursoService requestCurso;
    private ListView lvListaCurso;
    private List<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CursoPost cursoPost = new CursoPost();
        //cursoPost.setName("Nome do Curso - Ane 26/05");

        service = new RetrofitConfig()
                .criarService();

        lista = new ArrayList<>();


        nomeCurso = findViewById(R.id.etNomeCurso);
        etIdCurso = findViewById(R.id.etIdCurso);
        botaoSalvar = findViewById(R.id.btSalvar);
        botaoEditar = findViewById(R.id.btEditar);
        btDeletar = findViewById(R.id.btDelete);
        lvListaCurso = findViewById(R.id.lvListaCurso);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        lvListaCurso.setAdapter(adapter);

        requestCurso = new RetrofitConfig()
                .criarService();

        criarCurso();
        alterarCurso();
        deleteCurso();
        executarGetAll();


    }

    private void criarCurso() {


        botaoSalvar.setOnClickListener(view -> {

            String conteudo = nomeCurso.getText().toString();

            CursoPost novoCurso = new CursoPost();
            novoCurso.setName(conteudo);

            service.createRequest(novoCurso).enqueue(new Callback<CursoResponse>() {
                @Override
                public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                    cursoResponseAtualizado = response.body();

                    String id = Integer.toString(cursoResponseAtualizado.getId());
                    etIdCurso.setText(id);
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                    //tvDescricaoCurso.setText("O curso que foi cadastrado: \n" + cursoResponseAtualizado.toString());
                }

                @Override
                public void onFailure(Call<CursoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void alterarCurso() {

        botaoEditar.setOnClickListener(view -> {
            String editarCurso = nomeCurso.getText().toString();
            String idDigitadoPeloUsuario = etIdCurso.getText().toString();
            CursoPost alterarNomeCurso = new CursoPost();
            alterarNomeCurso.setName(editarCurso);

            int idCurso = Integer.parseInt(idDigitadoPeloUsuario);

            // int idCurso = cursoResponseAtualizado.getId();

            service.createRequestPut(alterarNomeCurso, idCurso)
                    .enqueue(new Callback<CursoResponse>() {
                        @Override
                        public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                            Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<CursoResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();

                        }

                    });
        });

    }

    private void executarGetAll(){
        requestCurso.createRequestGetAll().enqueue(new Callback<List<CursoResponse>>(){
            @Override
            public void onResponse (Call<List<CursoResponse>> call, Response<List<CursoResponse>> response){
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();

                List<CursoResponse> cursoLista = response.body();
                for (CursoResponse curso : cursoLista){
                    Log.i(">>>Resultado", curso.getId() + "  " + curso.getName());
                lista.add(curso.getName());

                }

                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure (Call<List<CursoResponse>> call, Throwable t){
                Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void deleteCurso() {


        btDeletar.setOnClickListener(view -> {
            String deleteCursoId = etIdCurso.getText().toString();
            int idRecebido = Integer.parseInt(deleteCursoId);


            service.deleteRequest(idRecebido).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Toast.makeText(getApplicationContext(), "Curso deletado!", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na Request!", Toast.LENGTH_LONG).show();
                }
            });

        });

    }
}

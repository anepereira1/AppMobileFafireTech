package com.example.professorallocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoService {
    @POST("departments")
    Call<DepartamentoResponse> createRequest(@Body DepartamentoPost departamentoPost);

//    @GET("courses")
//    Call<List<Curso>> getAllCourse();

    @PUT("departments/{department_id}")
    Call<DepartamentoResponse> createRequestPut(@Body DepartamentoPost departamentoPut, @Path("department_id") int id);

    @DELETE("departmets/{departament_id}")
    Call <Object> deleteRequest(@Path("department_id") int id);

    @GET("departments")
    Call<List<DepartamentoResponse>> createRequestGetAll();

}
}

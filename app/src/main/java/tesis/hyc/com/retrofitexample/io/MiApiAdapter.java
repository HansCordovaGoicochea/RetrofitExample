package tesis.hyc.com.retrofitexample.io;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tesis.hyc.com.retrofitexample.Global;


public class MiApiAdapter {

    private static MiApiService API_SERVICE;

    public static MiApiService getApiService(){

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // Agregamos logging como ultima intercepcion
        httpClient.addInterceptor(logging); //- esta es la linea importante

        String baseUrl = Global.BASE_URL;

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(MiApiService.class);
        }


        return API_SERVICE;
    }

}

package tesis.hyc.com.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tesis.hyc.com.retrofitexample.io.MiApiAdapter;
import tesis.hyc.com.retrofitexample.io.response.CustomerResponse;
import tesis.hyc.com.retrofitexample.model.Customer;

import org.mindrot.jbcrypt.BCrypt;


public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnGet);
        txt = (TextView) findViewById(R.id.txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerCliente();
            }
        });
    }

    private void login(ArrayList<Customer> customers) {
        String pass = "123456789";
//        String hash = "$2y$10$cmhjBxhi6RwY14IvTxKHHOsiuUh6vjABRByN7P0ed9f.duD.k73QG".replaceFirst("2y", "2a");

        for (Customer member : customers){
            //Ver esta referecia para poder comparar las claves //libs/bcrypt
            //https://stackoverflow.com/questions/44614380/how-can-i-make-bcrypt-in-php-and-jbcrypt-in-java-compatible
            //https://github.com/patrickfav/bcrypt
            String pass_hash = member.passwd.replaceFirst("2y", "2a");
            boolean isSuccessful = BCrypt.checkpw(pass, pass_hash);
            if (isSuccessful){
                Log.e("asdas", "Login correcto");
            }else{
                Log.e("asdas", "Login incorrecto");
            }

        }


    }

    private void obtenerCliente() {
        Call<CustomerResponse> call = MiApiAdapter.getApiService().getCustomerDetails("full",  "10737149752", "JSON", Global.API_KEY, 1);
        call.enqueue(new ResponsablesCallback());
    }

    class ResponsablesCallback implements Callback<CustomerResponse> {

        @Override
        public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
            Log.e("asdas", response.toString());
            if(response.isSuccessful()){
                CustomerResponse customerResponse = response.body();
                login(customerResponse.getCustomers());
            }else{

                Toast.makeText(MainActivity.this, "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CustomerResponse> call, Throwable t) {
            Log.e("Error-ache", t.getLocalizedMessage());
            Toast.makeText(MainActivity.this, "Error: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}

package com.example.android.sep;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android.sep.app.App_Controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.android.sep.loginActivity.TAG_ALAMAT;
import static com.example.android.sep.loginActivity.TAG_EMAIL;
import static com.example.android.sep.loginActivity.TAG_ID_PENGGUNA;
import static com.example.android.sep.loginActivity.TAG_KATA_SANDI;
import static com.example.android.sep.loginActivity.TAG_NAMA_LENGKAP;
import static com.example.android.sep.loginActivity.TAG_NAMA_PENGGUNA;
import static com.example.android.sep.loginActivity.my_shared_preferences;
import static com.example.android.sep.loginActivity.session_status;


public class profilActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    int success;
    private static final String TAG = daftarActivity.class.getSimpleName();
    private String url1 = Server.URL + "profil.php";
    private String url2 = Server.URL + "edit_profil.php";


    private final static String TAG_SUCCESS = "success";

    SharedPreferences sharedPreferences;
    String tag_json_obj = "json_obj_req";
    ConnectivityManager connec;
    Boolean session = false;
    String nama_pengguna, nama_lengkap,  alamat, kata_sandi, email, id_pengguna;
    EditText namaLengkap2, namaPengguna2, alamat2, email2, kataSandi2;
    Button edit, simpan, batal;
    TextView txtNamaLengkap, txtNamaPengguna, txtAlmat, txtEmail, txtKataSandi;
    LinearLayout satu, dua;

    @SuppressLint({"CutPasteId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profil);

        satu = findViewById(R.id.linear1);
        dua = findViewById(R.id.linear2);

        namaLengkap2 = (EditText) findViewById(R.id.namaLengkap1);
        namaPengguna2 = (EditText) findViewById(R.id.namaPengguna1);
        alamat2 = (EditText) findViewById(R.id.alamat1);
        email2 = (EditText) findViewById(R.id.email1);
        kataSandi2 = (EditText) findViewById(R.id.kataSandi1);

        edit =(Button) findViewById(R.id.buttonEdit);
        simpan = (Button) findViewById(R.id.buttonSimpan);
        batal = (Button) findViewById(R.id.buttonBatal);


        txtNamaLengkap = (TextView)findViewById(R.id.textView38);
        txtNamaPengguna = (TextView)findViewById(R.id.textView45);
        txtAlmat = (TextView)findViewById(R.id.textView50);
        txtEmail = (TextView)findViewById(R.id.textView48);
        txtKataSandi = (TextView)findViewById(R.id.textView51);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        nama_pengguna = sharedPreferences.getString(TAG_NAMA_PENGGUNA, null);
        nama_lengkap = sharedPreferences.getString(TAG_NAMA_LENGKAP, null);
        alamat = sharedPreferences.getString(TAG_ALAMAT, null);
        kata_sandi = sharedPreferences.getString(TAG_KATA_SANDI, null);
        email = sharedPreferences.getString(TAG_EMAIL, null);
        id_pengguna = sharedPreferences.getString(TAG_ID_PENGGUNA, null);


        txtNamaLengkap.setText(nama_lengkap);
        txtNamaPengguna.setText(nama_pengguna);
        txtAlmat.setText(alamat);
        txtEmail.setText(email);
        txtKataSandi.setText(kata_sandi);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("EDIT");
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("BATAL");
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_lengkap = namaLengkap2.getText().toString();
                String nama_pengguna = namaPengguna2.getText().toString();
                String alamat = alamat2.getText().toString();
                String email = email2.getText().toString();
                String kata_sandi = kataSandi2.getText().toString();
                connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                {
                    if (connec.getActiveNetworkInfo()   !=null
                            && connec.getActiveNetworkInfo() .isAvailable()
                            && connec.getActiveNetworkInfo(). isAvailable()){
                        simpan (id_pengguna, nama_lengkap, nama_pengguna, alamat, email, kata_sandi);

                    }else{
                        Toast.makeText(getApplicationContext(), "Tidak Ada Konneksi Internet", Toast.LENGTH_LONG).show();
                    }

                }
                //menyimpan ke session
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TAG_NAMA_LENGKAP, nama_lengkap);
                editor.putString(TAG_NAMA_PENGGUNA, nama_pengguna);
                editor.putString(TAG_ALAMAT, alamat);
                editor.putString(TAG_EMAIL, email);
                editor.putString(TAG_KATA_SANDI, kata_sandi);

                editor.commit();
            }
        });

        connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (connec.getActiveNetworkInfo() !=null
                    && connec.getActiveNetworkInfo().isAvailable()
                    && connec.getActiveNetworkInfo().isAvailable()){
                tampilkanData(id_pengguna);
            }else{
                Toast.makeText(getApplicationContext(), "Tidak Ada Koneksi Internet", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void tampilkanData (final String id_pengguna){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mengambil Data");
        showDialog();

        StringRequest request= new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Respon Data:" + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    String nama_lengkap = jObj.getString(TAG_NAMA_LENGKAP);
                    String nama_pengguna = jObj.getString(TAG_NAMA_PENGGUNA);
                    String alamat = jObj.getString(TAG_ALAMAT);
                    String email = jObj.getString(TAG_EMAIL);
                    String kata_sandi = jObj.getString(TAG_KATA_SANDI);

                    txtNamaLengkap.setText(nama_lengkap);
                    txtNamaPengguna.setText(nama_pengguna);
                    txtAlmat.setText(alamat);
                    txtEmail.setText(email);
                    txtKataSandi.setText(kata_sandi);

                    Log.e("Sukses Ambil Data", jObj.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
                    protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("Id_Pengguna", id_pengguna);

                return params;
            }
        };

        App_Controller.getInstance().addToRequestQueue(request, tag_json_obj);
    }

    private void simpan (final String id_pengguna, final String nama_lengkap, final String nama_pengguna,
                         final String alamat, final String email, final String kata_sandi){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Simpan");
        showDialog();



        StringRequest strReq = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Respon Data: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    if(success == 1){

                        String nama_lengkap = jObj.getString(TAG_NAMA_LENGKAP);
                        String nama_pengguna = jObj.getString(TAG_NAMA_PENGGUNA);
                        String alamat = jObj.getString(TAG_ALAMAT);
                        String email = jObj.getString(TAG_EMAIL);
                        String kata_sandi = jObj.getString(TAG_KATA_SANDI);

                        txtNamaLengkap.setText(nama_lengkap);
                        txtNamaPengguna.setText(nama_pengguna);
                        txtAlmat.setText(alamat);
                        txtEmail.setText(email);
                        txtKataSandi.setText(kata_sandi);


                        updateUI("BATAL");

                        Log.e("Sukses Ambil Data", jObj.toString());


                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_pengguna", id_pengguna);
                params.put("nama_lengkap", nama_lengkap);
                params.put("alamat", alamat);
                params.put("email", email);
                params.put("kata_sandi", kata_sandi);
                params.put("nama_pengguna", nama_pengguna);


                return params;
            }

        };

        // Adding request to request queue
        App_Controller.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void updateUI(String uiState) {
        switch (uiState) {
            case "EDIT":
                // Initialized state, show only the phone number field and start button
                satu.setVisibility(View.GONE);
                dua.setVisibility(View.VISIBLE);
                namaLengkap2.setText(txtNamaLengkap.getText().toString());
                namaPengguna2.setText(txtNamaPengguna.getText().toString());
                alamat2.setText(txtAlmat.getText().toString());
                email2.setText(txtEmail.getText().toString());
                kataSandi2.setText(txtKataSandi.getText().toString());

                break;
            case "BATAL":
                // Code sent state, show the verification field, the
                satu.setVisibility(View.VISIBLE);
                dua.setVisibility(View.GONE);

                break;
        }
    }
}
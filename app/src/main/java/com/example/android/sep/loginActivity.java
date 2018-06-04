package com.example.android.sep;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

public class loginActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    TextView textView33;
    Button button6;
    EditText editText3, editText4;
    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG = loginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NAMA_PENGGUNA = "nama_pengguna";
    public final static String TAG_KATA_SANDI = "kata_sandi";
    public final static String TAG_ID_PENGGUNA = "id_pengguna";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_NO_TLP = "no_tlp";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_NAMA_LENGKAP = "nama_lengkap";


    public final static String TAG_ID = "id_pengguna";
String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;

    String kata_sandi, nama_pengguna, nama_lengkap, id_pengguna, email, alamat, no_tlp;



    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
        button6=(Button)findViewById(R.id.button6);
        textView33=(TextView) findViewById(R.id.textView33);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        kata_sandi = sharedpreferences.getString(TAG_KATA_SANDI, null);
        nama_pengguna = sharedpreferences.getString(TAG_NAMA_PENGGUNA, null);
        id_pengguna = sharedpreferences.getString(TAG_ID_PENGGUNA, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        no_tlp = sharedpreferences.getString(TAG_NO_TLP, null);
        nama_lengkap = sharedpreferences.getString(TAG_NAMA_LENGKAP, null);

        id_pengguna = sharedpreferences.getString(TAG_ID,null);


        if (session) {
            Intent intent = new Intent(loginActivity.this, homeActivity.class);
            intent.putExtra(TAG_KATA_SANDI, kata_sandi);
            intent.putExtra(TAG_NAMA_PENGGUNA, nama_pengguna);
            intent.putExtra(TAG_ID, id_pengguna);
            startActivity(intent);
            finish();
        }
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String nama_pengguna = editText3.getText().toString();
                String kata_sandi = editText4.getText().toString();

                // mengecek kolom yang kosong
                if (nama_pengguna.trim().length() > 0 && kata_sandi.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(nama_pengguna, kata_sandi);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
        textView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent sep = new Intent(loginActivity.this, daftar2Activity.class);
                startActivity(sep);
                finish();
            }
        });

    }
    private void checkLogin(final String nama_pengguna, final String kata_sandi) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Masuk ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String nama_pengguna = jObj.getString(TAG_NAMA_PENGGUNA);
                        String kata_sandi = jObj.getString(TAG_KATA_SANDI);

                        String id_pennguna = jObj.getString(TAG_ID_PENGGUNA);
                        String alamat = jObj.getString(TAG_ALAMAT);
                        String no_tlp = jObj.getString(TAG_NO_TLP);
                        String email = jObj.getString(TAG_EMAIL);
                        String nama_lengkap = jObj.getString(TAG_NAMA_LENGKAP);


                         String id_pengguna = jObj.getString(TAG_ID);
                        Toast.makeText(getApplicationContext(), id_pengguna, Toast.LENGTH_SHORT).show();
                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id_pengguna);
                        editor.putString(TAG_KATA_SANDI, kata_sandi);
                        editor.putString(TAG_NAMA_PENGGUNA, nama_pengguna);
                        editor.putString(TAG_ID_PENGGUNA, id_pennguna);
                        editor.putString(TAG_ALAMAT, alamat);
                        editor.putString(TAG_EMAIL, email);
                        editor.putString(TAG_NO_TLP, no_tlp);
                        editor.putString(TAG_NAMA_LENGKAP, nama_lengkap);



                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(loginActivity.this, homeActivity.class);
                        intent.putExtra(TAG_KATA_SANDI, kata_sandi);
                        intent.putExtra(TAG_NAMA_PENGGUNA, nama_pengguna);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("NAMA_PENGGUNA", nama_pengguna );
                params.put("KATA_SANDI", kata_sandi);

                return params;
            }

        };

        // Adding request to request queue
        App_Controller.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
    @Override
    public void onBackPressed()
    {Intent intent = new Intent(getApplicationContext(), homesebelumActivity.class);
        startActivity(intent);
        finish();
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}

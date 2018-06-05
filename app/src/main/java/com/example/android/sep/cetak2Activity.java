package com.example.android.sep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import static com.example.android.sep.loginActivity.TAG_ID;
import static com.example.android.sep.loginActivity.my_shared_preferences;


public class cetak2Activity extends AppCompatActivity {
    private Button button5;
    public int warnaid;
    public int orientasiid;
    public int kertasid;
    public int layananid;
    ConnectivityManager conMgr;
    String id_cetak, radio, id_pengguna1, isiSpinner3, isiSpinner, isiSpinner1, isiSpinner2;
    TextView textView19, textView39, textView40, textView41, textView42, textView43, textView44, textView47;
    EditText EditText02, EditText03, EditText0;
    RadioButton radioButton3, radioButton4;
    RadioGroup radioGroup;

    SharedPreferences sharedpreferences;
    private String url = Server.URL + "cetak.php";

    private static final String TAG = daftarActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    String tag_json_obj = "json_obj_req";
    private String selectedFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak2);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id_pengguna1 = sharedpreferences.getString(TAG_ID, null);
        EditText02 = (EditText) findViewById(R.id.EditText02);
        textView19 = (TextView) findViewById(R.id.textView19);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        EditText0 = (EditText) findViewById(R.id.EditText0);
        EditText03 = (EditText) findViewById(R.id.EditText03);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

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

            textView19 = (TextView) findViewById(R.id.textView19);
            textView39 = (TextView) findViewById(R.id.textView39);
            textView40 = (TextView) findViewById(R.id.textView40);
            textView41 = (TextView) findViewById(R.id.textView41);
            textView42 = (TextView) findViewById(R.id.textView42);
            textView43 = (TextView) findViewById(R.id.textView43);
            textView44 = (TextView) findViewById(R.id.textView44);
            textView47 = (TextView) findViewById(R.id.textView47);

            Bundle bundle = getIntent().getExtras();
            textView19.setText(bundle.getString("parse_berkas"));
            textView39.setText(bundle.getString("parse_warna"));
            textView40.setText(bundle.getString("parse_kertas"));
            textView41.setText(bundle.getString("parse_orientasi"));
            textView42.setText(bundle.getString("parse_radio"));
            textView43.setText(bundle.getString("parse_layanan"));
            textView44.setText(bundle.getString("parse_salinan"));
            textView47.setText(bundle.getString("parse_komentar"));


            //mengambil id warna
            if (textView39.getText().equals("Berwarna")) {
                warnaid = 1;
            } else if (textView39.getText().equals("Hitam putih")) {
                warnaid = 2;
            }
            Toast.makeText(getApplicationContext(), "Selected " + warnaid, Toast.LENGTH_SHORT).show();
            isiSpinner = String.valueOf(warnaid).toString();


        //mengambil id orientasi
        if (textView41.getText().equals("Portrait")) {
            orientasiid = 1;
        } else if (textView41.getText().equals("Landscape")) {
            orientasiid = 2;
        }
        Toast.makeText(getApplicationContext(), "Selected " + orientasiid, Toast.LENGTH_SHORT).show();
        isiSpinner1 = String.valueOf(orientasiid).toString();


        //mengambil id kertas
        if (textView40.getText().equals("A4")) {
            kertasid = 1;
        } else if (textView40.getText().equals("F4")) {
            kertasid = 2;
        }
        Toast.makeText(getApplicationContext(), "Selected " + kertasid, Toast.LENGTH_SHORT).show();
        isiSpinner2 = String.valueOf(kertasid).toString();

        //mengambil id layanan
        if (textView43.getText().equals("Staples saja")) {
            layananid = 1;
        } else if (textView43.getText().equals("Jilid Mika")) {
            layananid = 2;
        } else if (textView43.getText().equals("Jilid Jepang")) {
            layananid = 3;
        } else if (textView43.getText().equals("Jilid Spiral")) {
            layananid = 4;
        }
        Toast.makeText(getApplicationContext(), "Selected " + layananid, Toast.LENGTH_SHORT).show();
        isiSpinner3 = String.valueOf(layananid).toString();

        //parsing data dari cetak activity


        button5 = (Button) findViewById(R.id.button5);
        // function tombol
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Spinner1 = String.valueOf(isiSpinner).toString();
                Toast.makeText(getApplicationContext(), "i " + Spinner1, Toast.LENGTH_SHORT).show();
                final String Spinner2 = String.valueOf(isiSpinner1).toString();
                Toast.makeText(getApplicationContext(), "a " + Spinner2, Toast.LENGTH_SHORT).show();
                final String Spinner3 = String.valueOf(isiSpinner2).toString();
                Toast.makeText(getApplicationContext(), "c" + Spinner3, Toast.LENGTH_SHORT).show();
                final String Spinner4 = String.valueOf(isiSpinner3).toString();
                Toast.makeText(getApplicationContext(), "a " + Spinner4, Toast.LENGTH_SHORT).show();
                final String komentar = textView47.getText().toString();
                Toast.makeText(getApplicationContext(), "f " + komentar, Toast.LENGTH_SHORT).show();
                final String radio = textView42.getText().toString();
                Toast.makeText(getApplicationContext(), "a " + radio, Toast.LENGTH_SHORT).show();
                final String salinan = textView44.getText().toString();
                Toast.makeText(getApplicationContext(), "d" + salinan, Toast.LENGTH_SHORT).show();
                final String id_pengguna = String.valueOf(id_pengguna1).toString();
                Toast.makeText(getApplicationContext(), "s " + id_pengguna, Toast.LENGTH_SHORT).show();
                final String berkas = textView19.getText().toString();
                Toast.makeText(getApplicationContext(), "berkas " + berkas, Toast.LENGTH_SHORT).show();



                //String nama_berkas= textView23.getText().toString();
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    check(Spinner1, Spinner2, Spinner3, Spinner4, komentar, berkas, radio, salinan, id_pengguna);
                } else {

                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
                Bundle bundle = new Bundle();
                bundle.putString("parse_berkas",textView19.getText().toString());

                Intent intent = new Intent(cetak2Activity.this, berkasActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
            private void check(String Spinner1, String Spinner2, String Spinner3, String Spinner4, final String komentar, final String berkas, final String radio, final String salinan, final String id_pengguna) {

                StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jObj = new JSONObject(response);


                            // Check for error node in json

                        } catch (JSONException e) {
                            // JSON error
                            Toast.makeText(getApplicationContext(),
                                    "gagal lagi", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Login Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                "gagal", Toast.LENGTH_LONG).show();


                    }
                })
                {
                   @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("warna", isiSpinner);
                        params.put("orientasi",isiSpinner1);
                        params.put("kertas", isiSpinner2);
                        params.put("layanan", isiSpinner3);
                        params.put("berkas", berkas);
                        params.put("komentar", komentar);
                        params.put("halaman", radio);
                        params.put("salinan", salinan);
                        params.put("id_pengguna", id_pengguna);
                        return params;
                    }

                };

                // Adding request to request queue
                App_Controller.getInstance().addToRequestQueue(strReq, tag_json_obj);
            }

            private String pilihradio() {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == radioButton3.getId()) {
                    radio = radioButton3.getText().toString();

                } else if (selectedId == radioButton4.getId()) {
                    radio = "Cetak Halaman " + EditText03.getText().toString();

                }
                return radio;

            }
}
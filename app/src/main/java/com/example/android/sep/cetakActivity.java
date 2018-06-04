package com.example.android.sep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.SharedMemory;
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

public class cetakActivity extends AppCompatActivity {
private Button btnCetak;
private Button button2;
TextView textView, textView23;
EditText EditText02, EditText03, EditText0;
private Spinner aaa, aaa1, aaa2, aaa3;
ProgressDialog pDialog;
RadioButton radioButton3, radioButton4;
RadioGroup radioGroup;

    int success;
    ConnectivityManager conMgr;
    public int warna;
    public int orientasi;
    public int kertas;
    public int layanan;
    String radio, id_pengguna1, isiSpinner3, isiSpinner, isiSpinner1, isiSpinner2;
    SharedPreferences sharedpreferences;

    private String url = Server.URL + "cetak.php";

    private static final String TAG = daftarActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        id_pengguna1 = sharedpreferences.getString(TAG_ID, null);

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
       // requestStoragePermission();

        aaa = (Spinner) findViewById(R.id.spinner1);
        aaa1 = (Spinner) findViewById(R.id.spinner2);
        aaa2 = (Spinner) findViewById(R.id.spinner3);
        aaa3 = (Spinner) findViewById(R.id.spinner4);
        EditText02 = (EditText) findViewById(R.id.EditText02);
        textView23 =(TextView) findViewById(R.id.textView23);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        EditText0=(EditText) findViewById(R.id.EditText0);
        EditText03=(EditText)findViewById(R.id.EditText03);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);

       final String[] list1=getResources().getStringArray(R.array.list1);
       final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list1);
       aaa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               if (adapter.getItem(position).equals("Berwarna")){
                    warna = 1;
               }
               else if (adapter.getItem(position).equals("Hitam putih")){
                   warna = 2;
               }
               Toast.makeText(getApplicationContext(), "Selected "+ warna,Toast.LENGTH_SHORT).show();
               isiSpinner =String.valueOf(warna).toString();
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });

        final String[] list2=getResources().getStringArray(R.array.list2);
        final ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list2);
        aaa1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter1.getItem(position).equals("Portrait")){
                    orientasi = 1;
                }
                else if (adapter1.getItem(position).equals("Landscape")){
                    orientasi = 2;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ orientasi,Toast.LENGTH_SHORT).show();
                isiSpinner1 =String.valueOf(orientasi).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] list3=getResources().getStringArray(R.array.list3);
        final ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list3);
        aaa2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter2.getItem(position).equals("A4")){
                    kertas = 1;
                }
                else if (adapter2.getItem(position).equals("F4")){
                    kertas = 2;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ kertas,Toast.LENGTH_SHORT).show();
                isiSpinner2 =String.valueOf(kertas).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] list4=getResources().getStringArray(R.array.list4);
        final ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,R.layout.item_spin,R.id.txItemSpin,list4);
        aaa3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapter3.getItem(position).equals("Staples saja")){
                    layanan = 1;
                }
                else if (adapter3.getItem(position).equals("Jilid Mika")){
                    layanan = 2;
                }
                else if (adapter3.getItem(position).equals("Jilid Jepang")){
                    layanan = 3;
                }
                else if (adapter3.getItem(position).equals("Jilid Spiral")){
                    layanan = 4;
                }
                Toast.makeText(getApplicationContext(), "Selected "+ layanan,Toast.LENGTH_SHORT).show();
                isiSpinner3 = String.valueOf(layanan).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCetak=(Button)findViewById(R.id.btnCetak);
        button2 = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView23);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final String Spinner1  = isiSpinner;
                final String Spinner2 = isiSpinner1;
                final String Spinner3 = isiSpinner2;
                final String Spinner4 = isiSpinner3;
                Toast.makeText(getApplicationContext(),"spiner3"+Spinner4,Toast.LENGTH_LONG).show();
                final String komentar = EditText02.getText().toString();
                final String berkas = textView23.getText().toString();
                final String radio = pilihradio();
                final String salinan = EditText0.getText().toString();
                final String id_pengguna = String.valueOf(id_pengguna1).toString();

                //String nama_berkas= textView23.getText().toString();
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    check(Spinner1, Spinner2, Spinner3, Spinner4, komentar, berkas, radio, salinan,id_pengguna);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
         }

      });

  }

    private void check(final String Spinner1, final String Spinner2, final String Spinner3, final String Spinner4, final String komentar, final String berkas, final String radio, final String salinan, final String id_pengguna) {

        StringRequest strReq = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {

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


            }}) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("warna",Spinner1);
                params.put("orientasi",Spinner2);
                params.put("kertas", Spinner3);
                params.put("layanan", Spinner4);
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
    public String pilihradio(){

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == radioButton3.getId()){
            radio = radioButton3.getText().toString();
        }
        else if (selectedId == radioButton4.getId()){
            radio = "Cetak Halaman "+EditText03.getText().toString();

        }
        return radio;
    }
    /*
    public void uploadMultipart() {
        //getting name for the image
        String nama_berkas = textView23.getText().toString().trim();

        //getting the actual path of the image
        String path = FilePath.getPath(this, filePath);

        if (path == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                        .addFileToUpload(path, "pdf") //Adding file
                        .addParameter("nama_berkas", nama_berkas) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onClick(View v) {
        if (v == button2) {
            showFileChooser();
        }
        if (v == btnCetak) {
            uploadMultipart();
        }
    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
    }
*/
}

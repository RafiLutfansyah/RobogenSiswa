package com.rafilutfansyah.robogensiswa.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.rafilutfansyah.robogensiswa.R;
import com.rafilutfansyah.robogensiswa.model.Siswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private SignInButton signInButton;
    private Button buttonSignIn;

    private Siswa siswa;

    private Uri photoUrl;
    private String name, email, uid, providerId;
    private String username, password, namaLengkap, namaPanggilan, kelasRobotik, tanggalLahir, urlFoto, alamat, namaSekolah, kelasSekolah, namaAyah, namaIbu, noTelp, emailDatabase, hariBelajar, jamBelajar, tanggalMendaftar;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText) findViewById(R.id.text_email);
        etPassword = (EditText) findViewById(R.id.text_password);

        pref = getApplicationContext().getSharedPreferences("session", 0);
        editor = pref.edit();

        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Google Play Services error", Toast.LENGTH_SHORT).show();
                        signInButton.setEnabled(true);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        siswa = new Siswa();

        buttonSignIn = (Button) findViewById(R.id.button_sign_in);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSignIn.setEnabled(false);
                getDataUsername();
            }
        });

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInButton.setEnabled(false);
                signIn();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    for (UserInfo profile : user.getProviderData()) {
                        // Id of the provider (ex: google.com)
                        providerId = profile.getProviderId();
                        // UID specific to the provider
                        uid = profile.getUid();
                        // Name, email address, and profile photo Url
                        name = profile.getDisplayName();
                        email = user.getEmail();
                        photoUrl = profile.getPhotoUrl();
                    }

                    getDataEmail();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        // ...
    }

    private void getDataEmail() {
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url ="https://robogen.000webhostapp.com/API/Siswa/";

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                siswa.setUsername(obj.getString("username"));
                                siswa.setPassword(obj.getString("password"));
                                siswa.setNamaLengkap(obj.getString("nama_lengkap"));
                                siswa.setNamaPanggilan(obj.getString("nama_panggilan"));
                                siswa.setKelasRobotik(obj.getString("kelas_robotik"));
                                siswa.setTanggalLahir(obj.getString("tanggal_lahir"));
                                siswa.setUrlFoto(obj.getString("url_foto"));
                                siswa.setAlamat(obj.getString("alamat"));
                                siswa.setNamaSekolah(obj.getString("nama_sekolah"));
                                siswa.setKelasSekolah(obj.getString("kelas_sekolah"));
                                siswa.setNamaAyah(obj.getString("nama_ayah"));
                                siswa.setNamaIbu(obj.getString("nama_ibu"));
                                siswa.setNoTelp(obj.getString("no_telp"));
                                siswa.setEmail(obj.getString("email"));
                                siswa.setHariBelajar(obj.getString("hari_belajar"));
                                siswa.setJamBelajar(obj.getString("jam_belajar"));
                                siswa.setTanggalMendaftar(obj.getString("tanggal_mendaftar"));

                                username = siswa.getUsername();
                                password = siswa.getPassword();
                                namaLengkap = siswa.getNamaLengkap();
                                namaPanggilan = siswa.getNamaPanggilan();
                                kelasRobotik = siswa.getKelasRobotik();
                                tanggalLahir = siswa.getTanggalLahir();
                                urlFoto = siswa.getUrlFoto();
                                alamat = siswa.getAlamat();
                                namaSekolah = siswa.getNamaSekolah();
                                kelasSekolah = siswa.getKelasSekolah();
                                namaAyah = siswa.getNamaAyah();
                                namaIbu = siswa.getNamaIbu();
                                noTelp = siswa.getNoTelp();
                                emailDatabase = siswa.getEmail();
                                hariBelajar = siswa.getHariBelajar();
                                jamBelajar = siswa.getJamBelajar();
                                tanggalMendaftar = siswa.getTanggalMendaftar();

                                if(email.equals(emailDatabase)) {
                                    editor.putString("username", username);
                                    editor.putString("password", password);
                                    editor.putString("namaLengkap", namaLengkap);
                                    editor.putString("namaPanggilan", namaPanggilan);
                                    editor.putString("kelasRobotik", kelasRobotik);
                                    editor.putString("tanggalLahir", tanggalLahir);
                                    editor.putString("urlFoto", urlFoto);
                                    editor.putString("alamat", alamat);
                                    editor.putString("namaSekolah", namaSekolah);
                                    editor.putString("kelasSekolah", kelasSekolah);
                                    editor.putString("namaAyah", namaAyah);
                                    editor.putString("namaIbu", namaIbu);
                                    editor.putString("noTelp", noTelp);
                                    editor.putString("email", emailDatabase);
                                    editor.putString("hariBelajar", hariBelajar);
                                    editor.putString("jamBelajar", jamBelajar);
                                    editor.putString("tanggalMendaftar", tanggalMendaftar);
                                    editor.commit();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if(!email.equals(emailDatabase)) {
                            signInButton.setEnabled(true);
                            editor.clear();
                            editor.commit();
                            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                    new ResultCallback<Status>() {
                                        @Override
                                        public void onResult(@NonNull Status status) {
                                        }
                                    });
                            FirebaseAuth.getInstance().signOut();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataEmail();
            }
        });
        queue.add(request);
    }

    private void getDataUsername() {
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url ="https://robogen.000webhostapp.com/API/siswa?username="+etUsername.getText();

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject obj = response.getJSONObject(0);

                            siswa.setUsername(obj.getString("username"));
                            siswa.setPassword(obj.getString("password"));
                            siswa.setNamaLengkap(obj.getString("nama_lengkap"));
                            siswa.setNamaPanggilan(obj.getString("nama_panggilan"));
                            siswa.setKelasRobotik(obj.getString("kelas_robotik"));
                            siswa.setTanggalLahir(obj.getString("tanggal_lahir"));
                            siswa.setUrlFoto(obj.getString("url_foto"));
                            siswa.setAlamat(obj.getString("alamat"));
                            siswa.setNamaSekolah(obj.getString("nama_sekolah"));
                            siswa.setKelasSekolah(obj.getString("kelas_sekolah"));
                            siswa.setNamaAyah(obj.getString("nama_ayah"));
                            siswa.setNamaIbu(obj.getString("nama_ibu"));
                            siswa.setNoTelp(obj.getString("no_telp"));
                            siswa.setEmail(obj.getString("email"));
                            siswa.setHariBelajar(obj.getString("hari_belajar"));
                            siswa.setJamBelajar(obj.getString("jam_belajar"));
                            siswa.setTanggalMendaftar(obj.getString("tanggal_mendaftar"));

                            username = siswa.getUsername();
                            password = siswa.getPassword();
                            namaLengkap = siswa.getNamaLengkap();
                            namaPanggilan = siswa.getNamaPanggilan();
                            kelasRobotik = siswa.getKelasRobotik();
                            tanggalLahir = siswa.getTanggalLahir();
                            urlFoto = siswa.getUrlFoto();
                            alamat = siswa.getAlamat();
                            namaSekolah = siswa.getNamaSekolah();
                            kelasSekolah = siswa.getKelasSekolah();
                            namaAyah = siswa.getNamaAyah();
                            namaIbu = siswa.getNamaIbu();
                            noTelp = siswa.getNoTelp();
                            emailDatabase = siswa.getEmail();
                            hariBelajar = siswa.getHariBelajar();
                            jamBelajar = siswa.getJamBelajar();
                            tanggalMendaftar = siswa.getTanggalMendaftar();

                            if(etUsername.getText().toString().equals(username) && etPassword.getText().toString().equals(password)) {
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.putString("namaLengkap", namaLengkap);
                                editor.putString("namaPanggilan", namaPanggilan);
                                editor.putString("kelasRobotik", kelasRobotik);
                                editor.putString("tanggalLahir", tanggalLahir);
                                editor.putString("urlFoto", urlFoto);
                                editor.putString("alamat", alamat);
                                editor.putString("namaSekolah", namaSekolah);
                                editor.putString("kelasSekolah", kelasSekolah);
                                editor.putString("namaAyah", namaAyah);
                                editor.putString("namaIbu", namaIbu);
                                editor.putString("noTelp", noTelp);
                                editor.putString("email", emailDatabase);
                                editor.putString("hariBelajar", hariBelajar);
                                editor.putString("jamBelajar", jamBelajar);
                                editor.putString("tanggalMendaftar", tanggalMendaftar);
                                editor.commit();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Username / Password Salah!", Toast.LENGTH_LONG).show();
                                editor.clear();
                                editor.commit();
                                buttonSignIn.setEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getDataUsername();
            }
        });
        queue.add(request);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(LoginActivity.this, "Google Sign In failed", Toast.LENGTH_SHORT).show();
                signInButton.setEnabled(true);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            signInButton.setEnabled(true);
                        }
                        // ...
                    }
                });
    }
}
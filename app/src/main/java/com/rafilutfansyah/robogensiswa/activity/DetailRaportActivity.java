package com.rafilutfansyah.robogensiswa.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rafilutfansyah.robogensiswa.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class DetailRaportActivity extends AppCompatActivity {

    private TextView textUsername, textHari, textTanggal, textJamMasuk, textMateri, textNilaiMerakit, textNilaiMandiri, textNilaiKreativitas, textNilaiTotal, textGrade, textKeterangan;
    private ImageView imageView;
    private String idRaport, username, materi, foto, grade, keterangan, hari, tanggal, jamMasuk;
    private int nilaiMerakit, nilaiMandiri, nilaiKreativitas;
    private Double nilaiTotal;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_raport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);

        textUsername = (TextView) findViewById(R.id.text_username);
        textHari = (TextView) findViewById(R.id.text_hari);
        textTanggal = (TextView) findViewById(R.id.text_tanggal);
        textJamMasuk = (TextView) findViewById(R.id.text_jam_masuk);
        textMateri = (TextView) findViewById(R.id.text_materi);
        imageView = (ImageView)findViewById(R.id.image_view);
        textNilaiMerakit = (TextView) findViewById(R.id.text_nilai_merakit);
        textNilaiMandiri = (TextView) findViewById(R.id.text_nilai_mandiri);
        textNilaiKreativitas = (TextView) findViewById(R.id.text_nilai_kreativitas);
        textNilaiTotal = (TextView) findViewById(R.id.text_nilai_total);
        textGrade = (TextView) findViewById(R.id.text_grade);
        textKeterangan = (TextView) findViewById(R.id.text_keterangan);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idRaport = (String) bundle.get("id_raport");
        username = (String) bundle.get("username");
        hari = (String) bundle.get("hari");
        tanggal = (String) bundle.get("tanggal");
        jamMasuk = (String) bundle.get("jam_masuk");
        materi = (String) bundle.get("materi");
        foto = (String) bundle.get("foto");
        nilaiMerakit = (int) bundle.getInt("nilai_merakit");
        nilaiMandiri = (int) bundle.getInt("nilai_mandiri");
        nilaiKreativitas = (int) bundle.getInt("nilai_kreativitas");
        nilaiTotal = (Double) bundle.getDouble("nilai_total");
        grade = (String) bundle.get("grade");
        keterangan = (String) bundle.get("keterangan");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(materi);
        actionBar.setDisplayHomeAsUpEnabled(true);

        textUsername.setText(": "+username);
        textHari.setText(": "+hari);
        textTanggal.setText(": "+tanggal);
        textJamMasuk.setText(": "+jamMasuk);
        textMateri.setText(": "+materi);
        Picasso.with(this) //picasso memanggil Contex class //awalan coding picasso
                .load("https://robogen.000webhostapp.com/codeigniter/uploads/"+foto)
                .resize(720, 720)
                .centerCrop()
                .into(imageView);
        textNilaiMerakit.setText(": "+String.valueOf(nilaiMerakit));
        textNilaiMandiri.setText(": "+String.valueOf(nilaiMandiri));
        textNilaiKreativitas.setText(": "+String.valueOf(nilaiKreativitas));
        textNilaiTotal.setText(": "+String.valueOf(nilaiTotal));
        textGrade.setText(": "+grade);
        textKeterangan.setText(keterangan);
        
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://robogen.000webhostapp.com/codeigniter/uploads/"+foto)));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {

            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

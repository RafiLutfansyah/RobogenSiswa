package com.rafilutfansyah.robogensiswa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafilutfansyah.robogensiswa.R;
import com.squareup.picasso.Picasso;

public class DetailRaportActivity extends AppCompatActivity {

    private TextView textUsername, textHari, textTanggal, textJamMasuk, textMateri, textFoto, textNilaiMerakit, textNilaiMandiri, textNilaiKreativitas, textNilaiTotal, textGrade, textKeterangan;
    private ImageView imageView;
    private String idRaport, username, materi, foto, grade, keterangan, hari, tanggal, jamMasuk;
    private int nilaiMerakit, nilaiMandiri, nilaiKreativitas;
    private Double nilaiTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_raport);

        textUsername = (TextView) findViewById(R.id.text_username);
        textHari = (TextView) findViewById(R.id.text_hari);
        textTanggal = (TextView) findViewById(R.id.text_tanggal);
        textJamMasuk = (TextView) findViewById(R.id.text_jam_masuk);
        textMateri = (TextView) findViewById(R.id.text_materi);
        textFoto = (TextView) findViewById(R.id.text_foto);
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

        textUsername.setText(username);
        textHari.setText(hari);
        textTanggal.setText(tanggal);
        textJamMasuk.setText(jamMasuk);
        textMateri.setText(materi);
        textFoto.setText(foto);
        Picasso.with(this) //picasso memanggil Contex class //awalan coding picasso
                .load("https://robogen.000webhostapp.com/codeigniter/uploads/"+foto) //meload gambar dari Internet
                .into(imageView);
        textNilaiMerakit.setText(String.valueOf(nilaiMerakit));
        textNilaiMandiri.setText(String.valueOf(nilaiMandiri));
        textNilaiKreativitas.setText(String.valueOf(nilaiKreativitas));
        textNilaiTotal.setText(String.valueOf(nilaiTotal));
        textGrade.setText(grade);
        textKeterangan.setText(keterangan);
    }
}

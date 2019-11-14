package com.kelompok3.misapps.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jgabrielfreitas.core.BlurImageView;
import com.kelompok3.misapps.Model.KaryawanModel;
import com.kelompok3.misapps.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKaryawan extends AppCompatActivity {


    @BindView(R.id.imageViewBlurred)
    BlurImageView imageViewBlurred;
    @BindView(R.id.image_karyawan)
    CircleImageView image_karyawan;
    @BindView(R.id.namaKaryawan)
    TextView namaKaryawan;
    @BindView(R.id.nikKaryawan)
    TextView nikKaryawan;

    @BindView(R.id.txAddress)
    TextView txAddress;
    @BindView(R.id.txGender)
    TextView txGender;
    @BindView(R.id.txBlood)
    TextView txBlood;
    @BindView(R.id.txCitizenship)
    TextView txCitizenship;
    @BindView(R.id.txDateOfBirth)
    TextView txDateOfBirth;
    @BindView(R.id.txReligion)
    TextView txReligion;
    @BindView(R.id.txBerlakuHingga)
    TextView txBerlakuHingga;
    @BindView(R.id.txTempatTanggalBuat)
    TextView txTempatTanggalBuat;
    @BindView(R.id.txMarriageStatus)
    TextView txMarriageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);
        ButterKnife.bind(this);

        KaryawanModel model = getIntent().getParcelableExtra("DATA_KARYAWAN");

        Picasso.get()
                .load(model.getBase_url())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageViewBlurred.setImageBitmap(bitmap);
                        imageViewBlurred.setBlur(24);
                        image_karyawan.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        namaKaryawan.setText((!model.getEmployee_name().equals("") ? model.getEmployee_name() : "-"));
        nikKaryawan.setText((!model.getNomor_induk_pegawai().equals("") ? model.getNomor_induk_pegawai() : "-"));
        txAddress.setText((!model.getAddress().equals("") ? model.getAddress() : "-"));
        txGender.setText((!model.getGol_darah().equals("") ? model.getGol_darah() : "-"));
        txDateOfBirth.setText((!model.getTempat_lahir().equals("") ? model.getTempat_lahir() : "-") + ", " + (!model.getTanggal_lahir().equals("") ? model.getTanggal_lahir() : "-"));
        txBlood.setText((!model.getGol_darah().equals("") ? model.getGol_darah() : "-"));
        txReligion.setText((!model.getAgama().equals("") ? model.getAgama() : "-"));
        txMarriageStatus.setText((!model.getStatus_perkawinan().equals("") ? model.getStatus_perkawinan() : "-"));
        txCitizenship.setText((!model.getKewarganegaraan().equals("") ? model.getKewarganegaraan() : "-"));
        txBerlakuHingga.setText((!model.getBerlaku_hingga().equals("") ? model.getBerlaku_hingga() : "-"));
        txTempatTanggalBuat.setText((!model.getTempat_buat().equals("") ? model.getTempat_buat() : "-") + ", " + (!model.getTanggal_buat().equals("") ? model.getTanggal_buat() : "-"));
    }
}

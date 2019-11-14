package com.kelompok3.misapps.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kelompok3.misapps.Fragments.AboutFragment;
import com.kelompok3.misapps.Fragments.HomeFragment;
import com.kelompok3.misapps.Fragments.KaryawanFragment;
import com.kelompok3.misapps.Fragments.OfficeFragment;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.SharedPreferences.TinyDB;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kelompok3.misapps.Util.Consts.KEY_CELL_PHONE;
import static com.kelompok3.misapps.Util.Consts.KEY_EMAIL;
import static com.kelompok3.misapps.Util.Consts.KEY_NAMA;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    Drawer result;
    AccountHeader headerResult;

    final Fragment aboutFragment = new AboutFragment();
    final Fragment homeFragment = new HomeFragment();
    final Fragment karyawanFragment = new KaryawanFragment();
    final Fragment officeFragment = new OfficeFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();

    Fragment active = homeFragment;

    String full_name, email, cell_phone;

    TinyDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        db = new TinyDB(this);

        full_name = db.getString(KEY_NAMA);
        email = db.getString(KEY_EMAIL);
        cell_phone = db.getString(KEY_CELL_PHONE);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        fragmentManager.beginTransaction().add(R.id.frameLayout, aboutFragment, aboutFragment.getClass().getSimpleName()).hide(aboutFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, karyawanFragment, karyawanFragment.getClass().getSimpleName()).hide(karyawanFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, officeFragment, officeFragment.getClass().getSimpleName()).hide(officeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment, homeFragment.getClass().getSimpleName()).commit();

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(new ProfileDrawerItem().withName(full_name).withEmail(email).withIcon(getResources().getDrawable(R.drawable.woman)))
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Beranda").withIcon(GoogleMaterial.Icon.gmd_home).withSelectable(true),
                        new PrimaryDrawerItem().withName("Tentang Aplikasi").withIcon(GoogleMaterial.Icon.gmd_info).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Karyawan").withIcon(GoogleMaterial.Icon.gmd_person).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Office").withIcon(GoogleMaterial.Icon.gmd_work).withSelectable(true),
                        new PrimaryDrawerItem().withName("Logout").withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withSelectable(true)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                // home fragment
                                fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                                active = homeFragment;
                                break;
                            case 2:
                                //about
                                fragmentManager.beginTransaction().hide(active).show(aboutFragment).commit();
                                active = aboutFragment;
                                break;
                            case 3:
                                //daftar karyawan
                                fragmentManager.beginTransaction().hide(active).show(karyawanFragment).commit();
                                active = karyawanFragment;
                                break;
                            case 4:
                                //daftar office
                                fragmentManager.beginTransaction().hide(active).show(officeFragment).commit();
                                active = officeFragment;
                                break;
                            case 5:
                                //keluar
                                db.clear();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                }).build();
    }
}

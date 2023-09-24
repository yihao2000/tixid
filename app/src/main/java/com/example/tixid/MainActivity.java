package com.example.tixid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tixid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //Appnya kalo abis login dia harus di close dulu sama dibuang dr recent app di hpnya, baru pas dibuka lg baru ke login
    TextView username;
    ActivityMainBinding binding;
    DBHelper myDB;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.abLogout:

                PreferenceData.clearLoggedInUser(getApplicationContext());
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DBHelper(this);
        myDB.insertTheatres();
        myDB.insertMovies();
        myDB.insertComingSoonMovies();

        startApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startApp();
    }


    public void startApp(){
        if(!PreferenceData.getUserLoggedInStatus(getApplicationContext())){
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

            startActivity(intent);
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        replaceFragment(new HomeFragment());


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){


                case R.id.bnmHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.bnmTheatres:
                    replaceFragment(new TheatreFragment());
                    break;

                case R.id.bnmProfile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });







    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void setUsername(){

    }
}
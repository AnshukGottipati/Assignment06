package com.example.assignment06;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment06.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragment.startListener , CreateUserFragment.submitListener, ProfileFragment.editListener
,EditUserFragment.editUserListener
{


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Main Activity");


        getSupportFragmentManager().beginTransaction()
                .add(binding.main.getId(), new MainFragment())
                .commit();


    }


    @Override
    public void gotoCreateFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.main.getId(),new CreateUserFragment())
                .commit();
    }

    @Override
    public void gotoProfileFragment(User user) {

        getSupportFragmentManager().beginTransaction()
                .replace(binding.main.getId(), ProfileFragment.newInstance(user),"MY_PROFILE_TAG")
                .addToBackStack(null)
               .commit();

    }

    @Override
    public void gotoEditFragment(User user) {

        getSupportFragmentManager().beginTransaction()
                .replace(binding.main.getId(), EditUserFragment.newInstance(user))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoProfileFragmentAgain(User user) {
       ProfileFragment pf =  (ProfileFragment) getSupportFragmentManager().findFragmentByTag("MY_PROFILE_TAG");
       pf.setmUser(user);
       if(pf != null)
           getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoProfileFragmentAgain() {
        getSupportFragmentManager().popBackStack();
    }

}
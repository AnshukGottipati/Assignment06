package com.example.assignment06;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.assignment06.databinding.FragmentCreateUserBinding;

import java.io.Serializable;


public class CreateUserFragment extends Fragment implements Serializable {

    public CreateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentCreateUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateUserBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.startBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = binding.EnterNameEditTxt.getText().toString();
                String text2 = binding.EnterEmailEditTxt.getText().toString();
                int id = binding.radioGroup.getCheckedRadioButtonId();

                if(id == -1) {
                    Toast.makeText(getActivity(),"no role selected",Toast.LENGTH_SHORT).show();
                }else if(text1.isEmpty() || text2.isEmpty()) {
                    Toast.makeText(getActivity(),"no email or name",Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton rad = (RadioButton) binding.getRoot().findViewById(id);
                    User user = new User(text1,text2,rad.getText().toString());
                    mListener.gotoProfileFragment(user);
                }
            }
        });
    }


    submitListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (submitListener) context;
    }

    public interface submitListener{
        void gotoProfileFragment(User user);
    }
}
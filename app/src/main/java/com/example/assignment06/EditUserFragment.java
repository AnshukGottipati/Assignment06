package com.example.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.assignment06.databinding.FragmentEditUserBinding;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserFragment extends Fragment implements Serializable {

    public static final String UserKey = "USER_EDIT_KEY";
    private User mUser;



    public EditUserFragment() {
        // Required empty public constructor
    }


    public static EditUserFragment newInstance(User user) {

        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(UserKey, user);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(UserKey);
        }
    }

    FragmentEditUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditUserBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.EnterNameEditTxt.setText(mUser.getName());
        binding.EnterEmailEditTxt.setText(mUser.getEmail());
        RadioGroup rg = binding.radioGroup;

        for(int i = 0; i < rg.getChildCount();i++){
            RadioButton btn = (RadioButton) rg.getChildAt(i);
            if(btn.getText().toString().equals(mUser.getRole())){
                rg.check(btn.getId());
            }
        }


        binding.startBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cancel
                mListener.gotoProfileFragmentAgain();
            }
        });

        binding.startBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edit

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
                    mListener.gotoProfileFragmentAgain(user);
                }
            }
        });

    }

    editUserListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (editUserListener) context;
    }

    public interface editUserListener{
        void gotoProfileFragmentAgain();
        void gotoProfileFragmentAgain(User user);

    }

}
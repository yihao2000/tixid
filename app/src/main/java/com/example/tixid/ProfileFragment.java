package com.example.tixid;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    EditText profileUsername, profilePassword, profilePhoneNumber, profileOldPassword;
    Button updateBtn;
    DBHelper myDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myDb = new DBHelper(getActivity().getApplicationContext());
        profileUsername = getView().findViewById(R.id.etUsernameProfile);
        profilePassword = getView().findViewById(R.id.etPasswordProfile);
        profilePhoneNumber = getView().findViewById(R.id.etPhoneNumberProfile);
        profileOldPassword = getView().findViewById(R.id.etOldPasswordProfile);
        updateBtn = getView().findViewById(R.id.btnEditProfile);

        String currUsername = PreferenceData.getLoggedInUserUsername(getActivity().getApplicationContext());


        User user = myDb.findUserCredential(currUsername);

        String username = user.getUsername();

        profileUsername.setText(user.getUsername());
        profilePhoneNumber.setText(user.getPhoneNumber());




        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sProfileName = profileUsername.getText().toString();
                String sProfileOldPassword = profileOldPassword.getText().toString();
                String sProfilePhoneNumber = profilePhoneNumber.getText().toString();
                String sProfileNewPassword = profilePassword.getText().toString();

                Boolean isExist = myDb.checkusername(sProfileName);

                if(sProfileName.equals("") ||  sProfilePhoneNumber.equals("") || sProfileOldPassword.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "All fields must be filled except new password column!", Toast.LENGTH_SHORT).show();
                }else{
                    if(!sProfileName.equals(PreferenceData.getLoggedInUserUsername(getActivity().getApplicationContext())) && isExist){
                        Toast.makeText(getActivity().getApplicationContext(), "Username is already taken! Please choose another one...", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!user.getPassword().equals(sProfileOldPassword)){
                            Toast.makeText(getActivity().getApplicationContext(), "Current Password does not match!", Toast.LENGTH_SHORT).show();
                        }else{

                            if(sProfileNewPassword.equals("")){
                                myDb.updateProfileOldPassword(currUsername, sProfileName, sProfileOldPassword, sProfilePhoneNumber);
                            }else{
                                myDb.updateProfileOldPassword(currUsername, sProfileName, sProfileNewPassword, sProfilePhoneNumber);
                            }
                            PreferenceData.setLoggedInUserUsername(getActivity().getApplicationContext(),sProfileName);


                        }


                    }
                }
            }
        });





        super.onViewCreated(view, savedInstanceState);
    }
}
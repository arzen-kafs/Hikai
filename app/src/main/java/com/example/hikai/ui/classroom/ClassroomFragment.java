package com.example.hikai.ui.classroom;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hikai.R;
import com.example.hikai.hikaiplayer.Player;

public class ClassroomFragment extends Fragment {

    CardView card1;
    private ClassroomViewModel homeViewModel;
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ClassroomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_classroom, container, false);


        card1=root.findViewById(R.id.card1);
       card1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent card1=new Intent(getContext(), Player.class);
               startActivity(card1);
           }
       });

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);

        //Progressbar 1
        final ProgressBar mProgress1 = root.findViewById(R.id.circularProgressbar1);
        mProgress1.setProgress(0);   // Main Progress
        mProgress1.setSecondaryProgress(100); // Secondary Progress
        mProgress1.setMax(100); // Maximum Progress
        mProgress1.setProgressDrawable(drawable);

        tv1 = root.findViewById(R.id.tv1);
        new Thread (new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus <100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress1.setProgress(pStatus);
                            tv1.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(100); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Progressbar 2
        final ProgressBar mProgress2 = root.findViewById(R.id.circularProgressbar2);
        mProgress2.setProgress(0);   // Main Progress
        mProgress2.setSecondaryProgress(100); // Secondary Progress
        mProgress2.setMax(100); // Maximum Progress
        mProgress2.setProgressDrawable(drawable);

        tv2 = root.findViewById(R.id.tv2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus <100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress2.setProgress(pStatus);
                            tv2.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(100); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Progressbar 3
        final ProgressBar mProgress3 = root.findViewById(R.id.circularProgressbar3);
        mProgress3.setProgress(0);   // Main Progress
        mProgress3.setSecondaryProgress(100); // Secondary Progress
        mProgress3.setMax(100); // Maximum Progress
        mProgress3.setProgressDrawable(drawable);

        tv3 = root.findViewById(R.id.tv3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus <100) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress3.setProgress(pStatus);
                            tv3.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(100); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}
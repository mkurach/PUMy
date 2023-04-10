package com.example.lab3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button butGoBack;
    TextView listText;

    FragmentManager fragmentManager;
    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sensors, container, false);

        butGoBack = view.findViewById(R.id.buttonGoBack);
        listText = view.findViewById(R.id.listSensors);
        SensorManager mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        String tmp = "";
        for(Sensor s : list) {
            tmp = tmp.concat(s.getName().toString() + "\n");

        }

        listText.setText(tmp);



        butGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerView, ButtonsFragment.class, null)
                        .commit();

            }
        });

        return view;
    }
}
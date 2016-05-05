package project.mobile.newworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Weekly.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Weekly#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weekly extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String PREF_NAME = "Resource";


    Button home;
    Button accept;
    TextView dispWeeks;
    EditText choice;
    ListView weeklyList;

    int numWeeks;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Weekly() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Weekly.
     */
    // TODO: Rename and change types and number of parameters
    public static Weekly newInstance(String param1, String param2) {
        Weekly fragment = new Weekly();
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

        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        home = (Button) view.findViewById(R.id.home);
        accept = (Button) view.findViewById(R.id.accept);
        choice = (EditText) view.findViewById(R.id.choice);
        dispWeeks = (TextView) view.findViewById(R.id.dispWeeks);
        weeklyList = (ListView) view.findViewById((R.id.weeklyList));


        SharedPreferences settings = this.getActivity().getSharedPreferences(PREF_NAME, 0);
        numWeeks = settings.getInt("numWeeks", 0);
        dispWeeks.setText("choose from week 1-" + numWeeks );



        //SharedPreferences settings = getActivity().getSharedPreferences(PREF_NAME, 0);
        String dispWeek = settings.getString(("1"), "1");
        String[] arr = dispWeek.split(" ");
        for(int x = 0; x < arr.length; x++){
            String tmp = arr[x];
            String[] tmpArr = tmp.split(",");
            //date steps time distance
            tmp = "Date: " + tmpArr[0] + "\n\nSteps: " + tmpArr[1] + " Time: " + tmpArr[2] + " Distance: " + tmpArr[3];
            arr[x] = tmp;
        }



        ArrayList<String> weekList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            weekList.add(arr[i]);
        }


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, weekList);
        weeklyList.setAdapter(adapter);




        return view;
    }





    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OptionsScreen.class);
                startActivity(intent);
            }

        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weekChoice = 1;
                if ( !(choice.getText().toString().trim().length() == 0)){
                    boolean isString = false;
                    try {
                        weekChoice = (Integer.parseInt(choice.getText().toString().trim()));
                    }
                    catch (Exception e){
                        choice.setText("Must be an int");
                        isString = true;
                    }
                    if(!isString){
                        SharedPreferences settings = getActivity().getSharedPreferences(PREF_NAME, 0);
                        String dispWeek = settings.getString(("" + weekChoice), "This no is work " + weekChoice);
                        if(weekChoice >= 1 && weekChoice <= numWeeks){
                            String[] arr = dispWeek.split(" ");
                            for(int x = 0; x < arr.length; x++){
                                String tmp = arr[x];
                                String[] tmpArr = tmp.split(",");
                                //date steps time distance
                                tmp = "Date: " + tmpArr[0] + "\n\nSteps: " + tmpArr[1] + " Time: " + tmpArr[2] + " Distance: " + tmpArr[3];
                                arr[x] = tmp;
                            }



                            ArrayList<String> weekList = new ArrayList<>();
                            for(int i = 0; i < arr.length; i++){
                                weekList.add(arr[i]);
                            }


                            ArrayAdapter<String> adapter;
                            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, weekList);
                            weeklyList.setAdapter(adapter);

                        }
                    }
                }
            }

        });



    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

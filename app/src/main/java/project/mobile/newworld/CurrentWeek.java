package project.mobile.newworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentWeek.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentWeek#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentWeek extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String PREF_NAME = "Resource";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button viewWeekly;
    private int numWeeks;
    private String dispWeek;

    ListView listView;


    //private OnFragmentInteractionListener mListener;

    public CurrentWeek() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentWeek.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentWeek newInstance(String param1, String param2) {
        CurrentWeek fragment = new CurrentWeek();
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
        View view = inflater.inflate(R.layout.fragment_current_week, container, false);
        viewWeekly = (Button)view.findViewById(R.id.launchWeekly);
        listView  = (ListView) view.findViewById(R.id.currWeek);





        SharedPreferences settings = this.getActivity().getSharedPreferences(PREF_NAME, 0);
        numWeeks = settings.getInt("numWeeks", 0);
        System.out.print(numWeeks);
        dispWeek = settings.getString(("" + numWeeks), "This no is work " + numWeeks);

        //Week week = unparse(dispWeek);

        Toast toast = Toast.makeText(this.getActivity().getApplicationContext(), dispWeek, Toast.LENGTH_LONG);
        toast.show();

        String[] arr = dispWeek.split(" ");
        //for(int x = 0; x < arr.length; x++){
        //
        //}
        ArrayList<String> weekList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            weekList.add(arr[i]);
        }


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, weekList);
        listView.setAdapter(adapter);

        //ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        //listView.setAdapter();

        return view;
    }


    public Week unparse(String string){


        String[] dayList = string.split(" ");
        Week week = new Week("");


        for(int i = 0; i < dayList.length ; i++){
            String[] oneDay = dayList[i].split(",");
            Day day = new Day(oneDay[0], Integer.parseInt(oneDay[1]), Double.parseDouble(oneDay[2]), Double.parseDouble(oneDay[3]));
            week.setStartDate(day.getDate());
            week.addDay(day);
        }
        return week;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



        viewWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, Weekly.newInstance(null, null))
                        .addToBackStack(null)
                        .commit();
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

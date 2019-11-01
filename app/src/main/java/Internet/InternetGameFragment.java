package Internet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InternetGameFragment extends Fragment {
    private View view;
    public InternetGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_status, container, false);
        }
        new InternetGameWebClient(this).execute();
        return view;
    }
    void put(String val){
        ((TextView) view.findViewById(R.id.percentageList)).setText(val);
    }
}

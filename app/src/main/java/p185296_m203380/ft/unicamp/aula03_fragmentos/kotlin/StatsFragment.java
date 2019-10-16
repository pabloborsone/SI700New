package p185296_m203380.ft.unicamp.aula03_fragmentos.kotlin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import p185296_m203380.ft.unicamp.aula03_fragmentos.FragmentController;
import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

public class StatsFragment extends Fragment implements FragmentController {

    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_stats, container, false);
        }


        return view;
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        if (getFragmentManager() == null) {
            setTargetFragment(fragment, 0);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.authors_frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

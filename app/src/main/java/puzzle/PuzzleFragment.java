package puzzle;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import p185296_m203380.ft.unicamp.aula03_fragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {

    private LinearLayout view;
    public PuzzleFragment() {
        // Required empty public constructor
    }

    private void startPuzzle(int puzzle, LinearLayout View) {
        Board board = Boards.getPuzzle(puzzle);

        ImageView imageView = new ImageView(getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(board.getWidth(),board.getHeight()));

        ArrayList<ImageView> imageViews = new ArrayList();
        imageView.setImageResource(R.drawable.alisson);
        view.addView(imageView);

        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(
                new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.puzzle_fragment, container, false);
            startPuzzle(0, view);
        }

        return view;
    }

}

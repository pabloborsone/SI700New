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
    private int dummyLine;
    private int dummyColumn;
    public PuzzleFragment() {
        // Required empty public constructor
    }

    private void startPuzzle(int puzzle, LinearLayout view) {
        Board board = Boards.getPuzzle(puzzle);

        ArrayList<ImageView> imageViews = new ArrayList<>();
        ArrayList<LinearLayout> linearLayouts = new ArrayList<>();



        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(board.getWidth(),board.getHeight()));
                imageView.setImageResource(board.getGameBlock(i, j));

                if (board.getGameBlock(i,j) == R.drawable.dummy) {
                    dummyLine = i;
                    dummyColumn = j;
                }
                imageViews.add(imageView);
                addListeners(imageView, i, j, board);
            }
        }


        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayouts.add(row);
        }

        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                linearLayouts.get(i).addView(imageViews.get(i*board.getNumColumns()+j));
            }
            view.addView(linearLayouts.get(i));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.puzzle_fragment, container, false);
            startPuzzle(0, view);
        }

        return view;
    }

    private void addListeners(ImageView imageView, final int line, final int column, final Board board) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}

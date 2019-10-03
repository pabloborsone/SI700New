package puzzle;

import android.widget.ImageView;

import java.util.ArrayList;

public class EmptyPuzzle extends AbstractPuzzle {
    public EmptyPuzzle(Board board, ArrayList<ImageView> imageViews) {
        super(board, imageViews);
    }

    @Override
    public void addListener(ImageView imageView, int line, int column) {

    }

    @Override
    public boolean endGame() {
        return false;
    }
}

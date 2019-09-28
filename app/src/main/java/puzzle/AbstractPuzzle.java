package puzzle;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPuzzle {
    private List<ImageView> imageViewArrayList = new ArrayList<>();
    private Board board = Boards.getPuzzle(0);

    public  AbstractPuzzle(Board board,ArrayList<ImageView> imageViews) {
        this.board = board;
        this.imageViewArrayList = imageViews;
        startGame();
        redraw();
        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                addListener(imageViews.get(i * board.getNumColumns() + j), i, j);
            }
        }
    }

    public void startGame() {
        Board.startGame();
    }

    protected void redraw() {
        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                imageViewArrayList.get(i*board.getNumColumns()+j).setImageResource(board.getGameBlock(i, j));
            }
        }
    }

    public abstract void addListener(ImageView imageView, int line, int column);
    public abstract boolean endGame();

}

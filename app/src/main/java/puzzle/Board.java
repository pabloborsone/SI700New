package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private int numLines;
    private int numColumns;
    private List<Integer> blocks;
    private int width;
    private int height;
    private static ArrayList<Integer> gameIndex;

    public Board(int numLines, int numColumns, List<Integer> blocks, int width, int height) {
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.blocks = blocks;
        this.width = width;
        this.height = height;
        gameIndex = new ArrayList<>(blocks);
        Board.startGame();
    }

    public int getNumLines() {
        return numLines;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public List getBlocks() {
        return blocks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void startGame() {
        Collections.shuffle(gameIndex);

    }

    public int getCorrectBlock(int line, int column) {
        return blocks.get((line * numColumns) + column);
    }

    public int getGameBlock(int line, int column) {
        return gameIndex.get((line * numColumns) + column);
    }

    public void swap(int line1, int column1, int line2, int column2) {
        int aux = gameIndex.indexOf((line1 * numColumns) + column1);

        gameIndex.set((line1 * numColumns) + column1, gameIndex.indexOf((line2 * numColumns) + column2));

        gameIndex.set((line2 * numColumns) + column2, aux);

    }

}
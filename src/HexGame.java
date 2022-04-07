import java.awt.*;
import java.util.ArrayList;

public class HexGame {
    DisjointSet disjointSet;
    int size;
    ArrayList<ArrayList<String>> board;

    public HexGame(int size) {
        this.disjointSet = new DisjointSet(size);
        this.size = size;
        this.board = new ArrayList<>(size);
        for (int i=0; i < size; i++) {
            this.board.add(new ArrayList<>(size));
            for (int j=0; j < size; j++) {
                this.board.get(i).add("0");
            }
        }
    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        double x = (double) position / (double) this.size;

        if (isOccupied(position, x)) {
            return false;
        }
        if (displayNeighbors) {
            getNeighborsBlue(position);
        }

        this.board.get((int) Math.ceil(x) - 1).set((position - 1) % this.size, "B");
        // TODO: I think I just need a union or something here to get the disjoint set working

//        if (this.board.find(LEFT_EDGE) == board.find(RIGHT_EDGE)) {
//            return true;
//        }
        return false;
    }

    public boolean playRed(int position, boolean displayNeighbors) {
        double x = (double) position / (double) this.size;

        if (isOccupied(position, x)) {
            return false;
        }
        if (displayNeighbors) {
            getNeighborsRed(position);
        }

        this.board.get((int) Math.ceil(x) - 1).set((position - 1) % this.size, "R");
        // TODO: I think I just need a union or something here to get the disjoint set working

//        if (this.board.find(TOP_EDGE) == board.find(BOTTOM_EDGE)) {
//            return true;
//        }
        return false;
    }

    private boolean isOccupied(int position, double x) {
        return !this.board.get((int) Math.ceil(x) - 1).get((position - 1) % this.size).equals("0");
    }

    private ArrayList<Integer> getNeighborsRed(int position) {
        return null;
    }

    private ArrayList<Integer> getNeighborsBlue(int position) {
        // TODO: Write the actual logic for getting neighbors here.
        return null;
    }

    public Color[] getGrid() {
        return null;
    }
}

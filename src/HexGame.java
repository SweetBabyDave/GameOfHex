import java.util.ArrayList;
import java.util.Collections;

public class HexGame {
    DisjointSet disjointSetBlue;
    DisjointSet disjointSetRed;
    int size;
    int TOP_EDGE;
    int BOTTOM_EDGE;
    int LEFT_EDGE;
    int RIGHT_EDGE;
    ArrayList<ArrayList<String>> board;

    public HexGame(int size) {
        this.disjointSetBlue = new DisjointSet(size * size + 4);
        this.disjointSetRed = new DisjointSet(size * size + 4);
        this.size = size;
        this.TOP_EDGE = size * size;
        this.BOTTOM_EDGE = size * size + 1;
        this.LEFT_EDGE = size * size + 2;
        this.RIGHT_EDGE = size * size + 3;
//        for (int i=1; i <= size; i++) {
//            this.disjointSet.union(this.TOP_EDGE, i);
//            this.disjointSet.union(this.BOTTOM_EDGE, i);
//            this.disjointSet.union(this.LEFT_EDGE, i);
//            this.disjointSet.union(this.RIGHT_EDGE, i);
//        }
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
        ArrayList<Integer> neighbors;

        if (displayNeighbors) {
            System.out.println("Cell " + position + ": " + getNeighborsBlue(position));
        }
        if (isOccupied(position, x)) {
            return false;
        }

//        this.board.get((int) Math.ceil(x) - 1).set((position - 1) % this.size, "B");
//
//        if (position % this.size == 1) {
//            this.disjointSetBlue.union(this.LEFT_EDGE, position);
//        } else if (position % this.size == 0) {
//            this.disjointSetBlue.union(this.RIGHT_EDGE, position);
//        }
//        neighbors = getNeighborsBlue(position);
//
//        for (int neighbor: neighbors) {
//            if (this.disjointSetBlue.size[neighbor] >= 0) {
//                this.disjointSetBlue.union(this.disjointSetBlue.size[neighbor], position);
//            }
//        }

        return this.disjointSetBlue.find(LEFT_EDGE) == this.disjointSetBlue.find(RIGHT_EDGE);
    }

    public boolean playRed(int position, boolean displayNeighbors) {
        double x = (double) position / (double) this.size;
        ArrayList<Integer> neighbors;

        if (displayNeighbors) {
            System.out.println("Cell " + position + ": " + getNeighborsRed(position));
        }
        if (isOccupied(position, x)) {
            return false;
        }

        this.board.get((int) Math.ceil(x) - 1).set((position - 1) % this.size, "R");

        if (position < this.size) {
            this.disjointSetRed.union(this.TOP_EDGE, position);
            System.out.println();
        } else if (position >= (this.size * this.size) - this.size) {
            this.disjointSetRed.union(this.BOTTOM_EDGE, position);
        }
        neighbors = getNeighborsRed(position);

        for (int neighbor: neighbors) {
            if (this.disjointSetRed.size[neighbor]  >= 0) {
                this.disjointSetRed.union(this.disjointSetRed.size[neighbor], position);
            }
        }


        return this.disjointSetRed.find(TOP_EDGE) == this.disjointSetRed.find(BOTTOM_EDGE);
    }

    // This is technically wrong because I used math to solve it
    private boolean isOccupied(int position, double x) {
        return !this.board.get((int) Math.ceil(x) - 1).get((position - 1) % this.size).equals("0");
    }

    private ArrayList<Integer> getNeighborsRed(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        if (position == 1) {
            Collections.addAll(neighbors, position + 1, position + this.size);
        } else if (position == this.size) {
            Collections.addAll(neighbors, position - 1, position + this.size - 1, position + this.size);
        } else if (position == (this.size * this.size) - this.size + 1) {
            Collections.addAll(neighbors, position + 1, position - this.size, position - this.size + 1);
        } else if (position == this.size * this.size) {
            Collections.addAll(neighbors, position - 1, position - this.size);
        } else if (position % this.size == 1) {
            Collections.addAll(neighbors, position + 1, position + this.size, position - this.size + 1, position - this.size);
        } else if (position % this.size == 0) {
            Collections.addAll(neighbors, position - 1, position + this.size, position + this.size - 1, position - this.size);
        } else if (position <= this.size) {
            Collections.addAll(neighbors, position - 1, position + 1, position + this.size - 1, position + this.size);
        } else if (position > (this.size * this.size) - this.size) {
            Collections.addAll(neighbors, position - 1, position + 1, position - this.size + 1, position - this.size);
        } else {
            Collections.addAll(neighbors, position - 1, position + 1, position - this.size, position - this.size + 1, position + this.size - 1, position + this.size);
        }
        return neighbors;
    }

    private ArrayList<Integer> getNeighborsBlue(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        if (position == 1) {
            Collections.addAll(neighbors, position + 1, position + this.size);
        } else if (position == this.size) {
            Collections.addAll(neighbors, position - 1, position + this.size - 1, position + this.size);
        } else if (position == (this.size * this.size) - this.size + 1) {
            Collections.addAll(neighbors, position + 1, position - this.size, position - this.size + 1);
        } else if (position == this.size * this.size) {
            Collections.addAll(neighbors, position - 1, position - this.size);
        } else if (position % this.size == 1) {
            Collections.addAll(neighbors, position + 1, position + this.size, position - this.size + 1, position - this.size);
        } else if (position % this.size == 0) {
            Collections.addAll(neighbors, position - 1, position + this.size, position + this.size - 1, position - this.size);
        } else if (position <= this.size) {
            Collections.addAll(neighbors, position - 1, position + 1, position + this.size - 1, position + this.size);
        } else if (position > (this.size * this.size) - this.size) {
            Collections.addAll(neighbors, position - 1, position + 1, position - this.size + 1, position - this.size);
        } else {
            Collections.addAll(neighbors, position - 1, position + 1, position - this.size, position - this.size + 1, position + this.size - 1, position + this.size);
        }
        return neighbors;
    }
}

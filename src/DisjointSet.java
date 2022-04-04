import java.util.Arrays;

public class DisjointSet {
    private final int[] size;

    public DisjointSet(int size) {
        this.size = new int[size];
        Arrays.fill(this.size, -1);
    }

    // This is not smart union I don't think. I also don't think there is any path compression
    public void union(int root1, int root2) {
        this.size[root2] = root1;
    }

    // There might be an error if the node cannot be found in the find
    public int find(int node) {
        if (this.size[node] < 0) {
            return node;
        } else {
            return find(this.size[node]);
        }
    }
}

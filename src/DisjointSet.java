import java.util.Arrays;

public class DisjointSet {
    public final int[] size;

    public DisjointSet(int size) {
        this.size = new int[size];
        Arrays.fill(this.size, -1);
    }

    public void union(int root1, int root2) {
        if (this.size[root1] < this.size[root2]) {
            this.size[root2] = this.size[root1] + this.size[root2];
            this.size[root1] = root2;
        } else {
            this.size[root1] = this.size[root1] + this.size[root2];
            this.size[root2] = root1;
        }
    }

    public int find(int node) {
        if (this.size[node] < 0) {
            return node;
        } else {
            return this.size[node] = find(this.size[node]);
        }
    }

    public int[] getSize() {
        return this.size;
    }
}

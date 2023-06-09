package ngordnet.main;

import edu.princeton.cs.algs4.Queue;


import java.util.Set;
import java.util.TreeSet;

public class HyponymPath {

    private boolean[] marked; /**mark[v] is true iff v is hyponym of s*/
    private int[] edgeTo; /**edgeTo[v] is the direct hypernym of v along the way from s*/
    private Set<Integer> hypoIDs;
    private int s;

    private void bfs(DirectedGraph G, int s) {
        Queue<Integer> fringe = new Queue<Integer>();
        fringe.enqueue(s);
        marked[s] = true;
        hypoIDs.add(s);
        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    fringe.enqueue(w);
                    marked[w] = true;
                    hypoIDs.add(w);
                    edgeTo[w] = v;
                }
            }
        }
    }


    /**find all hyponyms of words*/
    public HyponymPath(DirectedGraph G, int s) {
        hypoIDs = new TreeSet<>();
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    /**is word v a hyponym of s? */
    public boolean hasPathTo(int v) {
        return marked[v];
    }


    public Set<Integer> findHypos(){
        return hypoIDs;
    }


}

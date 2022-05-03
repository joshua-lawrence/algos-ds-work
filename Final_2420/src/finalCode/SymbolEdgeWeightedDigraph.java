package finalCode;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * SymbolEdgeWeightedDiraph creates an EdgeWeightedDiraph based on a file that lists 
 * the names of the two vertices and the weight between the vertices separated by 
 * a delimiter (e.g. a comma, space, etc.)
 * 
 * The implementation is based on SymbolGraph from K. Wayen and R. Sedgewick.
 *
 * @author Modified in CSIS 2420 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SymbolEdgeWeightedDigraph {
    private ST<String, Integer> st;              // string -> index
    private String[] keys;                       // index  -> string
    private EdgeWeightedDigraph graph;           // the underlying edge-weighted digraph

	/**
	 * Initializes a digraph from a file using the specified delimiter. 
     * Each line in the file contains
     * The name of a vertex, the name of the other vertex,
     * and the weight between the two vertices separated by the delimiter.
	 * 
	 * @param filename  the name of the file
	 * @param delimiter the delimiter between fields
     */
    public SymbolEdgeWeightedDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        // first pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an aray
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the digraph by connecting first vertex on each
        // line to all others
		graph = new EdgeWeightedDigraph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delimiter);
			int v = st.get(a[0]);
			int w = st.get(a[1]);
			double weight = Double.parseDouble(a[2]);
			graph.addEdge(new DirectedEdge(v, w, weight));
		}
    }

    /**
     * Does the digraph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the digraph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the digraph.
     *
     * @return the digraph associated with the symbol digraph
     */
    public EdgeWeightedDigraph digraph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
         int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}


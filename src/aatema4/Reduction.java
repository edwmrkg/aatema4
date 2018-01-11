package aatema4;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Reduction {

	private int noOfVertices;
	private int noOfEdges;
	private int noOfColors;
	private TreeMap<Integer, Integer> edges = new TreeMap<Integer, Integer>();

	/**
	 * Reads from file and store the data of the graph. Number of vertices, of edges
	 * and of colors. Also builds a list of edges.
	 * @param file - a String representing the name of the file.
	 */
	public void readFile(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String[] line = in.readLine().split(" ");
			noOfVertices = Integer.parseInt(line[0]);
			noOfEdges = Integer.parseInt(line[1]);
			noOfColors = Integer.parseInt(line[2]);

			for (int i = 0; i < noOfEdges; i++) {
				line = in.readLine().split(" ");
				edges.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			}
			in.close();
		} catch (Exception e) {
			System.out.println("File not found!");
			return;
		}
	}

	public void solve(String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			for (int i = 0; i < noOfVertices; i++) {
				if (noOfColors > 1)
					out.print("(");
				for (int j = 0; j < noOfColors; j++) {
					int var = i * noOfColors + j;
					out.print("x" + var);
					if (j < noOfColors - 1)
						out.print("V");
				}
				if (noOfColors > 1)
					out.print(")");
				if (i < noOfVertices - 1)
					out.print("^");
			}
			if (noOfColors > 0) {
				for (int i = 0; i < noOfVertices; i++) {
					for (int j = 0; j < noOfColors - 1; j++) {
						for (int k = j + 1; k < noOfColors; k++) {
							out.print("(~x");
							out.print(i * noOfColors + j);
							out.print("V~x");
							out.print(i * noOfColors + k);
							out.print(")^");
						}
					}
				}
				int counter = 0;
				for (Entry<Integer, Integer> edge : edges.entrySet()) {
					int vertex1 = edge.getKey();
					int vertex2 = edge.getValue();
					for (int i = 0; i < noOfColors; i++) {
						++counter;
						out.print("(~x");
						out.print(noOfColors * vertex1 + i);
						out.print("V~x");
						out.print(noOfColors * vertex2 + i);
						out.print(")");
						if (counter < noOfColors * noOfEdges)
							out.print("^");
					}
				}
			}
			out.println();
			out.close();
		} catch (Exception e) {
			System.out.println("Cannot create output file.");
		}

	}
}

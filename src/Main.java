import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class Node {
	public String actor;
	public SimpleMovie movie;
	public Node prev;
	public int depth;
}

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		var movies = MovieDatabaseBuilder.getMovieDB("./movie_data");
		var actors = new HashMap<String, ArrayList<SimpleMovie>>();

		// Connect each actor to the movies they appeared in
		for (SimpleMovie movie : movies) {
			for (String actor : movie.getActors()) {
				if (actors.get(actor) == null)
					actors.put(actor, new ArrayList<SimpleMovie>());
				actors.get(actor).add(movie);
			}
		}

		// Accept user input
		System.out.println("Six Degrees of Kevin Bacon");
		System.out.print("Actor name: ");
		String query = scanner.nextLine();

		if (actors.get(query) == null) {
			System.out.println("No such actor");
			return;
		}

		var nodes = new LinkedList<Node>();
		var visited = new HashMap<String, Boolean>();

		Node last = null;
		Node root = new Node();
		root.actor = query;
		root.depth = 0;

		nodes.add(root);

		while (nodes.size() > 0) {
			var node = nodes.remove();

			if (node.depth >= 6) continue;
			if (visited.getOrDefault(node.actor, false).equals(true)) continue;
			visited.put(node.actor, true);

			for (SimpleMovie movie : actors.get(node.actor)) {
				for (String actor : movie.getActors()) {
					var next = new Node();
					next.actor = actor;
					next.movie = movie;
					next.prev = node;
					next.depth = node.depth + 1;
					nodes.add(next);

					if (actor.equals("Kevin Bacon")) {
						last = next;
						break;
					}
				}
				if (last != null) break; // thanks java!
			}
			if (last != null) break;
		}

		if (last == null) {
			System.out.println("Could not find a link between the actor and Kevin Bacon in less than 6 movies");
			return;
		}

		var path = new LinkedList<Node>();

		while (last != root) {
			path.addFirst(last);
			last = last.prev;
		}

		System.out.println();
		System.out.print(root.actor);

		int baconNumber = 0;
		while (path.size() > 0) {
			var node = path.remove();
			System.out.println(" was in " + node.movie.getTitle() + " with");
			System.out.print(node.actor);
			baconNumber++;
		}

		System.out.println("\n");
		System.out.println("Bacon number: " + baconNumber);
	}
}

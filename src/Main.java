import java.util.Collections;
import java.util.ArrayList;

public class Main {
	public static ArrayList<String> removeDupsString(ArrayList<String> sortedList) {
		ArrayList<String> result = new ArrayList<String>();
		result.add(sortedList.get(0));
		for (int i = 1; i < sortedList.size(); i++)
			if (!sortedList.get(i).equals(sortedList.get(i - 1)))
				result.add(sortedList.get(i));
		return result;
	}

	public static ArrayList<Integer> removeDupsInteger(ArrayList<Integer> sortedList) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(sortedList.get(0));
		for (int i = 1; i < sortedList.size(); i++)
			if (!sortedList.get(i).equals(sortedList.get(i - 1)))
				result.add(sortedList.get(i));
		return result;
	}

	public static void main(String[] args) {
		ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("./movie_data");
		ArrayList<String> actors = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> coactors = new ArrayList<ArrayList<Integer>>();

		// Generate list of all actors, removing duplicates
		for (SimpleMovie movie : movies)
			for (String actor : movie.getActors())
				actors.add(actor);
		Collections.sort(actors);
		actors = removeDupsString(actors);

		for (int i = 0; i < actors.size(); i++)
			coactors.add(new ArrayList<Integer>());
		for (int i = 0; i < actors.size(); i++) {
			String actor = actors.get(i);
			for (SimpleMovie movie : movies) {
				if (movie.getActors().contains(actor)) {
					ArrayList<String> mActors = movie.getActors();
					for (int j = 0; j < mActors.size(); j++) {
						String coactor = mActors.get(j);
						if (coactor.equals(actor)) {
							coactors.get(i).add(j);
						}
					}
				}
			}
		}
		for (int i = 0; i < actors.size(); i++) {
			Collections.sort(coactors.get(i));
			coactors.set(i, removeDupsInteger(coactors.get(i)));
		}

		System.out.println(actors.size());
		for (int i = 0; i < 5000; i++)
			System.out.println(coactors.get(i).size());
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieDatabaseBuilder {
	public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
		ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
		try {
			File movieData = new File(fileName);
			Scanner reader = new Scanner(movieData);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] data = line.split("---");
				if (data.length > 1) {
					SimpleMovie s = new SimpleMovie(data[0], data[1]);
					movies.add(s);
				}

			}
		}
		catch (FileNotFoundException noFile) {
			System.out.println("File not found!");
			return null;
		}
		return movies;
	}
}

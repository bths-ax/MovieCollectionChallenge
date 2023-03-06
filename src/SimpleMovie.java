public class SimpleMovie {
	private String title;
	private String[] actors;

	public SimpleMovie(String title, String actorsData) {
		this.title = title;
		this.actors = actorsData.split(":");
	}

	public String getTitle() { return title; }
	public String getActors() { return actors; }
}

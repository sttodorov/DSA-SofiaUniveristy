
public class StartPoint {
	
	public static void main(String[] args) {
		
		BookIndexer61675 indexer = new BookIndexer61675();
		String[] keywords = new String[]{"lorem", "quisque", "aenean"};
		indexer.buildIndex("E:\\University\\2 Course\\DSA\\SecondHW\\test.txt", keywords, "E:\\University\\2 Course\\DSA\\SecondHW\\result.txt");
	}

}

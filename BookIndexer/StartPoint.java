
public class StartPoint {
	
	public static void main(String[] args) {
		SuffixTrie trie = new SuffixTrie();
		
		trie.Add("Hello");
		trie.Add("Assan");
		trie.Add("As");
		trie.Add("Aswer");
		trie.Add("Himoa");
		
		System.out.println(trie.Contains("Hello"));
		System.out.println(trie.Contains("H"));
		System.out.println(trie.Contains("As"));
		System.out.println(trie.Contains("Assani"));
		System.out.println(trie.Contains("Assan"));
		
		BookIndexer indexer = new BookIndexer();
		String[] keywords = new String[]{"lorem", "quisque", "aenean"};
		indexer.buildIndex("E:\\University\\2 Course\\DSA\\SecondHW\\test.txt", keywords, "E:\\University\\2 Course\\DSA\\SecondHW\\result.txt");
	}

}

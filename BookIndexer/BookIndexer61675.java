import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class BookIndexer61675 implements IBookIndexer{
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	private ArrayList<SuffixTrie> tries;
	private HashMap<String, SortedSet<Integer>> wordsPages;
	
	public BookIndexer61675()
	{
		this.tries = new ArrayList<SuffixTrie>();
		this.wordsPages = new HashMap<String, SortedSet<Integer>>();
	}
	
	@Override
	public void buildIndex(String bookFilePath, String[] keywords, String indexFilePath)
	{
		this.BuidTriesForEachPage(bookFilePath);
		Arrays.sort(keywords);
	    
		for (String keyword : keywords) {
			SortedSet<Integer> currentKeyWordPages = new TreeSet<Integer>();
			
			for (SuffixTrie pageWords : tries) {
				if(pageWords.Contains(keyword))
				{
					currentKeyWordPages.add(pageWords.Page());
				}
			}
			this.wordsPages.put(keyword, currentKeyWordPages);
		}
		this.WriteIndexInFile(indexFilePath, keywords);
	    
	}
	private void WriteIndexInFile(String indexFilePath, String[] keywords)
	{
		try
		{	
			File file = new File(indexFilePath);
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    
		    writer.write("INDEX");
		    writer.newLine();
		    
		    for (String keyword : keywords) {
		    	SortedSet<Integer> currentWordPages = this.wordsPages.get(keyword);
		    	String currentLine = "";
		    	int lastPageNumber = -3;
		    	boolean flag = false;
				if(currentWordPages.size() > 0)
				{
					writer.write(keyword);
				}
		    	for (Integer page : currentWordPages) {
		    		if(lastPageNumber == page-1)
		    		{
		    			
		    			flag=true;
		    			lastPageNumber = page;
		    		}
		    		else
		    		{
		    			if(flag)
		    			{
		    				currentLine+= "-" + lastPageNumber;
		    				flag = false;
		    			}
		    			
		    			currentLine += ", " + page;
		    			lastPageNumber = page;
		    		}
				}

	    		if(flag)
	    		{
	    			currentLine+= "-" + lastPageNumber;	
	    		}
	    		if(currentLine!= "")
	    		{
			    	writer.write(currentLine);
			    	writer.newLine();
	    		}
			}
		    
		    writer.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	private void BuidTriesForEachPage(String bookFilePath)
	{
		Scanner sc = null;
		SuffixTrie currentPageTrie = null;
        try {
            sc = new Scanner(new FileReader(bookFilePath));
            
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] wordsOnCurrentLine = text.split(" ");

                if (wordsOnCurrentLine[0].equalsIgnoreCase("===")) {
                	int currPageNumber = Integer.parseInt(wordsOnCurrentLine[2]);
                	if(currentPageTrie != null)
                	{
		        		this.tries.add(currentPageTrie);
		        		currentPageTrie = new SuffixTrie(currPageNumber);
                	}
                	else {
                		currentPageTrie = new SuffixTrie(currPageNumber);
                	}
                	
                } else {
                	for (String word : wordsOnCurrentLine) {
                		if(!currentPageTrie.Contains(word))
                		{
                			currentPageTrie.Add(word);
                		}
					}
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input file " + bookFilePath + " not found");
            System.exit(1);
        } finally {
        	this.tries.add(currentPageTrie);
            sc.close();
        }
	
	}
}

public class SuffixTrie {

	private CharNode root;
	private Integer page;
	
	public SuffixTrie()
	{
		this.root = new CharNode('$', false);
	}
	
	public SuffixTrie(Integer initPage)
	{
		this();
		this.page = initPage; 
	}
	
	public Integer Page()
	{
		return this.page;
	}
	
	public void Add(String word)
	{
		if(word.isEmpty())
		{
			return;
		}
		AddChars(word.toLowerCase(), this.root, 0);
	}
	
	public Boolean Contains(String word)
	{
		if(word.isEmpty())
		{
			return false;
		}
		return Contains(word.toLowerCase(), this.root,0);
	}
	
	private Boolean Contains(String word, CharNode node, Integer index)
	{
		char currentLetter = word.charAt(index);
		
		if(!node.Children().containsKey(currentLetter))
		{
			return false;
		}
		else
		{
			CharNode child = node.Children().get(currentLetter);
			if(index == word.length()-1)
			{
				if(child.IsLast())
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return Contains(word, child, index+1);
			}
		}
	}
	
	private void AddChars(String word, CharNode node, Integer index)
	{
		char currentChar = word.charAt(index);
		boolean isLast = index == word.length() -1;
		
		if(node.Children().containsKey(currentChar))
		{
			CharNode child = node.Children().get(currentChar);
			if(isLast)
			{
				child.IsLast(true);
			}
			else
			{
				AddChars(word, child, index+1);
			}
		}
		else
		{
			CharNode child = new CharNode(currentChar, isLast);
			node.Children().put(currentChar, child);
			if(!isLast)
			{
				AddChars(word, child, index+1);
			}
		}
	}
	
}
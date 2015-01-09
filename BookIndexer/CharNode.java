import java.util.HashMap;

public class CharNode {

	private Character letter;
	private HashMap<Character, CharNode> children;
	private Boolean isLast;
	
	public CharNode(Character initChar, Boolean initIsLast)
	{
		this.letter = initChar;
		this.isLast = initIsLast;
		this.children = new HashMap<Character, CharNode>();
	}
	
	public Boolean IsLast()
	{
		return this.isLast;
	}
	public void IsLast(Boolean setIsLast)
	{
		this.isLast = setIsLast;
	}
	public Character Letter()
	{
		return this.letter;
	}
	public HashMap<Character, CharNode> Children()
	{
		return this.children;
	}
}

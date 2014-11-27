import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class FileEncoder61675 implements FileEncoderFN{
	private Set<Integer> primeNumbers;
	
	public FileEncoder61675()
	{
		this.primeNumbers = new HashSet<Integer>();
	}
	
	private void RunEratosthenesSieve(int upperBound) {
	      int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
	      boolean[] isComposite = new boolean[upperBound + 1];
	      for (int m = 2; m <= upperBoundSquareRoot; m++) {
	            if (!isComposite[m]) {
	                  this.primeNumbers.add(m);
	                  for (int k = m * m; k <= upperBound; k += m)
	                        isComposite[k] = true;
	            }
	      }
	      for (int m = upperBoundSquareRoot; m <= upperBound; m++)
	            if (!isComposite[m])
	            {
	                  this.primeNumbers.add(m);
	            }
	}

	@Override
	public void encode(String sourceFile, String destinationFile, LinkedList<Character> key)
	{
		File file = new File(sourceFile);
		
		if(this.primeNumbers.size()==0)
		{
			this.RunEratosthenesSieve((int)file.length());
		}
		
		try
		{
			FileInputStream source = new FileInputStream(file);
			BufferedInputStream inputFileStream = new BufferedInputStream(source);

			FileOutputStream encodedFile = new FileOutputStream(destinationFile);
			BufferedOutputStream outputFileStream = new BufferedOutputStream(encodedFile);
			
			
			for (int i = 0;  ; i++) {
				int currentByte = inputFileStream.read();
				
				if(currentByte == -1)
				{
					break;
				}
				else if(this.primeNumbers.contains(i))
				{
					outputFileStream.write(currentByte);
				}
				else
				{
					outputFileStream.write(key.get(currentByte));
				}
			}
			
			inputFileStream.close();
			outputFileStream.close();
			
		}
		catch(Exception ex)
		{
			System.out.println("Something went wrong!!!");
			ex.printStackTrace();
		}
		
	}

	@Override
	public  void decode(String sourceFile, String destinationFile, LinkedList<Character> key)
	{
		File file = new File(sourceFile);
		if(this.primeNumbers.size()==0)
		{
			this.RunEratosthenesSieve((int)file.length());
		}
		
		try
		{
			FileInputStream source = new FileInputStream(file);
			BufferedInputStream inputFileStream = new BufferedInputStream(source);

			FileOutputStream encodedFile = new FileOutputStream(destinationFile);
			BufferedOutputStream outputFileStream = new BufferedOutputStream(encodedFile);
			
			for (int i = 0;  ; i++) {
				int currentByte = inputFileStream.read();
				
				if(currentByte == -1)
				{
					break;
				}
				else if(this.primeNumbers.contains(i))
				{
					outputFileStream.write(currentByte);
				}
				else
				{
					outputFileStream.write(key.get(currentByte));
				}
			}
			
			inputFileStream.close();
			outputFileStream.close();
			
		}
		catch(Exception ex)
		{
			System.out.println("Something went wrong!!!");
			ex.printStackTrace();
		}
	}
	
}

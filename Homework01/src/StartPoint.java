import java.util.LinkedList;
import java.util.Scanner;

public class StartPoint {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Write full path to source file: ");
		System.out.print("Write full path to destination file: ");
		scanner.close();
		
		LinkedList<Character> list = new LinkedList<Character>();
		for (int i = 255; i > -1; i--) {
			list.add((char) i);
		}
		
		FileEncoder61675 encoder = new FileEncoder61675();
		long time;
		
		// This test was with 350Kb file - work for ~100ms
		time = System.currentTimeMillis();
		encoder.encode("C:\\Users\\Stoyan\\Desktop\\IMG_4672.JPG", "C:\\Users\\Stoyan\\Desktop\\encoded-result.txt",list);
		encoder.decode("C:\\Users\\Stoyan\\Desktop\\encoded-result.txt", "C:\\Users\\Stoyan\\Desktop\\result.jpg",list);
		time = System.currentTimeMillis() - time;
		System.out.println(time + " miliseconds");
		
	}
}

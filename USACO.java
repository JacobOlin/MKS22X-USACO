import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class USACO{
  public static int bronze(String filename) {
    ArrayList<String> a = new ArrayList<String>();
    try {
      File f = new File(filename);
      Scanner s = new Scanner(f);
      while (s.hasNext()) {
        a.add(s.nextLine());
      }
    }catch(FileNotFoundException e) {
      System.out.println("Input a valid file name");
    }
    String first = a.get(0);
    int[] firstLine = new int[4];
    int ind = 0;
    int j = 0;
    for (int i = 0;i < first.length();i += 1) {
      if (first.charAt(i) == ' ') {
        firstLine[ind] = Integer.parseInt(first.substring(j,i));
        ind += 1;
        j = i + 1;
      }
      if (i+1 == first.length()) {
        firstLine[3] = Integer.parseInt(first.substring(j,i+1));
      }
    }
    for (int i = 0;i < firstLine.length;i += 1) {
      System.out.print(firstLine[i] + " ");
    }
    return 0;
  }
}

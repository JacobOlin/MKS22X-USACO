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
    return 0;
  }
}

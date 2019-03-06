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
    processString(first,firstLine);
    int[][] board = new int[firstLine[0]][firstLine[1]];
    for (int i = 0;i < board.length;i += 1) {
      processString(a.get(i+1),board[i]);
    }

    int[][] moves = new int [firstLine[3]][3];
    for (int i = 0;i < moves.length;i += 1) {
      processString(a.get(i+1+firstLine[0]),moves[i]);
    }
    /*for (int i = 0;i < firstLine.length;i += 1) {
      System.out.print(firstLine[i] + " ");
    }
    System.out.println("\n");
    for (int i = 0;i < board.length;i += 1) {
      for (int j = 0;j < board[i].length;j += 1) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    for(int i = 0;i < moves.length;i += 1) {
      for (int j = 0;j < moves[i].length;j += 1) {
        System.out.print(moves[i][j] + " ");
      }
      System.out.println();
    }*/
    return 0;
  }

  public static void processString(String s,int[] a) {
    int ind = 0;
    int j = 0;
    for (int i = 0;i < s.length();i += 1) {
      if (s.charAt(i) == ' ') {
        a[ind] = Integer.parseInt(s.substring(j,i));
        ind += 1;
        j = i + 1;
      }
      if (i+1 == s.length()) {
        a[a.length - 1] = Integer.parseInt(s.substring(j,i+1));
      }
    }
  }
}

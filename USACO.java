import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class USACO{

private static int[]firstLine;
private static int[][]board;
private static int[][]moves;
private static int[]firstLineS;
private static char[][]boardS;
private static int[]startEnd;

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
    firstLine = new int[4];
    processString(first,firstLine);
    board = new int[firstLine[0]][firstLine[1]];
    for (int i = 0;i < board.length;i += 1) {
      processString(a.get(i+1),board[i]);
    }

    moves = new int [firstLine[3]][3];
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
    for (int i = 0;i < moves.length;i += 1) {
      makeMove(moves[i]);
    }
    /*System.out.println("----------------");
    for (int i = 0;i < board.length;i += 1) {
      for (int j = 0;j < board[i].length;j += 1) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }*/
    sinkDown(firstLine[2]);
    return 72 * 72 * addUp();
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

  public static void makeMove(int[] a) {
    int max = 0;
    int xcor = 0;
    int ycor = 0;
    for (int i = 0;i < 3;i += 1) {
      for (int j = 0;j < 3;j += 1) {
        if (board[i+a[0] - 1][j+a[1] -1] >= max) {
          xcor = i+a[0] - 1;
          ycor = j+a[1] - 1;
          max = board[xcor][ycor];
        }
      }
    }
    board[xcor][ycor] -= a[2];
    for (int i = a[0] - 1;i < a[0] + 2;i += 1) {
      for (int j = a[1] - 1;j < a[1] + 2;j += 1) {
        if (board[i][j] >= board[xcor][ycor]) {
          board[i][j] = board[xcor][ycor];
        }
      }
    }
  }

  public static void sinkDown(int d) {
    for (int i = 0;i < board.length;i += 1) {
      for (int j = 0;j < board[i].length;j += 1) {
        board[i][j] = d - board[i][j];
        if (board[i][j] < 0) {
          board[i][j] = 0;
        }
      }
    }
  }

  public static int addUp() {
    int count = 0;
    for (int i = 0;i < board.length;i += 1) {
      for (int j = 0;j < board[i].length;j += 1) {
        count += board[i][j];
      }
    }
    return count;
  }

  public static int silver(String filename) {
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
    firstLineS = new int[3];
    processString(a.get(0),firstLineS);
    boardS = new char[firstLineS[0]][firstLineS[1]];
    for (int i = 0;i < boardS.length;i += 1) {
      for (int j = 0;j < a.get(i+1).length();j += 1) {
        boardS[i][j] = a.get(i+1).charAt(j);
      }
    }
    startEnd = new int[4];
    processString(a.get(a.size()-1),startEnd);

    int[][] board2 = new int[boardS.length][boardS[0].length];
    for (int i = 0;i < board2.length;i += 1) {
      for (int j = 0;j < board2[i].length;j += 1) {
        if (boardS[i][j] == '*') {
          board2[i][j] = -1;
        }
        else {
          board2[i][j] = 0;
        }
      }
    }

    /*for (int i = 0;i < board2.length;i += 1) {
      for (int j = 0;j < board2[i].length;j += 1) {
        System.out.print(board2[i][j] + " ");
      }
      System.out.println();
    }*/
    board2[startEnd[0]-1][startEnd[1]-1] = 1;
    changeBoard(firstLineS[2],(startEnd[0] + startEnd[1] + 1)%2,board2);

    return board2[startEnd[2]-1][startEnd[3]-1];
  }

  public static boolean inBounds(int i,int j) {
    return i >= 0 && j >= 0 && i < boardS.length && j < boardS[0].length;
  }

  public static void changeBoard(int count,int EvenOdd,int[][]field) {
    if (count > 0) {
      for (int i = 0;i < field.length;i += 1) {
        for (int j = 0;j < field[i].length;j += 1) {
          if ((i + j) % 2 == EvenOdd && field[i][j] != -1) {
            if (inBounds(i-1,j) && field[i-1][j] != -1) {
              field[i][j] += field[i-1][j];
            }
            if (inBounds(i+1,j) && field[i+1][j] != -1) {
              field[i][j] += field[i+1][j];
            }
            if (inBounds(i,j-1) && field[i][j-1] != -1) {
              field[i][j] += field[i][j-1];
            }
            if (inBounds(i,j+1) && field[i][j+1] != -1) {
              field[i][j] += field[i][j+1];
            }
          }
        }
      }

      for (int i = 0;i < field.length;i += 1) {
        for (int j = 0;j<field[i].length;j += 1) {
          if ((i+j) % 2 != EvenOdd && field[i][j] != -1) {
            field[i][j] = 0;
          }
        }
      }
      changeBoard(count - 1,(EvenOdd + 1) %2,field);
    }

  }

}

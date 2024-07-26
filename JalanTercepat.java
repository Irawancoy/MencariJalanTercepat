package MencariJalanTercepat;
import java.util.ArrayList;
import java.util.List;

public class JalanTercepat {
    static int[] baris = { -1, 1, 0, 0 };
    static int[] kolom = { 0, 0, -1, 1 };
    static String[] arah = { "atas", "bawah", "kiri", "kanan" };
    static String start = "^";
    static String end = "*";
    static String wall = "#";

    public static void main(String[] args) {
        String[][] peta = {
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "*", "#" },
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", " ", " ", " ", " ", " ", " ", " ", "#", " ", " ", " ", " ", " ", "#", " ", " ", " ", " ", " ", " ", " ", "#" },
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#" },
                { "#", " ", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "^", " ", " ", " ", "#" },
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" }
        };

        for (String[] strings : peta) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }

        List<String> path = new ArrayList<>();
        List<String> directionsList = new ArrayList<>();
        int rows = peta.length;
        int cols = peta[0].length;
        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (peta[i][j].equals(start)) {
                    startRow = i;
                    startCol = j;
                } else if (peta[i][j].equals(end)) {
                    endRow = i;
                    endCol = j;
                }
            }
        }

        if (startRow == -1 || endRow == -1) {
            System.out.println("Tidak ada jalan");
        } else {
            boolean[][] visited = new boolean[rows][cols];
            dfs(peta, startRow, startCol, endRow, endCol, visited, path, directionsList, new ArrayList<>());
        }

        int count = 1;
        for (int i = directionsList.size() - 1; i > 0; i--) {
            if (directionsList.get(i).equals(directionsList.get(i - 1))) {
                count++;
            } else {
                System.out.println(directionsList.get(i) + " " + count);
                count = 1;
            }
        }
        if (!directionsList.isEmpty()) {
           System.out.println(directionsList.get(0) + " " + count);
        }
        // Menampilkan total langkah yang diambil
         if(!directionsList.isEmpty()){
            System.out.println("Total langkah yang diambil: " + directionsList.size());
         }else{
            System.out.println("Tidak ada jalan");
         }
    }

            
    private static boolean dfs(String[][] peta, int row, int col, int endRow, int endCol, boolean[][] visited, List<String> path, List<String> directionsList, List<String> currentPath) {
        if (row == endRow && col == endCol) {
            path.addAll(currentPath);
            return true;
        }

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int newRow = row + baris[i];
            int newCol = col + kolom[i];
            if (isValidMove(newRow, newCol, peta, visited)) {
                currentPath.add(row + "," + col);
                if (dfs(peta, newRow, newCol, endRow, endCol, visited, path, directionsList, currentPath)) {
                    directionsList.add(arah[i]);
                    return true;
                }
                currentPath.remove(currentPath.size() - 1);
            }
        }

        visited[row][col] = false;
        return false;
    }

    private static boolean isValidMove(int row, int col, String[][] peta, boolean[][] visited) {
                
        return row >= 0 && row < peta.length && col >= 0 && col < peta[0].length && !peta[row][col].equals(wall) && !visited[row][col];
    }
}
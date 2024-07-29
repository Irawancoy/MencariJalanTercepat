package MencariJalanTercepat;

import java.util.ArrayList;
import java.util.List;

public class JalanTercepat {
    static int[] baris = {-1, 1, 0, 0};
    static int[] kolom = {0, 0, -1, 1};
    static String[] arah = {"atas", "bawah", "kiri", "kanan"};
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

        // String[][] peta = {
        //         {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
        //         {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "*", "#"},
        //         {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#"},
        //         {"#", " ", " ", " ", " ", " ", " ", " ", "#", " ", " ", " ", " ", " ", "#", " ", " ", " ", " ", " ", " ", " ", "#"},
        //         {"#", " ", "#", "#", "#", "#", "#", "#", "#", " ", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#"},
        //         {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
        //         {"#", " ", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", " ", "#"},
        //         {"#", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "^", " ", " ", " ", "#"},
        //         {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}
        // };

        // String[][] peta = {
        //         { "#", "*", "#","^", "#" } };

        for (String[] strings : peta) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }

        int rows = peta.length;
        int cols = peta[0].length;
        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;

        // Cari titik awal dan akhir
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
            return;
        }

        boolean[][] visited = new boolean[rows][cols];
        List<int[]> queue = new ArrayList<>();
        List<String> directions = new ArrayList<>();
        queue.add(new int[]{startRow, startCol});
        directions.add("");

        int index = 0;
        List<String> path = null;

        while (index < queue.size()) {
            int[] cell = queue.get(index);
            String direction = directions.get(index);
            int row = cell[0], col = cell[1];
            index++;

            if (row == endRow && col == endCol) {
                path = new ArrayList<>();
                for (String dir : direction.split(",")) {
                    if (!dir.isEmpty()) {
                        path.add(dir);
                    }
                }
                break;
            }

            visited[row][col] = true;

            for (int i = 0; i < 4; i++) {
                int newRow = row + baris[i];
                int newCol = col + kolom[i];
                if (newRow >= 0 && newRow < peta.length && newCol >= 0 && newCol < peta[0].length 
                        && !peta[newRow][newCol].equals(wall) && !visited[newRow][newCol]) {
                    queue.add(new int[]{newRow, newCol});
                    directions.add(direction + "," + arah[i]);
                }
            }
        }

        if (path == null) {
            System.out.println("Tidak ada jalan");
        } else {
            int count = 1;
            for (int i = 0; i < path.size() - 1; i++) {
                if (path.get(i).equals(path.get(i + 1))) {
                    count++;
                } else {
                    System.out.println(count +" "+ path.get(i));
                    count = 1;
                }
            }
            if (!path.isEmpty()) {
                System.out.println(path.get(path.size() - 1) + " " + count);
            }
            System.out.println(path.size() + " langkah");
        }
    }
}

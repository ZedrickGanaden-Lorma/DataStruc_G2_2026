public class Magic {
    public static void printArr(int[][] arr) {
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                System.out.printf("%3d", arr[x][y]);
            }
            System.out.println();
        }
    }

    public static void reversePrintArr(int[][] arr) {
        for (int x = arr.length - 1; x >= 0; x--) {
            System.out.print(x + " : ");
            for (int y = 0; y < arr[x].length; y++) {
                System.out.printf("%3d", arr[x][y]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int ax, ay;
        ax = ay = 9;
        int[][] arr = new int[ax][ay];
        // for (int a = 1; true; a++) {
        // }
        int y = 0;
        // int y = ay - 1;
        int x = (int) ax / 2;
        for (int v = 1; v < 100; v++) {
            System.out.printf("x : %d  y : %d   %n", x, y);
            int j = 0;
            if (arr[y][x] == 0) {
                arr[y][x] = v;
            } else {
                System.out.println("Jumping");
                System.out.println(x + " " + y);
                y = ((y - 2) + ay) % ay;
                x = ((x - 1) + ax) % ax;
                System.out.println(x + " " + y);
                if (arr[y][x] == 0) {
                    arr[y][x] = v;
                } else {
                    break;
                }
            }
            x = (x + 1) % ax;
            y = (y + 1) % ay;
            reversePrintArr(arr);
            System.out.println();
        }
    }
}

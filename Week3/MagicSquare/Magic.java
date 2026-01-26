public class Magic {
    public static void printArr(int[][] arr) {
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                System.out.printf("%3d", arr[x][y]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int ax, ay;
        ax = ay = 5;
        int[][] arr = new int[ax][ay];
        printArr(arr);
        int y = ay - 1;
        int x = (int) ax / 2;
        for (int a = 1; true; a++) {
        }
    }
}
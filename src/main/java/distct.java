import java.util.ArrayList;

public class distct {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 2, 4, 1, 4, 2, 3, 5};

        System.out.println(1^2);
        System.out.println(3^3);
        int e = 0;
        for (int cur : a) {
            int bf = e;
            e ^= cur;
            System.out.println("cur=" + cur + ",bf="+bf+",e=" + e);
        }
        System.out.println(e);
    }
}

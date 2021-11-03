package lewy.typicalTopTen;

/**
 * 二分查询（非递归方式）
 * {1,3,8,10,11,67,100}，编程实现二分查找，要求使用非递归方式完成。
 */
public class BinarySearchNonRecursive {

    public static void main(String[] args) {

        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 10);
        if (index != -1) {
            System.out.println("find it，index is：" + index);
        } else {
            System.out.println("can not find it");
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1; // 向左找
            } else {
                left = mid + 1; // 向右找
            }
        }
        return -1;
    }
}

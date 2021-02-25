package cn.xzxy.lewy.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BasicSort {

    /**
     * 内存排序：交换排序：冒泡排序
     *                   快速排序
     *          插入排序：直接插入排序
     *                   希尔排序
     *          选择排序：选择排序
     *                   堆排序
     *          归并排序
     *          基数排序
     * 内外存排序：
     */

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 冒泡排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, minIndex, i);
        }
        return array;
    }

    /**
     * 插入排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static int[] insertSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
                swap(array, j, j + 1);
            }
        }
        return array;
    }

    /**
     * 希尔排序
     *
     * @param array 未排序的
     * @return 以排好序的
     */
    private static int[] shellSort(int[] array) {
        int len = array.length;
        if (len == 0) {
            return array;
        }
        //定义初始增量，希尔建议的增量是length的一半
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                //这里要注意，while循环执行结束后preIndex又被减了一个gap，是负值
                //此时再加上gap，实际上就是array[preIndex]
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 归并排序
     *
     * @param array 未排好序的
     * @return 排好序的
     */
    private static int[] mergeSort(int[] array) {
        if (array.length < 2) return array;

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        //进行递归排序
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序：将两端排序好的数组结合成一个排序数组
     *
     * @param left  左半部分
     * @param right 右半部分
     * @return 排好序的
     */
    private static int[] merge(int[] left, int[] right) {
        //定义最后结果的数组
        int[] result = new int[left.length + right.length];
        // index : 最后结果数组的索引
        // i ：左半部分数组的索引
        // j ：右半部分数组的索引
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)   //左半部分结束
                result[index] = right[j++];
            else if (j >= right.length)   //右半部分结束
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }
    /**
     * 解析：
     * 22, 3, 40, 8    27, 16, 33, 19
     * 3 8 22 40       16 19 27 33
     * 假设初始index=0 i=0 j=0 len=8 left.length = right.length = 4
     * 1: index = 0; left[0] = 3 right[0] = 16
     *    3 < 16
     *    result[0] = left[0] = 3
     * 2: index = 1;  left[1] = 8 right[0] = 16
     *    8 < 16
     *    result[1] = left[1] = 8
     * 3: index = 2; left[2] = 22 right[0] = 16
     *    22 > 16
     *    result[2] = right[0] = 16
     * 4: index = 3; left[2] = 22 right[1] = 19
     *    22 > 19
     *    result[3] = right[1] = 19
     * 5: index = 4; left[2] = 22 right[2] = 27
     *    result[4] = left[2] = 22
     * 6: index = 5; left[3] = 40 right[2] = 27
     *    result[5] = right[2] = 27
     * 7: result[6] = right[3] = 33
     * 8: index = 7; left[3] = 40 j = 4 >= 4
     *    result[7] left[3] = 40
     */

    /**
     * 快速排序
     *
     * @param array 未排好序的
     * @param start 开始索引 最初一般为0
     * @param end   结束索引  最初一般为length -1
     * @return 排好序的
     */
    private static int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end > array.length || start > end)
            return null;
        //寻找基准所在的索引，作用是作为下一次划分分区的标准
        //随机确定基准
        //int index = partition1(array, start, end);
        //三数取中确定基准
        int index = partition2(array, start, end);

        //分治法，递归调用
        if (index > start)
            quickSort(array, start, index - 1);
        if (index < end)
            quickSort(array, index + 1, end);
        return array;
    }

    /**
     * 快排：partition随机确定基准的索引值
     *
     * @param array 未排好序
     * @param start 开始索引
     * @param end   结束索引
     * @return 最小索引
     */
    private static int partition1(int[] array, int start, int end) {
        //获得随机基准的索引
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int index = start - 1;
        //把随机基准位置的元素和start位置元素互换
        swap(array, pivot, start);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                index++;
                if (i > index) {
                    swap(array, i, index);
                }
            }
        }
        return index;
    }

    /**
     * 快排：partition三数取中确定基准的索引值
     *
     * @param array 未排好序
     * @param start 开始索引
     * @param end   结束索引
     * @return 最小索引
     */
    private static int partition2(int[] array, int start, int end) {
        //三数取中：数组中间元素的下标
        int middle = start + (end - start) / 2;
        //确定中间数，并进行位置交换
        if (array[start] > array[end])   //保证左端较小
            swap(array, start, end);
        if (array[middle] > array[end])   //保证中间较小
            swap(array, end, middle);
        if (array[middle] > array[start])
            swap(array, middle, start);      //保证左端最小
        int index = start - 1;
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                index++;
                if (i > index) {
                    swap(array, i, index);
                }
            }
        }
        return index;
    }

    /**
     * 堆排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static int[] heapSort(int[] array) {
        if (array.length < 1) return array;
        //构建一个大根堆
        buildMaxHeap(array);
        //按照堆的性质，依次对堆中每个节点进行堆化
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }

        return array;
    }

    /**
     * 构建大根堆：本质上是向完全二叉树中插入节点的过程
     *
     * @param array 未排好序的
     */
    private static void buildMaxHeap(int[] array) {

        int half = (array.length - 1) / 2;

        //依次对每个非叶子节点进行堆化
        for (int i = half; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    /**
     * 堆化：删除最大节点后，对堆进行调整
     *
     * @param array    未排好序的
     * @param heapSize 堆的大小，即array.length
     * @param index    某个节点的索引，可以看作某个父节点
     */
    private static void heapify(int[] array, int heapSize, int index) {
        //该节点左孩子的索引
        int left = index * 2 + 1;
        //该节点右孩子的索引
        int right = index * 2 + 2;
        //确定此时最大索引值
        int maxIndex = index;
        //如果左子树大于父节点，则最大索引为左子树的索引
        if (left < heapSize && array[left] > array[index]) {
            maxIndex = left;
        }
        //如果右子树大于父节点，则最大索引为右子树的索引
        if (right < heapSize && array[right] > array[maxIndex]) {
            maxIndex = right;
        }
        //如果父节点不是最大值，则将父节点和最大值进行交换
        if (index != maxIndex) {
            swap(array, index, maxIndex);
            //递归调用
            heapify(array, heapSize, maxIndex);
        }
    }

    /**
     * 计数排序
     *
     * @param array 未排好序
     * @return 排好序的
     */
    private static int[] countingSort(int[] array) {
        if (array.length == 0) return array;

        //找到最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        System.out.println("max: " + max + ", min: " + min);

        //bias记录最小值和索引0之间的距离
        int bias = 0 - min;

        //定义
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        System.out.println("bucket before: " + Arrays.toString(bucket));
        System.out.println("Array  before: " + Arrays.toString(array));
        //计数用的数组，数值只要出现一次就加一
        //bias
        for (int i : array) {
            bucket[i + bias]++;
        }
        System.out.println("bucket after : " + Arrays.toString(bucket));
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }

    /**
     * 桶排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static ArrayList<Integer> bucketSort(int[] array) {

        //选择最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        //桶数
        int bucketNum = (max - min) / array.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        //一个桶这里定义为一个ArrayList<Integer>
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i : array) {
            int num = (i - min) / (array.length);
            bucketArr.get(num).add(i);
        }

        //对每个桶进行排序
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for (ArrayList<Integer> i : bucketArr) {
            Collections.sort(i);
            resultList.addAll(i);
        }

        //System.out.println(bucketArr.toString());
        //[[3, 8, 9, 10, 15, 16, 17], [19, 22, 27, 29, 32, 33], [39, 40, 49]]

        //将桶合并起来

        return resultList;
    }

    /**
     * 基数排序
     *
     * @param array 未排序的
     * @return 排好序的
     */
    private static int[] radixSort(int[] array) {

        int max = Integer.MIN_VALUE;
        for (int i : array) {
            max = Math.max(max, i);
        }

        int n = 1;//代表位数对应的数：1,10,100...
        int k = 0;//保存每一位排序后的结果用于下一位的排序输入
        int length = array.length;
        int[][] bucket = new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[length];//用于保存每个桶里有多少个数字
        while (n < max) {
            for (int num : array) //将数组array里的每个数字放在相应的桶里
            {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            for (int i = 0; i < length; i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;//将桶里计数器置0，用于下一次位排序
            }
            n *= 10;
            k = 0;//将k置0，用于下一轮保存位排序结果
        }
        return array;
    }

    public static void main(String[] args) {
        int[] myArray = {22, 3, 40, 8, 27, 16, 33, 19, 39, 10, 49, 32, 29, 9, 15, 17};
        //bubbleSort
        //int[] resultArr = bubbleSort(myArray);

        //selectionSort
        //int[] resultArr = selectionSort(myArray);

        //insertSort
        //int[] resultArr = insertSort(myArray);

        //shellSort
        //int[] resultArr = shellSort(myArray);

        //mergeSort
        //int[] resultArr = mergeSort(myArray);

        //quickSort
        int[] resultArr = quickSort(myArray, 0, myArray.length - 1);

        //heapSort
        //int[] resultArr = heapSort(myArray);

        //countingSort
        //int[] resultArr = countingSort(myArray);

        //bucketSort
        //ArrayList<Integer> resultArr = bucketSort(myArray);
        //for (int i : resultArr) {
        //    System.out.print(i + " ");
        //}

        //radixSort
        //int[] resultArr = radixSort(myArray);
        System.out.println(Arrays.toString(resultArr));
    }

}

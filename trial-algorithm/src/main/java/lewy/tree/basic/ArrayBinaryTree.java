package lewy.tree.basic;

/**
 * 树的顺序存储
 *
 * 顺序存储是指利用用长度为(2^i-1)数组存储深度为i的二叉树，
 * 这种存储方法会造成一定的空间浪费。
 */
public class ArrayBinaryTree<T> {
    //使用数组来记录该树的所有子节点
    private Object[] datas;
    private int DEFAULT_DEEP = 8;
    //保存该树的深度
    private int deep;
    private int arraySize;

    //以默认深度来创建二叉树
    public ArrayBinaryTree() {
        this.deep = DEFAULT_DEEP;
        this.arraySize = (int) Math.pow(2, deep) - 1;
        datas = new Object[arraySize];
    }

    //以指定深度来创建二叉树
    public ArrayBinaryTree(int deep) {
        this.deep = deep;
        this.arraySize = (int) Math.pow(2, deep) - 1;
        datas = new Object[arraySize];
    }

    //以指定深度、指定根节点来创建二叉树
    public ArrayBinaryTree(int deep, T data) {
        this.deep = deep;
        this.arraySize = (int) Math.pow(2, deep) - 1;
        datas = new Object[arraySize];
        datas[0] = data;
    }

    /**
     * 为指定节点添加子节点
     *
     * @param index 需要添加子节点的父节点的索引
     * @param data  新子节点的数据
     * @param left  是否为左节点
     */
    private void add(int index, T data, boolean left) {
        if (datas[index] == null) {
            throw new RuntimeException(index + "处节点为空，无法添加子节点！");
        }
        if (2 * index + 1 >= arraySize) {
            throw new RuntimeException("树底层的数组已满，树越界异常！");
        }
        //添加左子节点
        if (left) {
            datas[2 * index + 1] = data;
        } else {
            datas[2 * index + 2] = data;
        }
    }

    //判断二叉树是否为空
    public boolean empty() {
        //根据根元素来判断二叉树是否为空
        return datas[0] == null;
    }

    //返回根节点
    public T root() {
        return (T) datas[0];
    }

    //返回指定节点（非根节点）的父节点
    public T parent(int index) {
        return (T) datas[(index - 1) / 2];
    }

    //返回指定节点（非叶子节点）的左子节点
    public T left(int index) {
        if (2 * index + 1 >= arraySize) {
            throw new RuntimeException("该子节点为叶子节点，无子节点");
        }
        return (T) datas[index * 2 + 1];
    }

    //返回指定节点的右子节点
    public T right(int index) {
        if (2 * index + 1 >= arraySize) {
            throw new RuntimeException("该子节点为叶子节点，无子节点");
        }
        return (T) datas[index * 2 + 2];
    }

    //返回二叉树的深度
    public int deep(int index) {
        return deep;
    }

    //返回指定节点的位置
    public int pos(T data) {
        //该循环实际上就是按广度遍历来搜索每个节点
        for (int i = 0; i < arraySize; i++) {
            if (datas[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return java.util.Arrays.toString(datas);
    }

    public static void main(String[] args) {
        ArrayBinaryTree<String> binTree = new ArrayBinaryTree<String>(4, "根");
        binTree.add(0, "第二层右子节点", false);
        binTree.add(2, "第三层右子节点", false);
        binTree.add(6, "第四层右子节点", false);
        System.out.println(binTree);
    }
}
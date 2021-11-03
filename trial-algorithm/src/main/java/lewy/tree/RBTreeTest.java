package lewy.tree;

/**
 * 红黑树测试类
 */
public class RBTreeTest {

    private static final int[] a = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    private static final boolean mDebugInsert = false;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = false;    // "删除"动作的检测开关(false，关闭；true，打开)

    public static void main(String[] args) {
        int i, ilen = a.length;
        RBTree<Integer> tree=new RBTree<Integer>();

        System.out.println("== 原始数据: ");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.println("\n");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // 设置mDebugInsert=true,测试"添加函数"
            if (mDebugInsert) {
                System.out.printf("== 添加节点: %d\n", a[i]);
                System.out.println("== 树的详细信息: \n");
                tree.print();
                System.out.println("\n");
            }
        }

        System.out.println("== 前序遍历: ");
        tree.preOrder();

        System.out.println("\n== 中序遍历: ");
        tree.inOrder();

        System.out.println("\n== 后序遍历: ");
        tree.postOrder();
        System.out.println("\n");

        System.out.printf("== 最小值: %s\n", tree.minimum());
        System.out.printf("== 最大值: %s\n", tree.maximum());
        System.out.println("== 树的详细信息: \n");
        tree.print();
        System.out.println("\n");

        // 设置mDebugDelete=true,测试"删除函数"
        if (mDebugDelete) {
            for(i=0; i<ilen; i++)
            {
                tree.remove(a[i]);

                System.out.printf("== 删除节点: %d\n", a[i]);
                System.out.println("== 树的详细信息: \n");
                tree.print();
                System.out.println("\n");
            }
        }

        // 销毁二叉树
        tree.clear();
    }
}
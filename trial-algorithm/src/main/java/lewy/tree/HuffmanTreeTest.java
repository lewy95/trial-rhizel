package lewy.tree;

public class HuffmanTreeTest {

    private static final int[] a = {5, 6, 8, 7, 15};

    public static void main(String[] args) {
        int i;
        HuffmanTree tree;

        System.out.print("== 添加数组: ");
        for (i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");

        // 创建数组a对应的Huffman树
        tree = new HuffmanTree(a);

        System.out.print("\n== 前序遍历: ");
        tree.preOrder();

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();

        System.out.print("\n== 后序遍历: ");
        tree.postOrder();
        System.out.println();

        System.out.println("== 树的详细信息: ");
        tree.print();

        // 销毁二叉树
        tree.destroy();
    }

}

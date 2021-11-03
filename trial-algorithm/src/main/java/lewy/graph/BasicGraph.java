package lewy.graph;

/**
 * 简单的图的实现
 */
public class BasicGraph {

    private final int MAX_VERTEX = 10;// 最多20个顶点
    private Vertex[] vertex;// 顶点数组
    private int[][] adjacency;// 邻接矩阵
    private int numOfVertex;// 当前图中顶点的数量

    public BasicGraph() {// 构造器
        vertex = new Vertex[MAX_VERTEX];
        adjacency = new int[MAX_VERTEX][MAX_VERTEX];
        numOfVertex = 0;
        // 将邻接矩阵初始化
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++)
                adjacency[i][j] = 0;
        }
    }

    // 添加顶点
    public void addVertex(char v) {
        vertex[numOfVertex++] = new Vertex(v);
    }

    // 无向图 添加边
    public void addEdge(int start, int end) {
        adjacency[start][end] = 1;
        adjacency[end][start] = 1;
    }

    // 有向图 添加边
    public void addEdgeArc(int start, int end) {
        adjacency[start][end] = -1;
    }

    // 打印某个顶点
    private void printVertex(int index) {
        System.err.print(vertex[index].label);
    }

    // 打印邻接矩阵
    private void show() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                if (j == MAX_VERTEX - 1)
                    System.out.println(adjacency[i][j] + "  ");
                else
                    System.out.print(adjacency[i][j] + "  ");
            }
        }
    }

    /**
     * 找到与某一顶点邻接而未被访问的顶点,如何做？
     * 在邻接矩阵中，找到指定顶点所在的行，从第一列开始向后寻找值为1的列，列号是邻接顶点的号码，检查此顶点是否访问过。
     * 如果该行没有值为1而又未访问过的点，则此顶点的邻接点都访问过了。
     */
    private int getUnVisitedVertex(int index) {
        for (int i = 0; i < numOfVertex; i++)
            if (adjacency[index][i] == 1 && !vertex[i].wasVisited)
                return i;
        return -1;
    }

    /**
     * 图的深度优先遍历，用栈
     */
    public void dfs() {
        vertex[0].wasVisited = true;// 从头开始访问
        printVertex(0);
        Stack stack = new Stack();
        stack.push(0);

        /*
         * 1. 用peek()方法获取栈顶的顶点
         * 2. 试图找到这个顶点的未访问过的邻接点
         * 3. 如果没有找到这样的顶点，出栈
         * 4. 如果找到，访问之，入栈
         */
        while (!stack.isEmpty()) {

            int index = getUnVisitedVertex(stack.peek());
            if (index == -1)// 没有这个顶点
                stack.pop();
            else {
                vertex[index].wasVisited = true;
                printVertex(index);
                stack.push(index);
            }
        }
        // 栈为空，遍历结束，标记位重新初始化
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    /**
     * 图的广度优先遍历，用队列
     */
    private void bfs() {
        vertex[0].wasVisited = true;
        printVertex(0);
        Queue queue = new Queue();
        queue.insert(0);

        int v2;
        while (!queue.isEmpty()) { // 直到队列为空
            int v1 = queue.remove();
            // 直到点v1没有未访问过的邻接点
            while ((v2 = getUnVisitedVertex(v1)) != -1) {
                // 取到未访问过的点，访问之
                vertex[v2].wasVisited = true;
                printVertex(v2);
                queue.insert(v2);
            }
        }
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    // 最小生成树 minimum spanning tree
    private void mst() {
        vertex[0].wasVisited = true;
        Stack stack = new Stack();
        stack.push(0);
        while (!stack.isEmpty()) {
            int currentVertex = stack.peek();
            int v = getUnVisitedVertex(currentVertex);
            if (v == -1)
                stack.pop();
            else {
                vertex[v].wasVisited = true;
                stack.push(v);
                //当前顶点与下一个未访问过的邻接点
                printVertex(currentVertex);
                printVertex(v);
                System.out.print("  ");
            }
        }
        for (int i = 0; i < numOfVertex; i++)
            vertex[i].wasVisited = false;
    }

    public static void main(String[] args) {
        BasicGraph graph = new BasicGraph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.show();
        //graph.dfs();
        //graph.bfs();
        graph.mst();
    }
}


/**
 * 顶点类
 */
class Vertex {
    char label; // 如A，B,C
    boolean wasVisited; // 标识是否访问过此顶点

    public Vertex(char vertex) {
        this.label = vertex;
        wasVisited = false;
    }
}

/**
 * 自定栈类
 */
class Stack {
    final int MAX_SIZE = 10;
    int stack[];
    int top;

    public Stack() {
        stack = new int[MAX_SIZE];
        top = -1;
    }

    public void push(int idata) {
        stack[++top] = idata;
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

/**
 * 自定义队列类
 */
class Queue {
    final int SIZE = 10;
    int[] qarray;
    int front;
    int rear;

    public Queue() {
        qarray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    // 在队尾追加
    public void insert(int key) {
        if (rear == SIZE - 1)
            rear = -1;
        qarray[++rear] = key;
    }

    // 队头删除数据
    public int remove() {
        int temp = qarray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return rear + 1 == front || front + SIZE - 1 == rear;
    }
}


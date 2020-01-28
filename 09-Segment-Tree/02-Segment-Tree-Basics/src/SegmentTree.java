public class SegmentTree<E> {

    // 用于存储线段树的数组
    private E[] tree;
    private E[] data;
    private Merger merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间为[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid+1, r);
        tree[treeIndex] = (E) merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引为index的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引为index的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}

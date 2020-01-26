public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax() {
        E ret = findMax();

        // 先堆中最后一个元素和根元素交换，也即数组中第0个元素和最后一个元素交换位置
        data.swap(0, data.getSize() - 1);
        // 删除最后一个元素（最大元素）
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 下沉操作
    private void siftDown(int k) {
        // 当前元素还有左孩子时候
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 当前元素右孩子也存在的时候  且 右孩子的值  大于 左孩子的值的时候，  如果右孩子的值  小于 左孩子的值
            // j没有被rightChild(k)赋值， j记录的还是左右孩子中最大元素的索引
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                // data[j]记录的是leftChild和rightChild中的最大值
                j = rightChild(k);
            }
            // 如果当前节点的值 >= 左右孩子中的最大值  说明满足大根堆性质 break
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            // 如果当前节点的值 < 左右孩子中的最大值  说明不满足大根堆性质
            data.swap(k, j);

            //下一轮siftDown操作
            k = j;
        }
    }
}

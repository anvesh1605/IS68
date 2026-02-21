package DSA_Assignment_01;

public class ques4 {
    public static void main(String[] args) {
            int[] stockPrices = {100, 200, 150, 300, 250};
            SegmentTree segmentTree = new SegmentTree(stockPrices);
    
            segmentTree.update(2, 380);
    
            int maxPrice = segmentTree.queryMax(1, 3);
            System.out.println("Maximum stock price in the range [1, 3]: " + maxPrice);
    }

    static class SegmentTree{
        int[] segTree;
        int n;

        public SegmentTree(int[] arr){
            this.n = arr.length;
            segTree = new int[4*n];
            buildSegTree(segTree, arr, 0, 0, n-1);
        }

        public void buildSegTree(int[] segTree, int[] arr, int idx, int l, int r){
            if (l == r){
                segTree[idx] = arr[r];
                return;
            }
            int mid = (l + r)/ 2;
            buildSegTree(segTree, arr, 2*idx+1, l, mid);
            buildSegTree(segTree, arr, 2*idx+2, mid+1, r);
            segTree[idx] = Math.max(segTree[2*idx+1], segTree[2*idx+2]);
        }

        public void update(int idx, int price){
            updateQuery(idx, price, segTree, 0, 0, n-1);
        }

        public void updateQuery(int idx, int value, int[] segTree, int i, int l, int r){
            if (l == r){
                segTree[i] = value;
                return;
            }

            int mid = (l + r) / 2;
            if (idx <= mid){
                updateQuery(idx, value, segTree, 2*i+1, l, mid);
            }else{
                updateQuery(idx, value, segTree, 2*i+2, mid+1, r);
            }
            segTree[i] = Math.max(segTree[2*i+1], segTree[2*i+2]);
        }

        public int queryMax(int l, int r){
            return RMQ(segTree, l, r, 0, 0, n-1);
        }

        public int RMQ(int[] segTree, int qs, int qe, int i, int l, int r){
            // 3 cases : in, out, overlap 
            if (l>=qs && r<=qe) return segTree[i]; //in
            if (l>qe || r<qs) return Integer.MIN_VALUE; //out

            int mid = (l+ r) / 2;
            int leftMax = RMQ(segTree, qs, qe, 2*i+1, l, mid);
            int rightMax = RMQ(segTree, qs, qe, 2*i+2, mid+1, r);
            return Math.max(leftMax, rightMax);
        }

    }

}

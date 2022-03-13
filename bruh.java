Class KeyValue{
	public String Key;
	public String Value;
}


HashMap<String, String> KeyValue = new HashMap<>()


class Btree {
    public BTreeNode root;
    public int t; 
 
    Btree(int t) {
        this.root = null;
        this.t = t;
    }
 
    public void traverse() {
        if (this.root != null)
            this.root.traverse();
        System.out.println();
    }
 
    public BTreeNode search(int k) {
        if (this.root == null)
            return null;
        else
            return this.root.search(k);
    }
}
 

class BTreeNode {
    int[] keys; 
    int t; 
    BTreeNode[] C;
    int n;
    boolean leaf;
 
    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.C = new BTreeNode[2 * t];
        this.n = 0;
    }
 
    public void traverse() {
 		nt i = 0;
        for (i = 0; i < this.n; i++) {
            if (this.leaf == false) {
                C[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }
 		if (leaf == false)
            C[i].traverse();
    }
 
    BTreeNode search(int k) { 
        int i = 0;
        while (i < n && k > keys[i])
            i++;
        if (keys[i] == k)
            return this;
        if (leaf == true)
            return null;
        return C[i].search(k);
 
    }
}
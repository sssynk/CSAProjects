import java.util.ArrayList;

public class Tree {
    public int nodedata;
    public Tree left;
    public Tree right;

    public Tree() {
        this.nodedata = 0;
        left = null;
        right = null;
    }

    public Tree(int nodedata) {
        this.nodedata = nodedata;
        left = null;
        right = null;
    }

    public void add(int newnodeint) {
        if (newnodeint < this.nodedata) {
            if (left == null) {
                left = new Tree(newnodeint);
            } else {
                left.add(newnodeint);
            }
        } else {
            if (right == null) {
                right = new Tree(newnodeint);
            } else {
                right.add(newnodeint);
            }
        }
    }

    public void find(int findint) {
        if (findint == this.nodedata) {
            System.out.println("Found " + findint);
        } else {
        if (findint < this.nodedata) {
            if (left != null) {
                left.find(findint);
            } else {
                System.out.println("Not found " + findint);
            }
        } else {
            if (right != null) {
                right.find(findint);
            } else {
                System.out.println("Not found " + findint);
            }
        }
    }
    }

    public ArrayList<Object> get() {
        
        ArrayList<Object> list = new ArrayList<Object>();
        list.add("arr:"+nodedata);
        if (left != null) {
            list.add(left.get());
        } else {
            list.add("eld");
        }
        if (right != null) {
            list.add(right.get());
        } else {
            list.add("erd");
        }
        return list;
    }

    public void printTreeHorizontal() {
        ArrayList<Object> got = get();
        System.out.println("top - - - "+got.get(0));
        //got.remove(0);
        recursiveDraw(got);
        
        //System.out.println(got.get(1));
        
    }
    

    private void recursiveDraw(ArrayList<Object> list) {
        ArrayList<Object> ret = new ArrayList<Object>();
        //if (((String) list.get(0)).contains("arr:")) {
        //System.out.println(list.get(0));
        //}
        System.out.println("input = "+list.toString());
        ret.add("arr:0");
        ArrayList<Object> left;
        ArrayList<Object> right;

        if (!list.get(1).toString().equals("eld")) {
            left = ((ArrayList<Object>) list.get(1));
            System.out.print(left.get(0));
            ret.add(left);
        } else if (list.get(2).toString().equals("erd")) {
            return;
        }
        System.out.print(" - - - ");
        if (!list.get(2).toString().equals("erd")) {
            right = ((ArrayList<Object>) list.get(2));
            System.out.print(right.get(0));
            ret.add(right);
        } else if (list.get(1).toString().equals("eld")) {
            return;
        }
        System.out.println();
        System.out.println("output = "+ret.toString());
        recursiveDraw(ret);
    }




}

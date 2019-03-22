import com.sun.javafx.binding.SelectBinding;

import java.util.Iterator;

public class BinaryTree<E> {

    protected E val; // value associated with node
    protected BinaryTree<E> parent; // parent of node
    protected BinaryTree<E> left, right; // children of node
    public BinaryTree()
    // post: constructor that generates an empty node
    {
        val = null;
        parent = null;
        left = right = this;
    }

    public BinaryTree(E value)
    // post: returns a tree referencing value and two empty subtrees
    {
        val = value;
        right = left = new BinaryTree<E>();
        setLeft(left);
        setRight(right);
    }

    public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right)
    // post: returns a tree referencing value and two subtrees
    {
        val = value;
        if (left == null) {
            left = new BinaryTree<E>();
        }
        setLeft(left);
        if (right == null) {
            right = new BinaryTree<E>();
        }
        setRight(right);
    }

    public BinaryTree<E> left()
    // post: returns reference to (possibly empty) left subtree
    // post: returns reference to (possibly empty) left subtree
    {
        return left;
    }

    public BinaryTree<E> parent(){return parent;}
    // post: returns reference to parent node, or null

    public void setLeft(BinaryTree<E> newLeft)
    // post: sets left subtree to newLeft
    // re-parents newLeft if not null
    {
        if (isEmpty()) return;
        if (left != null && left.parent() == this) left.setParent(null);
        left = newLeft;
        left.setParent(this);
    }

    public void setRight(BinaryTree<E> newRight)
    // post: sets left subtree to newLeft
    // re-parents newLeft if not null
    {
        if (isEmpty()) return;
        if (right != null && right.parent() == this) right.setParent(null);
        right = newRight;
        right.setParent(this);
    }

    protected void setParent(BinaryTree<E> newParent)
    // post: re-parents this node to parent reference, or null
    {
        if (!isEmpty()) {
            parent = newParent;
        }
    }

    public String inorder (BinaryTree raiz){
        return evaluarInorder(raiz);
    }

    public String evaluarInorder(BinaryTree nodo){
        String t = "";
        if (nodo.val==null){
            return "";
        }
        t+= evaluarInorder(nodo.left);
        t+= nodo.val.toString()+"\n";
        t+=evaluarInorder(nodo.right);
        return t;
    }
    /**public Iterator<E> iterator(){

    }**/
    // post: returns an in-order iterator of the elements

    /**public boolean isLeftChild(){

    }**/
    // post: returns true if this is a left child of parent

    public E value()
    // post: returns value associated with this node
    {
        return val;
    }

    public void setValue(E value)
    // post: sets the value associated with this node
    {
        val = value;
    }
    public boolean isEmpty(){
        return val==null&&left==null && right==null;
    }

    public String buscar(String a){
        String str = "";
        BinaryTree arbolito = this;
        if (arbolito.val == null){
            return str = "*"+a+"*";
        }
        if(((Association)arbolito.val).getKey().toString().equals(a)){
            return (((Association) arbolito.val).getValue()).toString();
        }
        if((((Association) arbolito.val).getKey().toString().compareToIgnoreCase(a)>0)){
            //usa left
            arbolito = arbolito.left;
            str = arbolito.buscar(a);
        }else if((((Association) arbolito.val).getKey().toString().compareToIgnoreCase(a)<0)){
            //usa right
            arbolito = arbolito.right;
            str = arbolito.buscar(a);
        }
        return str;
    }
}
package Tree;

public class Tree2 {

    public static void order(Node var) {
        if(var == null) {
            return;
        }
        System.out.println(var.getData());
        order(var.getLeft());
        order(var.getRight());

    }
}

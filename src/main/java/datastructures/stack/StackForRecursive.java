package datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: StackForRecursive
 * @author: hw83770
 * <p>
 * 用栈 模拟所有递归 用 BST 前序, 中序， 后序 遍历做例子
 * <p>
 * 没有 command 包装的 中序， 后序 代码结构很不一样
 */

public class StackForRecursive {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Command {
        String command;
        TreeNode node;

        Command(String command, TreeNode node) {
            this.command = command;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        StackForRecursive stackForRecursive = new StackForRecursive();
        TreeNode node1 = stackForRecursive.genTree();
        stackForRecursive.preOrder(node1);
        stackForRecursive.preOrderRecursive(node1);

    }

    private TreeNode genTree() {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node4, null);
        TreeNode node2 = new TreeNode(2, node5, node6);

        return new TreeNode(1, node2, node3);
    }

    private void preOrderRecursive(TreeNode crr) {
        if (crr == null)
            return;

        System.out.println(crr.val);
        preOrder(crr.left);
        preOrder(crr.right);
    }

    private List<Integer> preOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<Integer>();
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("visit", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if ("visit".equals(command.command) && command.node != null) {
                stack.push(new Command("visit", command.node.right));
                stack.push(new Command("visit", command.node.left));
                stack.push(new Command("print", command.node));
            }
            if ("print".equals(command.command) && command.node != null) {
                System.out.println(command.node.val);
                res.add(command.node.val);
            }
        }
        return res;
    }

    public List<Integer> inOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<Integer>();
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("visit", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if ("visit".equals(command.command) && command.node != null) {
                stack.push(new Command("visit", command.node.right));
                stack.push(new Command("print", command.node));
                stack.push(new Command("visit", command.node.left));
            }
            if ("print".equals(command.command) && command.node != null) {
                System.out.println(command.node.val);
                res.add(command.node.val);
            }
        }
        return res;
    }

    public List<Integer> postOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<Integer>();
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("visit", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if ("visit".equals(command.command) && command.node != null) {
                stack.push(new Command("print", command.node));
                stack.push(new Command("visit", command.node.right));
                stack.push(new Command("visit", command.node.left));
            }
            if ("print".equals(command.command) && command.node != null) {
                System.out.println(command.node.val);
                res.add(command.node.val);
            }
        }
        return res;
    }

}


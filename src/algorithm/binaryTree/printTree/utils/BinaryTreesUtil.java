package binaryTree.printTree.utils;

import binaryTree.printTree.interfaces.BinaryTreeInfo;
import binaryTree.printTree.printer.InOrderPrinter;
import binaryTree.printTree.printer.LevelOrderPrinter;
import binaryTree.printTree.printer.Printer;

/**
 * 二叉树打印工具类
 *
 * @author xuyh
 * @date 2019/5/10
 */
public final class BinaryTreesUtil {
    private BinaryTreesUtil() {
    }

    public static void print(BinaryTreeInfo tree) {
        print(tree, null);
    }

    public static void println(BinaryTreeInfo tree) {
        println(tree, null);
    }

    public static void print(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return;
        }
        printer(tree, style).print();
    }

    public static void println(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return;
        }
        printer(tree, style).println();
    }

    public static String printString(BinaryTreeInfo tree) {
        return printString(tree, null);
    }

    public static String printString(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return null;
        }
        return printer(tree, style).printString();
    }

    private static Printer printer(BinaryTreeInfo tree, PrintStyle style) {
        if(style==null){
            return new LevelOrderPrinter(tree);
        }
        if (style == PrintStyle.IN_ORDER) {
            return new InOrderPrinter(tree);
        } else if (style == PrintStyle.LEVEL_ORDER) {
            return new LevelOrderPrinter(tree);
        } else {
            throw new IllegalArgumentException("unsupported print style");
        }
    }

    public enum PrintStyle {
        LEVEL_ORDER, IN_ORDER
    }
}

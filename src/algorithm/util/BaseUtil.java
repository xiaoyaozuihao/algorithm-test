package util;

import linkedList.DoubleNode;
import binaryTree.printTree.TreeNode;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyh
 * @date 2019/4/5
 */
public class BaseUtil {
    /**
     * 对数器的实现,用于校验自己写的算法是否正确
        0,有一个想要测试的方法a
        1,实现一个绝对正确但复杂度不好的方法b，该方法也可能不正确，但通过比对能够进行方便的找出错在哪
        2,实现一个随机样本发生器
        3,实现比对的方法
        4,把a和b方法比对很多次来验证a方法是否正确
        5,如果有一个样本使得测试出错，打印该样本分析是哪个方法出错
        6,当样本数量很多时比对依然正确，基本可以确定方法a已经正确
    */

    /**
     * 1.先定义一个绝对正确的但可能复杂度不好的方法，这里选库函数的排序算法
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 2.样本生成器,用于生成随机数组,数组的大小随机，数组的每个位置的值随机
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //Math.random();//生成[0-1)区间的值，左闭右开
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];//生成[0,maxSize]范围的整数
        for (int i = 0; i < arr.length; i++) {
            //生成每个位置的数，范围是[-maxValue,maxValue]
            arr[i] = (int) (Math.random() * (2 * maxValue + 1)) - maxValue;
        }
        return arr;
    }

    /**
     * 3.定义比较两个数组是否相等的方法
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
//        Arrays.equals(arr1,arr2);
        if (arr1 == arr2) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static List<String> getDiff(List<String> l1,List<String> l2){
        List<String> diff = new ArrayList<>();
        List<String> maxList = l1;
        List<String> minList = l2;
        if(l2.size()>l1.size()) {
            maxList = l2;
            minList = l1;
        }
        Map<String,Integer> map = new HashMap<>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            if(map.get(string)!=null) {
                map.put(string, map.get(string)+1);
                continue;
            }
            diff.add(string);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet()) {
            if(entry.getValue()==1) {
                diff.add(entry.getKey());
            }
        }
        return diff;
    }

    //不考虑元素的顺序
    public static boolean isEqual(List<Integer> l1, List<Integer> l2) {
        if (l1 == l2) {
            return true;
        }
        if (l1 == null || l2 == null) {
            return false;
        }
        if (l1.size() != l2.size()) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : l1) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        for (Integer i : l2) {
            if (!map.containsKey(i)) {
                return false;
            }
            map.put(i, map.get(i) - 1);
            if (map.get(i) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 实现拷贝数组的功能，方便将一个数组样本复制多份
     */
    public static <T extends Number> T[] copyArray(T[] arr) {
        if (arr == null) {
            return null;
        }
        T[] res = (T[]) Array.newInstance(arr.getClass(), arr.length);
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 定义打印数组的方法
     */
    public static <T extends Number> void printArray(T[] arr) {
        if (arr == null) {
            return;
        }
        if (arr.length == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i] + ",");
            } else {
                sb.append(arr[i] + "]");
            }
        }
        System.out.println(sb.toString());
    }

    public static void printArray(int[] arr) {
        printArray(toObject(arr));
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // "\t"是制表符
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void swap(int[] arr,int i,int j){
        int temp= arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T extends Number> void swap(T[] arr, int i, int j) {
        //定义变量交换
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        //异或运算实现交换
//        arr[i] ^= arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] ^= arr[j];
    }

    public static void printLinkedList(TreeNode head) {
        System.out.print("Linked list:");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("DoubleLinked list:正序 ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("|逆序 ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.pre;
        }
        System.out.println();
    }

    public static Integer[] toObject(int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new Integer[0];
        } else {
            Integer[] result = new Integer[array.length];
            for(int i = 0; i < array.length; ++i) {
                result[i] = array[i];
            }
            return result;
        }
    }

    public static void testTemplate(String className, String methodName) {
        //定义测试次数
        int times = 10000;
        int maxSize = 100;
        int maxValue = 30;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            invokeMethod(className, methodName, arr1);
            comparator(arr2);
            if (!isEqual(arr2, arr1)) {
                succeed = false;
                printArray(arr);
                break;
            }
        }
        System.out.println(succeed ? "nice" : "oh no!");
//        int[] arr=generateRandomArray(maxSize,maxValue);
//        printArray(arr);
//        invokeMethod(className,methodName, arr);
//        printArray(arr);
    }

    private static void invokeMethod(String className, String methodName, int[] arr) {
        try {
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod(methodName, int[].class);
            method.invoke(obj, arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int max=8;
        int min=4;
        int floor = (int)(Math.random() * (max - min + 1) + min);
        System.out.println(floor);
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int maxValue=(int)(Math.random()*21);
            int sample = (int) (Math.random() * (2 * maxValue + 1)) - maxValue;
            map.put(maxValue,sample);
        }
        System.out.println(map);
    }
}

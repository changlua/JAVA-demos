package com.changlu.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Description: 哈夫曼树
 * @Author: changlu
 * @Date: 8:09 PM
 */
//哈夫曼树节点
class TreeNode {
    public Character ch;
    public int val;
    public int freq;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){}
    public TreeNode(Character ch, int val, int freq, TreeNode left, TreeNode right) {
        this.ch = ch;
        this.val = val;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class Huffman {

    public static void main(String[] args) {
        String s = "aaabbbeeedacfwwwwddd";
        System.out.println("编码前：" + s);
        //编码API
        Object[] encodeRes = encode(s);
        String encodeStr = (String) encodeRes[0];
        Map<Character, String> encodeMap = (Map<Character, String>) encodeRes[1];
        System.out.println("编码表：");
        for (Map.Entry<Character, String> e : encodeMap.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
        System.out.println("编码后：" + encodeStr);
        //解码API
        String decodeStr = decode(encodeStr, encodeMap);
        System.out.println("解码后:" + decodeStr);
    }

    /**
     * 解码
     * @param encodeStr 已编码字符串
     * @param encodeMap 编码map集合
     * @return
     */
    public static String decode (String encodeStr, Map<Character, String> encodeMap) {
        StringBuilder decodeStr = new StringBuilder();
        while (encodeStr.length() > 0) {
            //遍历所有编码map对应的value（也就是编码路径）
            for (Map.Entry<Character, String> entry : encodeMap.entrySet()) {
                String charEncode = entry.getValue();
                //匹配路径前缀
                if (encodeStr.startsWith(charEncode)) {
                    decodeStr.append(entry.getKey());
                    //删除该前缀
                    encodeStr = encodeStr.substring(charEncode.length());
                    break;
                }
            }
        }
        return decodeStr.toString();
    }

    //编码
    //0：编码后的字符串
    //1：对应哈弗曼树中字符的路径表示
    public static Object[] encode(String s) {
        Object[] res = new Object[2];
        //编码字符串
        //1、构建哈夫曼树
        TreeNode rootNode = constructTree(s);
        //2、根据哈夫曼树找到所有的路径并存储到map中
        Map<Character, String> encodeMap = new HashMap<>();
        findPath(rootNode, encodeMap, new StringBuilder());//存储所有字符、编码路径到map中
        //此时 字符:路径编码  已经再encodeMap中存储了，我们即可来进行编码
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(encodeMap.get(s.charAt(i)));
        }
        res[0] = sb.toString();
        res[1] = encodeMap;
        return res;
    }

    /**
     * 寻找到哈夫曼树中所有字符的路径，并存储到map中
     * @param root 哈夫曼树的根节点
     * @param encodeMap 编码map
     * @param path 存储路径
     */
    private static void findPath(TreeNode root, Map<Character, String> encodeMap, StringBuilder path) {
        //每个元素的终点就是n0节点
        if (root.left == null || root.right == null) {
            path.append(root.val);
            //为什么要从第1位开始呢？因为完全可以再少一位0，这样也能减少不少字符
            encodeMap.put(root.ch, path.substring(1));
            path.deleteCharAt(path.length() - 1);
            return;
        }
        path.append(root.val);
        //递归左右
        if (root.left != null) findPath(root.left, encodeMap, path);
        if (root.right != null) findPath(root.right, encodeMap, path);
        //删除所有最后一个字符
        path.deleteCharAt(path.length() - 1);
    }


    /**
     * 构造哈夫曼树
     * @param s
     * @return
     */
    private static TreeNode constructTree(String s) {
        //1、统计每个字符出现的权重
        Map<Character, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            cntMap.put(s.charAt(i), cntMap.getOrDefault(s.charAt(i), 1));
        }
        //2、将所有的字符构建成TreeNode节点存储到LinkedList中
        LinkedList<TreeNode> nodelist = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : cntMap.entrySet()) {
            Character ch = entry.getKey();//字符
            Integer freq = entry.getValue();//频率
            int val = 0;//节点值
            nodelist.add(new TreeNode(ch, val, freq, null, null));
        }
        //3、根据频率来进行排序
        Collections.sort(nodelist, (node1, node2)->node1.freq - node2.freq);
        //4、构建哈夫曼树
        //处理只有一个节点的情况
        if (nodelist.size() == 1) {
            //1个节点也要有父节点
            TreeNode treeNode = nodelist.get(0);
            return new TreeNode(null, 0, treeNode.freq, treeNode, null);
        }
        //开始构建哈夫曼树
        TreeNode root = null;
        while (nodelist.size() > 0) {
            //取出头部两个节点
            TreeNode t1 = nodelist.removeFirst();
            TreeNode t2 = nodelist.removeFirst();
            //左右子树节点来进行分别赋值0，1
            t1.val = 0;
            t2.val = 1;
            //若是当前节点中没有节点了
            if (nodelist.size() == 0) {
                root = new TreeNode(null, 0, t1.freq + t2.freq, t1, t2);
            }else {
                //首先构建成一个虚拟节点
                TreeNode combineNode = new TreeNode(null, 0, t1.freq + t2.freq, t1, t2);
                //将虚拟节点插入到链表中，同样需要维护其有序性
                //暴力法【实际这里可用二分来进行】
                if (combineNode.freq > nodelist.getLast().freq) {
                    nodelist.addLast(combineNode);
                }else {
                    //遍历找到权重>该合并节点的权重
                    for (int i = 0; i < nodelist.size(); i++) {
                        if (nodelist.get(i).freq >= combineNode.freq) {
                            nodelist.add(i, combineNode);
                            break;
                        }
                    }
                }
            }
        }
        return root;
    }

}

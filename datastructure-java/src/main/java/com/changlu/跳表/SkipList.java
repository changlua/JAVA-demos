package com.changlu.跳表;

import java.util.Random;
import java.util.Stack;

class SkipNode<T> {
    int key;
    T value;
    SkipNode right,down;//左右上下四个方向的指针

    public SkipNode (int key,T value) {
        this.key=key;
        this.value=value;
    }
}

public class SkipList<T> {

    SkipNode<T> headNode;//头节点，入口
    int highLevel;//目前最高的层数
    Random random;// 用于投掷硬币
    final int MAX_LEVEL = 32;//最大的层

    SkipList(){
        random=new Random();//随机数生成器
        headNode=new SkipNode<T>(Integer.MIN_VALUE,null);//头部结点
        highLevel=0;//高度
    }

    /**
     * 查找结点
     * @param key
     * @return
     */
    public SkipNode search(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if(team.key==key){
                return team;
            }else if(team.right == null){//右侧没有了，只能下降
                team = team.down;
            }else if(team.right.key > key) {//需要下降去寻找
                team=team.down;
            }else{//右侧比较小向右
                team=team.right;
            }
        }
        return null;
    }

    /**
     * 添加结点
     * @param node
     */
    public void add(SkipNode node){
        //获取到结点的key
        int key = node.key;
        //1、根据key查询到对应的跳表结点
        SkipNode findNode = search(key);
        //2、针对是否存在结点来做操作
        //2.1、如果存在这个key的节点
        if(findNode!=null){
            findNode.value = node.value;
            return;
        }
        //2.2、当前表中不存在该node时，就会去进行添加操作
        Stack<SkipNode> stack=new Stack<SkipNode>();//存储向下的节点，这些节点可能在右侧插入节点
        //重新去寻找位置
        SkipNode team = headNode;//查找待插入的节点   找到最底层的哪个节点。
        while (team!=null) {
            //进行查找操作
            if(team.right == null){//右侧没有了，只能下降
                stack.add(team); //将曾经向下的节点记录一下
                team = team.down;
            }else if(team.right.key > key){//需要下降去寻找
                stack.add(team); //将曾经向下的节点记录一下
                team = team.down;
            }else{ //向右
                team=team.right;
            }
        }
        //3、进行插入结点操作
        int level = 1;//当前层数，从第一层添加(第一层必须添加，先添加再判断)
        SkipNode downNode = null;//保持前驱节点(即down的指向，初始为null)
        while (!stack.isEmpty()) {
            //3.1、获取到待更新结点
            team=stack.pop();//抛出待插入的左侧节点
            SkipNode nodeTeam=new SkipNode(node.key, node.value);//节点需要重新创建
            nodeTeam.down = downNode;//处理竖方向
            downNode = nodeTeam;//标记新的节点下次使用
            //3.2、来进行插入结点操作
            if(team.right==null) {
                //右侧为null 说明插入在末尾
                team.right = nodeTeam;
            }
            //水平方向处理（右边有值情况）
            else {
                //右侧还有节点，插入在两者之间
                nodeTeam.right=team.right;
                team.right=nodeTeam;
            }
            //3、创建上级索引操作（根据一个随机值来进行判定是否要创建新的一级索引）
            //考虑是否需要向上
            if(level>MAX_LEVEL)//已经到达最高级的节点啦
                break;
            double num = random.nextDouble();//[0-1]随机数
            if(num > 0.5)//运气不好结束
                break;
            //运气好的话层级向上+1
            level++;
            //一旦出现新的层级时，首先会去创建对应层级的head结点，然后添加到栈中去
            if(level > highLevel){ //比当前最大高度要高但是依然在允许范围内 需要改变head节点
                highLevel = level;
                //需要创建一个新的节点
                SkipNode highHeadNode=new SkipNode(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                headNode = highHeadNode;//改变head
                stack.add(headNode);//下次抛出head
            }
        }
    }

    /**
     * 删除结点
     * @param key
     */
    public void delete(int key){//删除不需要考虑层数
        SkipNode team=headNode;
        while (team!=null) {
            if (team.right == null) {
                //右侧没有了，说明这一层找到，没有只能下降
                team=team.down;
            }else if(team.right.key==key){//找到节点，右侧即为待删除节点
                team.right=team.right.right;//删除右侧节点
                team=team.down;//向下继续查找删除
            }else if(team.right.key>key){//右侧已经不可能了，向下
                team=team.down;
            }else {
                //节点还在右侧
                team=team.right;
            }
        }
    }



    public void printList() {
        SkipNode teamNode=headNode;
        int index=1;
        SkipNode last=teamNode;
        while (last.down!=null){
            last=last.down;
        }
        while (teamNode!=null) {
            SkipNode enumNode=teamNode.right;
            SkipNode enumLast=last.right;
            System.out.printf("%-8s","head->");
            while (enumLast!=null&&enumNode!=null) {
                if(enumLast.key==enumNode.key){
                    System.out.printf("%-5s",enumLast.key+"->");
                    enumLast=enumLast.right;
                    enumNode=enumNode.right;
                }else{
                    enumLast=enumLast.right;
                    System.out.printf("%-5s","");
                }
            }
            teamNode=teamNode.down;
            index++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipList<Integer>list=new SkipList<Integer>();
        for(int i=1;i<20;i++){
            list.add(new SkipNode<Integer>(i,666));
        }
        list.printList();
        list.delete(4);
        list.delete(8);
        list.printList();
        SkipNode node = list.search(4);
        System.out.println(node.value);
    }
}
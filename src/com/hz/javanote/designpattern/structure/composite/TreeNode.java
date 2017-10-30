package com.hz.javanote.designpattern.structure.composite;

import java.util.Iterator;
import java.util.LinkedList;

public class TreeNode {  
    
    private String name;  
    private TreeNode parent;  
    private LinkedList<TreeNode> children = new LinkedList<>();  
      
    public TreeNode(String name){  
        this.name = name;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public TreeNode getParent() {  
        return parent;  
    }  
  
    public void setParent(TreeNode parent) {  
        this.parent = parent;  
    }  
      
    //添加孩子节点  
    public void add(TreeNode node){  
        children.add(node);  
    }  
      
    //删除孩子节点  
    public void remove(TreeNode node){  
        children.remove(node);  
    }  
      
    //取得孩子节点  
    public Iterator<TreeNode> getChildren(){  
        return children.iterator();  
    }  
}  
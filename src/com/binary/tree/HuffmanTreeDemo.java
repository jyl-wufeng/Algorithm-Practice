package com.binary.tree;

import java.util.*;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        //构建赫夫曼树
        HuffmanTree huffmanTree = new HuffmanTree("JetBrains is a cutting-edge software vendor specializing in the creation of intelligent developmentr");
       //生成字典表
        Map<Integer, String> integerStringMap = huffmanTree.dictionaryTable();
        //编码
        Byte[] encode = huffmanTree.encode("JetBrains is a cutting-edge software vendor specializing in the creation of intelligent developmentr");
        //解码
        String decode = huffmanTree.decode(encode);
        System.out.print("解码后的字符串为："+decode);
    }
}

//1000110
class HuffmanTree {
    HuuffmanNode top;
    Map<Integer, String> integerStringMap;

    /**
     * 统计字符串中各字符权值
     *
     * @param characterString 字符串
     * @return
     */
    public static List<HuuffmanNode> statisticsString(String characterString) {
        List<HuuffmanNode> list = new ArrayList<>();
        Map<Character, Integer> stringValue = new HashMap<>();
        for (int i = 0; i < characterString.length(); i++) {
            if (stringValue.get(characterString.charAt(i)) == null) {
                stringValue.put(characterString.charAt(i), 1);
            } else {
                stringValue.put(characterString.charAt(i), stringValue.get(characterString.charAt(i)) + 1);
            }
        }
        stringValue.forEach((c, i) -> {
            HuuffmanNode huuffmanNode = new HuuffmanNode(c, i);
            list.add(huuffmanNode);
        });
        return list;
    }

    /**
     * 创建一颗赫夫曼树
     *
     * @param list
     */
    public void createHuffmanTree(List<HuuffmanNode> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            HuuffmanNode leftNode = list.get(0);
            HuuffmanNode rightNode = list.get(1);
            HuuffmanNode parentNode = new HuuffmanNode(leftNode.value + rightNode.value);
            parentNode.leftNode = leftNode;
            parentNode.rightNode = rightNode;
            list.add(parentNode);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        this.top = list.get(0);
    }

    /**
     * 生成字典表
     *
     * @return 字典表
     */
    public Map<Integer, String> dictionaryTable() {
        integerStringMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        this.dictionaryTable(integerStringMap, stringBuilder, this.top.leftNode, "0");
        this.dictionaryTable(integerStringMap, stringBuilder, this.top.rightNode, "1");
        return integerStringMap;
    }

    private void dictionaryTable(Map<Integer, String> dictionaryTable, StringBuilder stringBuilder, HuuffmanNode huuffmanNode, String code) {
        StringBuilder newStringBuilder = new StringBuilder(stringBuilder).append(code);
        if (huuffmanNode != null) {
            //不为空则为叶子节点
            if (huuffmanNode.data != null) {
                dictionaryTable.put(huuffmanNode.data, newStringBuilder.toString());
            } else {
                dictionaryTable(dictionaryTable, newStringBuilder, huuffmanNode.leftNode, "0");
                dictionaryTable(dictionaryTable, newStringBuilder, huuffmanNode.rightNode, "1");
            }
        }
    }

    /**
     * 编码
     *
     * @param characterString 需要编码的字符串
     * @return 编码后的
     */
    public Byte[] encode(String characterString) {
        StringBuilder encodeString = new StringBuilder();
        byte[] bytes = characterString.getBytes();
        for (byte b : bytes) {
            encodeString.append(integerStringMap.get(Integer.valueOf(b)));
        }
        int length = (encodeString.length() + 7) / 8;
        Byte[] encodeByte = new Byte[length];
        int index = 0;
        for (int i = 0; i < encodeString.length(); i += 8) {
            if (i + 8 > encodeString.length()) {//最后不足八位只截取到最后
                encodeByte[index] = (byte) Integer.valueOf(encodeString.substring(i), 2).intValue();
            } else {
                encodeByte[index] = (byte) Integer.valueOf(encodeString.substring(i, i + 8), 2).intValue();
            }
            index++;
        }
        System.out.println("编码后的字符为："+encodeString.toString());
        return encodeByte;
    }

    /**
     * 解码
     * @param codes 编码后的字节
     * @return 解码后的字符串
     */
    public String decode(Byte[] codes) {
        StringBuilder stringValue = new StringBuilder();
        StringBuilder originalValue= new StringBuilder();
        Map<String, Integer> codeMap = new HashMap<>();
        integerStringMap.forEach((key, value) -> {
            codeMap.put(value, key);
        });
        int index = 0;
        for (byte b : codes) {
            index++;
            String s = Integer.toBinaryString(b);

            if (s.length() >= 8) {//超过8位截取
                stringValue.append(s.substring(s.length() - 8));
            } else if (index != codes.length) {//不足8位补0
                stringValue.append(String.format("%8s", s).replaceAll("\\s", "0"));
            } else {//最后一个不动
                stringValue.append(s);
            }
        }
        System.out.println("解码后的字符为："+stringValue);
        for(int i=0;i<stringValue.length();){
            boolean bl=true;
            int count=1;
            while (bl){
                Integer integer = codeMap.get(stringValue.substring(i, i + count));
                if(integer!=null){
                    originalValue.append((char) integer.intValue());
                    bl=false;
                    i+=count;
                }
                count++;
            }
        }
        return originalValue.toString();
    }


    public void createHuffmanTree(int[] datas) {
        List<HuuffmanNode> list = new ArrayList<>();
        for (int data : datas) {
            HuuffmanNode node = new HuuffmanNode(data);
            list.add(node);
        }
        this.createHuffmanTree(list);
    }

    public HuffmanTree(int[] datas) {
        this.createHuffmanTree(datas);
    }

    public HuffmanTree(String characterString) {
        List<HuuffmanNode> list = statisticsString(characterString);
        this.createHuffmanTree(list);
    }

    public void preOrderTraverse(HuuffmanNode node) {
        if (node == null) {
            return;
        }
        preOrderTraverse(node.leftNode);
        preOrderTraverse(node.rightNode);
    }
}

class HuuffmanNode implements Comparable<HuuffmanNode> {
    HuuffmanNode leftNode;
    HuuffmanNode rightNode;
    Integer value;
    Integer data;

    public HuuffmanNode(int value) {
        this.value = value;
    }

    public HuuffmanNode(int data, int value) {
        this.data = data;
        this.value = value;
    }

    @Override
    public int compareTo(HuuffmanNode o) {
        return -(o.value - this.value);
    }
}

package com.stack;

public class Calculator {
    public static void main(String[] args) {
        System.out.println(calculation("1-3*2+(2*(6+2))+4-5"));
    }

    /**
     * 依次放入栈中，若优先级大于前一个符号，则取出数字计算再放入；遇到括号需把括号内的先取出计算再放回；最后反转栈顺序，计算最后值
     * @param expression 表达式
     * @return
     */
    public static int calculation(String expression) {
        String number = "0123456789";
        String integer = "";
        Stack<Integer> numberStack = new Stack<>(20);
        Stack<Character> charStack = new Stack<>(20);

        Stack<Integer> numberStackTemp = new Stack<>(20);
        Stack<Character> charStackTemp = new Stack<>(20);
        //是否取出计算值
        boolean isCalculationTwo=false;
        for (int index = 0; index < expression.length(); index++) {
            char symbol = expression.charAt(index);
            if(number.indexOf(symbol)==-1){
                if(!"".equals(integer)) {
                    numberStack.push(Integer.valueOf(integer));
                    integer="";
                }
                //小括号的值先计算
                if(')'==symbol){
                    while ('('!=charStack.top.data){
                        charStackTemp.push(charStack.pull());
                        numberStackTemp.push(numberStack.pull());
                    }
                    //括号取出不要
                    charStack.pull();
                    //最后一个数字存入
                    numberStackTemp.push(numberStack.pull());
                    //计算括号内的值并放入
                    while (charStackTemp.current>0){
                        numberStackTemp.push(calculationTwoB(numberStackTemp.pull(),numberStackTemp.pull(),charStackTemp.pull()));
                    }
                    //放回
                    numberStack.push(numberStackTemp.pull());
                }else {
                    if(isCalculationTwo){
                        numberStack.push(calculationTwo(numberStack.pull(),numberStack.pull(),charStack.pull()));
                        isCalculationTwo=false;
                    }
                    //如果优先级大于，则下一次取出计算值
                    if(charStack.top.data!=null&&symbol!='('&&getPriority(symbol)>getPriority(charStack.top.data)){
                        isCalculationTwo=true;
                    }
                    //不为括号则直接放入
                    charStack.push(symbol);
                }
            }else{
                integer+=symbol;
            }
        }
        //最后一个值放入
        if(!"".equals(integer)) {
            numberStack.push(Integer.valueOf(integer));
        }

        //反转顺序
        while(numberStack.current>0){
            numberStackTemp.push(numberStack.pull());
        }
        while(charStack.current>0){
            charStackTemp.push(charStack.pull());
        }
        //计算值
        while (charStackTemp.current>0){
            numberStackTemp.push(calculationTwoB(numberStackTemp.pull(),numberStackTemp.pull(),charStackTemp.pull()));
        }
       return  numberStackTemp.pull();

    }




    public static int getPriority(char symbol) {
        if ('+' == symbol || '-' == symbol) {
            return 0;
        } else if ('*' == symbol || '%' == symbol || '/' == symbol) {
            return 1;
        } else if ('(' == symbol || ')' == symbol) {
            return 2;
        } else {
            return -1;
        }

    }

    public static int calculationTwo(Integer b, Integer a, char symbol) {
        switch (symbol) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                throw new RuntimeException("不支持的运算符号：" + symbol);
        }
    }

    public static int calculationTwoB(Integer a, Integer b, char symbol) {
        switch (symbol) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '%':
                return a % b;
            case '/':
                return a / b;
            default:
                throw new RuntimeException("不支持的运算符号：" + symbol);
        }
    }
}

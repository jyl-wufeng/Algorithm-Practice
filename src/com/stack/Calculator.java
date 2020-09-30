package com.stack;

public class Calculator {

    public int calculation(String expression) {
        String number = "0123456789";
        String integer="";
        Stack<Integer> numberStack=new Stack<>(20);
        Stack<Character> charStack=new Stack<>(20);

        Stack<Integer> numberStackTemp=new Stack<>(20);
        Stack<Character> charStackTemp=new Stack<>(20);

        for (int index = 0; index < expression.length(); index++) {
            char symbol = expression.charAt(index);

            if (number.indexOf(symbol)==-1) {
                numberStack.push(Integer.valueOf(integer));
                integer="";
                //如果优先级高的需要先计算
                if(charStack.current>0&&getPriority(symbol)>getPriority(charStack.pull())){
                    //如果是后括号 取出到前括号的范围 计算
                    if(symbol==')'){
                        char operator=charStack.pull();
                        while (operator!='(') {
                            numberStackTemp.push(numberStack.pull());
                            numberStackTemp.push(numberStack.pull());
                            charStackTemp.push(operator);
                            operator=charStack.pull();
                        }
                        while (numberStackTemp.current>1){
                            numberStackTemp.push(calculationTwo(numberStackTemp.pull(),numberStackTemp.pull(),charStackTemp.pull()));
                        }

                    }else if(symbol=='('){

                    }
                }else{
                    charStack.push(symbol);
                }
            }else{
                integer+=symbol;
            }
        }

    }

    public int  getPriority(char symbol) {
        if('+'==symbol||'-'==symbol){
            return 0;
        }else if('*'==symbol||'%'==symbol||'/'==symbol){
            return 1;
        }else if('('==symbol||')'==symbol){
            return 2;
        }else {
            return -1;
        }

    }

    public int calculationTwo(Integer a,Integer b,char symbol){
        switch (symbol){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '%':
                return a%b;
            case '/':
                return a/b;
            default:
                throw new RuntimeException("不支持的运算符号："+symbol);
        }
    }
}

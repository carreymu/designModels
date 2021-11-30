package models.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        String expStr = getExpStr();
        HashMap<String, Integer> var = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        System.out.println("运算结果：" + expStr + "="  +calculator.run(var));
    }

    private static HashMap<String, Integer> getValue(String expStr) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        for (char c : expStr.toCharArray()) {
            if (c != '+' && c != '-') {
                if (!map.containsKey(String.valueOf(c))) {

                }
            }
        }
        return map;
    }

    private static String getExpStr() throws IOException {
        System.out.println("输入表达式：");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }
}

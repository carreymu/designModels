package models.interpreter;

import java.util.HashMap;

/**
 * 变量解析器
 */
public class VarExpression extends Expression{
    private String key; //key=a,key=b,key=c

    public VarExpression(String key) {
        this.key = key;
    }

    // var就是{a=10,b=20}
    // interpreter 根据变量名返回对应值
    @Override
    int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}

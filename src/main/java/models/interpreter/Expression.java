package models.interpreter;

import java.util.HashMap;

/**
 * 通过HashMap获取各变量的值
 */
public abstract class Expression {
    // 解释公式和数值，key是表达式参数[a,b,c], value是具体值
    // HashMap{a=10,b=20}
    abstract int interpreter(HashMap<String, Integer> var);
}

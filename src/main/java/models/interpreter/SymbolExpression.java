package models.interpreter;

import java.util.HashMap;

/**
 * 抽象运算符解析器，每个运算符都只和自己左右两个数字有关系
 * 但左右两个数字有可能也是一个解析都结果，物流何种类型，都是Expression类都实现类
 */
public class SymbolExpression extends Expression{
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    // 因SymbolExpression由其子类实现，所以interpreter是空实现
    @Override
    int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}

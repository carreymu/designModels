package models.interpreter;

import java.util.HashMap;

/**
 * 加法解析器
 */
public class AddExpression extends SymbolExpression{
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    // 处理相加
    // var仍然是{a=10,b=20}
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}

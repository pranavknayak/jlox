package com.craftinginterpreters.lox;

class Interpreter implements Expr.Visitor<Object>{
  @Override
  public Object visitLiteralExpr(Expr.Literal expr){
    return expr.value;
  }

  @Override
  public Object visitGroupingExpr(Expr.Grouping expr){
    return evaluate(expr.expression);
  }

  @Override
  public Object visitUnaryExpr(Expr.Unary expr){
    Object right = evaluate(expr.right);
    switch(expr.operator.type){
      case BANG:
        return !isTruthy(right);
      case MINUS:
        return -(double)right;
    }

    return null;
  }

  private Boolean isTruthy(Object object){
    if (object == null) return false;
    if (object instanceof Boolean) return (Boolean)object;
    return true;
  }

  private Object evaluate(Expr expr){
    return expr.accept(this);
  }


}

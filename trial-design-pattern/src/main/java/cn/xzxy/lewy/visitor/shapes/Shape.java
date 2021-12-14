package cn.xzxy.lewy.visitor.shapes;

import cn.xzxy.lewy.visitor.Visitor;

/**
 * 通用形状接口
 */
public interface Shape {
  void move(int x, int y);
  void draw();
  String accept(Visitor visitor);
}

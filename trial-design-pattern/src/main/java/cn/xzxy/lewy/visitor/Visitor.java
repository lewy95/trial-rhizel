package cn.xzxy.lewy.visitor;

import cn.xzxy.lewy.visitor.shapes.Circle;
import cn.xzxy.lewy.visitor.shapes.CompoundShape;
import cn.xzxy.lewy.visitor.shapes.Dot;
import cn.xzxy.lewy.visitor.shapes.Rectangle;

public interface Visitor {
  String visitDot(Dot dot);

  String visitCircle(Circle circle);

  String visitRectangle(Rectangle rectangle);

  String visitCompoundGraphic(CompoundShape cg);
}

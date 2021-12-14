package cn.xzxy.lewy.visitor.shapes;

import cn.xzxy.lewy.visitor.Visitor;

public class Rectangle implements Shape {
  private int id;
  private int x;
  private int y;
  private int width;
  private int height;

  public Rectangle(int id, int x, int y, int width, int height) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitRectangle(this);
  }

  @Override
  public void move(int x, int y) {
    // move shape
  }

  @Override
  public void draw() {
    // draw shape
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}

package io.github.simonvar.cglabs.core3d.figures

import io.github.simonvar.cglabs.core3d.Grid
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Line(val a: Point, val b: Point, val color: Color = Color.Black) extends Graphical {

  override def draw(grid: Grid): Unit = {
    grid.context.setStroke(color)
    grid.context.strokeLine(grid.zero.x + a.x, grid.zero.y - a.y, grid.zero.x + b.x, grid.zero.y - b.y)
    grid.context.setStroke(grid.defaultColor)
  }

  def colored(c: Color): Line = new Line(a, b, c)

  override def rotateX(alpha: Double): Line = new Line(a.rotateX(alpha), b.rotateX(alpha), color)

  override def rotateY(alpha: Double): Line = new Line(a.rotateY(alpha), b.rotateY(alpha), color)

  override def rotateZ(alpha: Double): Line = new Line(a.rotateZ(alpha), b.rotateZ(alpha), color)

  override def scale(alpha: Double): Line = new Line(a.scale(alpha), b.scale(alpha), color)

  override def moveX(alpha: Double): Line = new Line(a.moveX(alpha), b.moveX(alpha))

  override def moveY(alpha: Double): Line = new Line(a.moveY(alpha), b.moveY(alpha))

  override def moveZ(alpha: Double): Line = new Line(a.moveZ(alpha), b.moveZ(alpha))
}
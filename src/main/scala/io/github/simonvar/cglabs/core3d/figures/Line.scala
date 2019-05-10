package io.github.simonvar.cglabs.core3d.figures

import io.github.simonvar.cglabs.core3d.Context
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Line(override val tag: String, val a: Point, val b: Point, val color: Color = Color.Black) extends Graphical {

  override def draw(grid: Context): Unit = {
    grid.graphics.setStroke(color)
    grid.graphics.strokeLine(grid.zero.x + a.x, grid.zero.y - a.y, grid.zero.x + b.x, grid.zero.y - b.y)
    grid.graphics.setStroke(grid.defaultColor)
  }

  def colored(c: Color): Line = copy(color = c)

  override def rotateX(alpha: Double): Line = copy(a = a.rotateX(alpha), b = b.rotateX(alpha))

  override def rotateY(alpha: Double): Line = copy(a = a.rotateY(alpha), b = b.rotateY(alpha))

  override def rotateZ(alpha: Double): Line = copy(a = a.rotateZ(alpha), b = b.rotateZ(alpha))

  override def scale(alpha: Double): Line = copy(a = a.scale(alpha), b = b.scale(alpha))

  override def moveX(alpha: Double): Line = copy(a = a.moveX(alpha), b = b.moveX(alpha))

  override def moveY(alpha: Double): Line = copy(a = a.moveY(alpha), b = b.moveY(alpha))

  override def moveZ(alpha: Double): Line = copy(a = a.moveZ(alpha), b = b.moveZ(alpha))

  private def copy(tag: String = tag, a: Point = a, b: Point = b, color: Color = color) = new Line(tag, a, b, color)

}
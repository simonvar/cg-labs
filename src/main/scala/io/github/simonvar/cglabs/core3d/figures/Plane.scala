package io.github.simonvar.cglabs.core3d.figures

import io.github.simonvar.cglabs.core3d.Context
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Plane(override val tag: String, val a: Point, val b: Point, val c: Point, val d: Point, val color: Color) extends Graphical {

  override def draw(context: Context): Unit = {

    val gA = new Point(tag + "_a", context.zero.x + a.x, context.zero.y - a.y, a.z)
    val gB = new Point(tag + "_b", context.zero.x + b.x, context.zero.y - b.y, b.z)
    val gC = new Point(tag + "_c", context.zero.x + c.x, context.zero.y - c.y, c.z)
    val gD = new Point(tag + "_d", context.zero.x + d.x, context.zero.y - d.y, d.z)

    context.graphics.setStroke(color)

    context.graphics.strokeLine(gA.x, gA.y, gB.x, gB.y)
    context.graphics.strokeLine(gB.x, gB.y, gC.x, gC.y)
    context.graphics.strokeLine(gC.x, gC.y, gD.x, gD.y)
    context.graphics.strokeLine(gD.x, gD.y, gA.x, gA.y)
    context.graphics.setStroke(context.defaultColor)

    context.graphics.setFill(color)
    context.graphics.fillPolygon(Seq((gA.x, gA.y), (gB.x, gB.y), (gC.x, gC.y), (gD.x, gD.y)))
    context.graphics.setFill(context.defaultColor)

  }

  override def scale(alpha: Double): Plane =
    copy(a = a.scale(alpha), b = b.scale(alpha), c = c.scale(alpha), d = d.scale(alpha))

  override def rotateX(alpha: Double): Plane =
    copy(a = a.rotateX(alpha), b = b.rotateX(alpha), c = c.rotateX(alpha), d = d.rotateX(alpha))

  override def rotateY(alpha: Double): Plane =
    copy(a = a.rotateY(alpha), b = b.rotateY(alpha), c = c.rotateY(alpha), d = d.rotateY(alpha))

  override def rotateZ(alpha: Double): Plane =
    copy(a = a.rotateZ(alpha), b = b.rotateZ(alpha), c = c.rotateZ(alpha), d = d.rotateZ(alpha))

  override def moveX(alpha: Double): Plane =
    copy(a = a.moveX(alpha), b = b.moveX(alpha), c = c.moveX(alpha), d = d.moveX(alpha))

  override def moveY(alpha: Double): Plane =
    copy(a = a.moveY(alpha), b = b.moveY(alpha), c = c.moveY(alpha), d = d.moveY(alpha))

  override def moveZ(alpha: Double): Plane =
    copy(a = a.moveZ(alpha), b = b.moveZ(alpha), c = c.moveZ(alpha), d = d.moveZ(alpha))

  private def copy(tag: String = tag, a: Point = a, b: Point = b, c: Point = c, d: Point = d, color: Color = color) =
    new Plane(tag, a, b, c, d, color)


}

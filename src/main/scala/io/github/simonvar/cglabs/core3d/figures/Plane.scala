package io.github.simonvar.cglabs.core3d.figures

import io.github.simonvar.cglabs.core3d.Grid
import io.github.simonvar.cglabs.core3d.base.Graphical
import scalafx.scene.paint.Color

class Plane(val a: Point, val b: Point, val c: Point, val d: Point, val color: Color) extends Graphical {

  override def draw(grid: Grid): Unit = {

    val gA = new Point(grid.zero.x + a.x, grid.zero.y - a.y, a.z)
    val gB = new Point(grid.zero.x + b.x, grid.zero.y - b.y, b.z)
    val gC = new Point(grid.zero.x + c.x, grid.zero.y - c.y, c.z)
    val gD = new Point(grid.zero.x + d.x, grid.zero.y - d.y, d.z)

    grid.context.setStroke(color)

    grid.context.strokeLine(gA.x, gA.y, gB.x, gB.y)
    grid.context.strokeLine(gB.x, gB.y, gC.x, gC.y)
    grid.context.strokeLine(gC.x, gC.y, gD.x, gD.y)
    grid.context.strokeLine(gD.x, gD.y, gA.x, gA.y)
    grid.context.fillPolygon(Seq((gA.x, gA.y), (gB.x, gB.y), (gC.x, gC.y), (gD.x, gD.y)))
    grid.context.setStroke(grid.defaultColor)
  }

  override def scale(alpha: Double): Plane =
    new Plane(a.scale(alpha), b.scale(alpha), c.scale(alpha), d.scale(alpha), color)

  override def rotateX(alpha: Double): Plane =
    new Plane(a.rotateX(alpha), b.rotateX(alpha), c.rotateX(alpha), d.rotateX(alpha), color)

  override def rotateY(alpha: Double): Plane =
    new Plane(a.rotateY(alpha), b.rotateY(alpha), c.rotateY(alpha), d.rotateY(alpha), color)

  override def rotateZ(alpha: Double): Plane =
    new Plane(a.rotateZ(alpha), b.rotateZ(alpha), c.rotateZ(alpha), d.rotateZ(alpha), color)

  override def moveX(alpha: Double): Plane =
    new Plane(a.moveX(alpha), b.moveX(alpha), c.moveX(alpha), d.moveX(alpha), color)

  override def moveY(alpha: Double): Plane =
    new Plane(a.moveY(alpha), b.moveY(alpha), c.moveY(alpha), d.moveY(alpha), color)

  override def moveZ(alpha: Double): Plane =
    new Plane(a.moveZ(alpha), b.moveZ(alpha), c.moveZ(alpha), d.moveZ(alpha), color)

}

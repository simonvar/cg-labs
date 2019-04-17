package io.github.simonvar.cglabs.core

import scalafx.scene.canvas.GraphicsContext

class Grid(val height: Double, val width: Double, val context: GraphicsContext) {

  private val zero = new Point(width / 2, height / 2)

  def drawLine(p1: Point, p2: Point): Unit = {
    context.strokeLine(zero.x + p1.x, zero.y - p1.y, zero.x + p2.x, zero.y - p2.y)
  }

  def mirrorX(p: Point): Point = {
    new Point(p.x, -p.y)
  }

  def mirrorY(p: Point): Point = {
    new Point(-p.x, p.y)
  }


}

package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.core3d.{Line, Point, Rotatable, Scalable}
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Playground(private val zero: Point, private val context: GraphicsContext) extends Rotatable[Unit]
  with Scalable[Unit]{

  private val defaultColor = Color.Black
  private var lines = List[Line]()

  def addLine(l: Line): Unit = lines = l :: lines

  def draw(): Unit = {
    context.clearRect(0, 0, context.canvas.getWidth, context.canvas.getHeight)
    lines.foreach(drawLine)
  }

  def drawLine(line: Line): Unit = {
    context.setStroke(line.color)
    context.strokeLine(zero.x + line.a.x, zero.y - line.a.y, zero.x + line.b.x, zero.y - line.b.y)
    context.setStroke(defaultColor)
  }

  override def rotateX(alpha: Double): Unit = lines = lines.map(l => l.rotateX(alpha))

  override def rotateY(alpha: Double): Unit = lines = lines.map(l => l.rotateY(alpha))

  override def rotateZ(alpha: Double): Unit = lines = lines.map(l => l.rotateZ(alpha))

  override def scale(alpha: Double): Unit = lines = lines.map(l => l.scale(alpha))
}

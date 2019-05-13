package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.core3d.Context
import io.github.simonvar.cglabs.core3d.base.{Graphical, Rotatable, Scalable}

class GraphicalScene(private val grid: Context) extends Rotatable[Unit] with Scalable[Unit]{

  private var graphicals = List[Graphical]()

  def addGraphical(l: Graphical): Unit = graphicals = l :: graphicals

  def draw(): Unit = {
    grid.graphics.clearRect(0, 0, grid.graphics.canvas.getWidth, grid.graphics.canvas.getHeight)
    graphicals.foreach(l => l.draw(grid))
  }

  override def rotateX(alpha: Double): Unit = {
    graphicals = graphicals.map(l => l.rotateX(alpha))
  }

  override def rotateY(alpha: Double): Unit = graphicals = graphicals.map(l => l.rotateY(alpha))

  override def rotateZ(alpha: Double): Unit = graphicals = graphicals.map(l => l.rotateZ(alpha))

  override def scale(alpha: Double): Unit = graphicals = graphicals.map(l => l.scale(alpha))
}

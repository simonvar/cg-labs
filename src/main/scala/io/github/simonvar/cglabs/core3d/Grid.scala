package io.github.simonvar.cglabs.core3d

import io.github.simonvar.cglabs.core3d.figures.Point
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Grid(val width: Int, val height: Int, val depth: Int, val context: GraphicsContext, val defaultColor: Color) {

  val zero: Point = new Point(width / 2, height / 2, depth / 2)

}

package io.github.simonvar.cglabs.core3d

import io.github.simonvar.cglabs.core3d.figures.Point
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Context(val width: Int, val height: Int, val depth: Int, val graphics: GraphicsContext, val defaultColor: Color) {

  val zero: Point = new Point("zero", width / 2, height / 2, depth / 2)

}

package io.github.simonvar.cglabs

class Point(val x: Double, val y: Double) {

  def +(p: Point): Point = {
    new Point(x + p.x, y + p.y)
  }

}

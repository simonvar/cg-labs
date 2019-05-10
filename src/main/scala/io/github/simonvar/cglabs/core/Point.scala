package io.github.simonvar.cglabs.core

class Point(val x: Double, val y: Double) {

  def +(p: Point): Point = {
    new Point(x + p.x, y + p.y)
  }

  def *(a: Double) = new Point(x * a, y * a)


  override def toString = s"Point($x, $y)"
}

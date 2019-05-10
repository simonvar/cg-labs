package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.core.Point

class Bezier(points: List[Point]) extends (Double => Point) {

  override def apply(t: Double): Point = {
    var acc = new Point(0, 0)
    for ((p, i) <- points.view.zipWithIndex) {
      acc = accumulate(t)(points.size - 1)(i)(p)(acc)
    }
    acc
  }

  private def accumulate(t: Double)(n: Int)(k: Int)(current: Point)(acc: Point): Point = acc + (current * b(n, k, t))

  private def b(n: Int, k: Int, t: Double): Double = cnk(n, k) * Math.pow(t, k) * Math.pow(1 - t, n - k)

  private def cnk(n: Int, k: Int): Int = fac(n) / (fac(k) * fac(n - k))

  private def fac(k: Int): Int = 1 to k product

}

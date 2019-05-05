package io.github.simonvar.cglabs.core3d.base

trait Moveable[A] {

  def moveX(alpha: Double): A

  def moveY(alpha: Double): A

  def moveZ(alpha: Double): A

}

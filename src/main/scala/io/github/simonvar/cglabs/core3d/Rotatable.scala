package io.github.simonvar.cglabs.core3d

trait Rotatable[A] {

  def rotateX(alpha: Double) : A

  def rotateY(alpha: Double) : A

  def rotateZ(alpha: Double) : A

}

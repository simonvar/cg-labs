package io.github.simonvar.cglabs.core3d.base

trait Graphical extends Drawable with Moveable[Graphical] with Rotatable[Graphical] with Scalable[Graphical] {

  val tag: String

}

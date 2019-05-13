package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.Theme1.{HEIGHT, WIDTH}
import io.github.simonvar.cglabs.core.{Grid, Point, Polygon}
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.White

object Theme2 extends JFXApp {

  private final val WIDTH = 800
  private final val HEIGHT = 600

  stage = new JFXApp.PrimaryStage {
    title.value = "CG LAB 1"

    width = WIDTH
    maxWidth = WIDTH
    minWidth = WIDTH
    height = HEIGHT
    maxHeight = HEIGHT
    minHeight = HEIGHT

    scene = new Scene {
      fill = White
      content = new Canvas {
        height = HEIGHT
        width = WIDTH

        private val grid = new Grid(HEIGHT, WIDTH, graphicsContext2D)

        drawAxes(graphicsContext2D)

        private val points1 =
          new Point(0, 0) ::
            new Point(0, 50) ::
            new Point(50, 50) ::
            new Point(50, 0) :: Nil

        private val points2 =
          new Point(50, 0) ::
            new Point(50, -50) ::
            new Point(100, -50) ::
            new Point(100, 0) :: Nil

        private val points3 =
          new Point(100, 0) ::
            new Point(100, 50) ::
            new Point(150, 50) ::
            new Point(150, 0) :: Nil

        private val points4 = Bezier.formatPoints(new Point(150, 0) ::
          new Point(170, -100) ::
          new Point(230, -100) ::
          new Point(307, 70) :: Nil)


        drawBezier(grid, points1)
        drawBezier(grid, points2)
        drawBezier(grid, points3)
        drawBezier(grid, points4)

      }
    }

    private def drawBezier(grid: Grid, points: List[Point]): Unit = {
//      drawLines(grid, points)

      val bezier = new Bezier(points)

      val step = 0.01

      for (a <- 0 until 100 by 1) {

        val t1 = a.toDouble / 100
        val t2 = a.toDouble / 100 + step

        val current = bezier(t1)
        val next = bezier(t2)

        println(t1)
        println(current)

        grid.drawLine(current, next)
      }
    }

    private def drawLines(grid: Grid, points: List[Point]): Unit = {
      points.tail.scanLeft(points.head)((last: Point, p: Point) => {
        grid.drawLine(last, p)
        p
      })
    }

    private def drawAxes(context: GraphicsContext): Unit = {
      context.setStroke(Color.Red)
      context.strokeLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT)
      context.strokeLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2)
      context.setStroke(Color.Black)
    }

  }
}
package io.github.simonvar.cglabs

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.paint.Color.White

object App extends JFXApp {

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

        drawAxes(graphicsContext2D)

        private val polygon: Polygon = generatePolygon()
        private val grid = new Grid(HEIGHT, WIDTH, graphicsContext2D)

        drawPolygon(grid, polygon)

        private val xypolygon: Polygon = increase(mirrorXYPolygon(grid, polygon), 2)

        drawPolygon(grid, xypolygon)


      }
    }

  }

  private def drawAxes(context: GraphicsContext): Unit = {
    context.strokeLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT)
    context.strokeLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2)
  }

  private def generatePolygon(): Polygon = new Polygon(
    new Point(10, 10) ::
      new Point(60, 30) ::
      new Point(20, 90) :: Nil
  )

  private def drawPolygon(grid: Grid, polygon: Polygon): Unit = {
    polygon.points.tail.scanLeft(polygon.points.head)((last: Point, p: Point) => {
      grid.drawLine(last, p)
      p
    })

    grid.drawLine(polygon.points.head, polygon.points.last)
  }

  private def mirrorXPolygon(grid: Grid, p: Polygon) = new Polygon(p.points.map(t => grid.mirrorX(t)))

  private def mirrorYPolygon(grid: Grid, p: Polygon) = new Polygon(p.points.map(t => grid.mirrorY(t)))

  private def mirrorXYPolygon(grid: Grid, p: Polygon) = mirrorXPolygon(grid, mirrorYPolygon(grid, p))

  private def increase(p: Polygon, scale: Int) = new Polygon(
    p.points.map(t => new Point(t.x * scale, t.y * scale))
  )

  private def decrease(p: Polygon, scale: Int) = new Polygon(
    p.points.map(t => new Point(t.x * scale, t.y / scale))
  )

}

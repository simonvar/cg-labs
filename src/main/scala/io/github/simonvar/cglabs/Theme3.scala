package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.core3d.Context
import io.github.simonvar.cglabs.core3d.figures.{Line, Plane, Point}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.beans.property.BooleanProperty
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control._
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.White
import scalafx.scene.{Group, Node, Scene}


object Theme3 extends JFXApp {

  private val dragModeActiveProperty = new BooleanProperty(this, "dragModeActive", false)
  private val borderStyle = "" +
    "-fx-background-color: white;" +
    "-fx-border-color: black;" +
    "-fx-border-width: 1;" +
    "-fx-border-radius: 6;" +
    "-fx-padding: 6;"

  private final val WIDTH = 800
  private final val HEIGHT = 600
  private final val DEPTH = 800

  var playground: GraphicalScene = _

  stage = new JFXApp.PrimaryStage() {

    val panelsPane = new Pane() {

      val zoomPanel = makeDraggable(createZoomPanel())
      val progressPanelX = makeDraggable(createRotateX())
      val progressPanelY = makeDraggable(createRotateY())
      val progressPanelZ = makeDraggable(createRotateZ())

      zoomPanel.relocate(0, 35)
      progressPanelX.relocate(0, 106)
      progressPanelY.relocate(0, 142)
      progressPanelZ.relocate(0, 176)

      children = Seq(zoomPanel, progressPanelX, progressPanelY, progressPanelZ)
      alignmentInParent = Pos.TopLeft
    }

    val dragModeCheckbox = new CheckBox("Drag mode") {
      margin = Insets(6)
      selected = dragModeActiveProperty()
    }

    dragModeActiveProperty <== dragModeCheckbox.selected

    title = "CG"
    scene = new Scene {
      fill = White

      root = new BorderPane() {
        left = panelsPane
        bottom = dragModeCheckbox
        center = new Canvas {
          val grid = new Context(WIDTH, HEIGHT, DEPTH, graphicsContext2D, Color.Black)

          height = grid.height
          width = grid.width

          playground = new GraphicalScene(grid)

          playground.addGraphical(
            new Line(
              tag = "x",
              a = new Point("x_1", -WIDTH / 2, 0, 0),
              b = new Point("x_2", WIDTH / 2, 0, 0),
              color = Color.Red
            )
          )

          playground.addGraphical(
            new Line(
              tag = "y",
              a = new Point("y_1", 0, HEIGHT / 2, 0),
              b = new Point("y_2", 0, -HEIGHT / 2, 0),
              color = Color.Green
            )
          )


          playground.addGraphical(
            new Line(
              tag = "z",
              a = new Point("z_1", 0, 0, DEPTH / 2),
              b = new Point("z_2", 0, 0, -DEPTH / 2),
              color = Color.Blue
            )
          )

          playground.addGraphical(
            new Plane(
              tag = "plane",
              new Point("plane_1", 0, 0, 60),
              new Point("plane_2", 0, 200, 60),
              new Point("plane_3", 200, 200, 60),
              new Point("plane_4", 200, 0, 60),
              Color.Aquamarine
            )
          )

          playground.draw()

        }
      }
    }

    maximized = true
    fullScreen = true
  }

  private def makeDraggable(node: Node): Node = {

    val dragContext = new DragContext()

    new Group(node) {
      filterEvent(MouseEvent.Any) {
        me: MouseEvent =>
          if (dragModeActiveProperty()) {
            me.eventType match {
              case MouseEvent.MousePressed =>
                dragContext.mouseAnchorX = me.x
                dragContext.mouseAnchorY = me.y
                dragContext.initialTranslateX = node.translateX()
                dragContext.initialTranslateY = node.translateY()
              case MouseEvent.MouseDragged =>
                node.translateX = dragContext.initialTranslateX + me.x - dragContext.mouseAnchorX
                node.translateY = dragContext.initialTranslateY + me.y - dragContext.mouseAnchorY
              case _ =>
            }
            me.consume()
          }
      }
    }
  }

  private def createZoomPanel(): Node = new HBox(6) {
    val minS = 0.1
    val maxS = 10

    private val slider = new Slider() {
      min = minS
      max = maxS
      value = 1
    }

    slider.valueProperty.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
      playground.scale(1 / oldVal.doubleValue())
      playground.scale(newVal.doubleValue())
      playground.draw()
    }

    children = Seq(new Label("Scale:"), slider)
    style = borderStyle
  }

  private def createRotateX(): Node = new HBox(6) {
    private val slider = new Slider() {
      max = 360
    }

    slider.valueProperty.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
      playground.rotateX(newVal.doubleValue() - oldVal.doubleValue())
      playground.draw()
    }

    children = Seq(new Label("X:"), slider)
    style = borderStyle
  }

  private def createRotateY(): Node = new HBox(6) {
    val slider = new Slider()
    slider.max = 360
    slider.valueProperty.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
      playground.rotateY(newVal.doubleValue() - oldVal.doubleValue())
      playground.draw()
    }
    children = Seq(new Label("Y:"), slider)
    style = borderStyle
  }

  private def createRotateZ(): Node = new HBox(6) {
    val slider = new Slider()
    slider.max = 360
    slider.valueProperty.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
      playground.rotateZ(newVal.doubleValue() - oldVal.doubleValue())
      playground.draw()
    }
    children = Seq(new Label("Z:"), slider)
    style = borderStyle
  }

  private final class DragContext {
    var mouseAnchorX: Double = 0d
    var mouseAnchorY: Double = 0d
    var initialTranslateX: Double = 0d
    var initialTranslateY: Double = 0d
  }


}
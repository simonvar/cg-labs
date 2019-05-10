package io.github.simonvar.cglabs

import io.github.simonvar.cglabs.core3d.Grid
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


object Window extends JFXApp {

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

  var playground: Playground = _

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
          val grid = new Grid(WIDTH, HEIGHT, DEPTH, graphicsContext2D, Color.Black)

          height = grid.height
          width = grid.width

          playground = new Playground(grid)

          playground.addGraphical(new Line(
            new Point(-WIDTH / 2, 0, 0),
            new Point(WIDTH / 2, 0, 0),
            Color.Red
          ))

          playground.addGraphical(new Line(
            new Point(0, HEIGHT / 2, 0),
            new Point(0, -HEIGHT / 2, 0),
            Color.Green
          ))

          playground.addGraphical(new Line(
            new Point(0, 0, DEPTH / 2),
            new Point(0, 0, -DEPTH / 2),
            Color.Blue
          ))

          playground.addGraphical(new Plane(
            new Point(0, 0, 60),
            new Point(0, 200, 60),
            new Point(200, 200,60),
            new Point(200, 0, 60),
            Color.OrangeRed
          ))

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
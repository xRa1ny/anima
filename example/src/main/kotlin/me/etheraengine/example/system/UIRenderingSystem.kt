package me.etheraengine.example.system

import me.etheraengine.entity.Entity
import me.etheraengine.entity.UIButton
import me.etheraengine.entity.UILabel
import me.etheraengine.entity.UISlider
import me.etheraengine.entity.component.*
import me.etheraengine.scene.Scene
import me.etheraengine.system.RenderingSystem
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Graphics
import java.awt.geom.Dimension2D
import java.awt.geom.Point2D
import kotlin.math.roundToInt

@Component
class UIRenderingSystem : RenderingSystem {
    override fun render(scene: Scene, entities: List<Entity>, g: Graphics, now: Long, deltaTime: Long) {
        entities
            .filterIsInstance<UIButton>()
            .forEach {
                val position = it.getComponent<Point2D>()!!
                val dimension = it.getComponent<Dimension2D>()!!
                val text = it.getComponent<UIText>()!!

                g.color = text.color
                g.font = g.font.deriveFont(text.size)
                g.font = g.font.deriveFont(text.style)

                if (it.hasComponent<UIHoverable>()) {
                    val hoverable = it.getComponent<UIHoverable>()!!

                    if (hoverable.isHovered) {
                        g.font = g.font.deriveFont(text.size + 5)
                    }
                }

                if (it.hasComponent<UIClickable>()) {
                    val clickable = it.getComponent<UIClickable>()!!

                    if (clickable.isClicked) {
                        g.font = g.font.deriveFont(text.size)
                    }
                }

                // can be used to center text on the rendered UIButton (x-axis)
                val xOffset = (dimension.width / 2) - (g.fontMetrics.stringWidth(text.text) / 2)

                g.drawString(
                    text.text,
                    (position.x).toInt(),
                    (position.y + (dimension.height / 2) + (g.fontMetrics.height / 3)).toInt()
                )
            }

        entities
            .filterIsInstance<UILabel>()
            .forEach {
                val position = it.getComponent<Point2D>()!!
                val text = it.getComponent<UIText>()!!

                g.color = text.color
                g.font = g.font.deriveFont(text.size)
                g.font = g.font.deriveFont(text.style)

                g.drawString(
                    text.text,
                    position.x.toInt(),
                    position.y.toInt()
                )
            }

        entities
            .filterIsInstance<UISlider>()
            .forEach {
                val position = it.getComponent<Point2D>()!!
                val dimension = it.getComponent<Dimension2D>()!!
                val text = it.getComponent<UIText>()!!
                val value = it.getComponent<UIValue<Double>>()!!

                g.font = g.font.deriveFont(text.size)
                g.font = g.font.deriveFont(text.style)
                g.color = Color.LIGHT_GRAY

                val pinHeight = dimension.height / 8

                g.fillRect(
                    position.x.toInt(),
                    position.y.toInt() + ((dimension.height.toInt() / 2) - pinHeight.toInt() / 2),
                    dimension.width.toInt(),
                    pinHeight.toInt()
                )

                g.color = Color.GRAY

                if (it.hasComponent<UIHoverable>()) {
                    val hoverable = it.getComponent<UIHoverable>()!!

                    if (hoverable.isHovered) {
                        g.color = Color.WHITE
                    }
                }

                g.fillRect(
                    it.getPinXPositionForCurrentValue(10.0).toInt(),
                    position.y.toInt(),
                    10,
                    dimension.height.toInt()
                )

                g.color = text.color

                g.drawString(text.text, position.x.toInt(), position.y.toInt() + g.fontMetrics.height)
                g.drawString(
                    "${value.value.roundToInt()}%",
                    position.x.toInt(),
                    position.y.toInt() + dimension.height.toInt()
                )
            }

        entities
            .filter { it.hasComponent<Point2D>() }
            .filter { it.hasComponent<Dimension2D>() }
            .filter { it.hasComponent<UIFocusable>() }
            .forEach {
                val position = it.getComponent<Point2D>()!!
                val dimension = it.getComponent<Dimension2D>()!!
                val focusable = it.getComponent<UIFocusable>()!!

                if (focusable.isFocused) {
                    g.color = Color.WHITE
                    g.drawRect(
                        position.x.toInt(),
                        position.y.toInt(),
                        dimension.width.toInt(),
                        dimension.height.toInt()
                    )
                }
            }
    }
}
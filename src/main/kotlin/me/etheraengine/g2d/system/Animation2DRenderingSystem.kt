package me.etheraengine.g2d.system

import me.etheraengine.entity.Entity
import me.etheraengine.g2d.entity.component.Animations2D
import me.etheraengine.scene.Scene
import me.etheraengine.system.RenderingSystem
import org.springframework.stereotype.Component
import java.awt.Graphics
import java.awt.geom.Point2D
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * prebuilt system to handle 2D animation graphics
 */
@Component
class Animation2DRenderingSystem : RenderingSystem {
    override fun render(
        scene: Scene,
        entities: ConcurrentLinkedQueue<Entity>,
        g: Graphics,
        now: Long,
        deltaTime: Long
    ) {
        entities
            .filter { it.hasComponent<Animations2D>() }
            .filter { it.hasComponent<Point2D>() }
            .forEach {
                val animations = it.getComponent<Animations2D>()!!
                val currentAnimation = animations.animations[animations.currentAnimation]
                    ?: return@forEach

                // stop last animation
                animations.animations[animations.lastAnimation]?.isPlaying = false

                if (!currentAnimation.isPlaying) {
                    currentAnimation.isPlaying = true
                }

                if (currentAnimation.currentSpriteIndex == currentAnimation.spritesheet.sprites.size) {
                    if (currentAnimation.shouldLoop) {
                        currentAnimation.currentSpriteIndex = 0
                    } else {
                        currentAnimation.currentSpriteIndex -= 1
                    }
                }

                val position = it.getComponent<Point2D>()!!

                // render current frame now...
                val currentSprite = currentAnimation.spritesheet.sprites[currentAnimation.currentSpriteIndex]

                g.drawImage(
                    currentSprite,
                    position.x.toInt() + animations.renderOffsetX,
                    position.y.toInt() + animations.renderOffsetY,
                    animations.renderWidth,
                    animations.renderHeight,
                    null
                )

                val frameTime = System.currentTimeMillis() - currentAnimation.lastFrameTime

                if (frameTime >= currentAnimation.frameDuration) {
                    currentAnimation.currentSpriteIndex++
                }
            }
    }
}
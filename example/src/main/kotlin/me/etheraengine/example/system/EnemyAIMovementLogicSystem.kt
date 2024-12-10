package me.etheraengine.example.system

import me.etheraengine.entity.Entity
import me.etheraengine.example.entity.component.EnemyAI
import me.etheraengine.example.entity.component.Position
import me.etheraengine.g2d.entity.component.Movement2D
import me.etheraengine.scene.Scene
import me.etheraengine.system.LogicSystem
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentLinkedQueue

@Component
class EnemyAIMovementLogicSystem : LogicSystem {
    override fun update(scene: Scene, entities: ConcurrentLinkedQueue<Entity>, now: Long, deltaTime: Long) {
        entities
            .filter { it.hasComponent<EnemyAI>() }
            .filter { it.hasComponent<Position>() }
            .filter { it.hasComponent<Movement2D>() }
            .forEach {
                val enemyAi = it.getComponent<EnemyAI>()!!

                if (enemyAi.target == null) {
                    return@forEach
                }

                val enemyPosition = it.getComponent<Position>()!!
                val enemyMovement = it.getComponent<Movement2D>()!!
                val targetPosition = enemyAi.getTargetPosition()
                val distanceX = targetPosition!!.x - enemyPosition.x
                val distanceY = targetPosition.y - enemyPosition.y
                val xMovement = distanceX / enemyMovement.speed
                val yMovement = distanceY / enemyMovement.speed

                enemyMovement.vx = Math.clamp(xMovement, -1.0, 1.0)
                enemyMovement.vy = Math.clamp(yMovement, -1.0, 1.0)
            }
    }
}
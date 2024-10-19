package me.etheraengine.example.entity

import me.etheraengine.entity.Entity
import me.etheraengine.entity.component.State
import me.etheraengine.g2d.entity.component.Animations2D
import me.etheraengine.g2d.entity.component.Movement2D
import me.etheraengine.example.entity.component.*
import me.etheraengine.example.graphics.entity.*
import org.springframework.stereotype.Component

@Component
class Player : Entity() {
    init {
        addComponents(
            State(EntityState.IDLE),
            Movement2D(300f),
            PlayerMovement(),
            Position(400f, 400f, 50, 50),
            Attack(1f, 40f, 250, 40f),
            Health(10f, 1_000),
            Collideable(),
            Animations2D(
                EntityAnimation.IDLE_LEFT,
                mapOf(
                    EntityAnimation.IDLE_LEFT to PlayerIdleLeftAnimation(),
                    EntityAnimation.IDLE_RIGHT to PlayerIdleRightAnimation(),
                    EntityAnimation.WALK_LEFT to PlayerWalkLeftAnimation(),
                    EntityAnimation.WALK_RIGHT to PlayerWalkRightAnimation(),
                    EntityAnimation.ATTACK_LEFT to PlayerAttackLeftAnimation(),
                    EntityAnimation.ATTACK_RIGHT to PlayerAttackRightAnimation(),
                    EntityAnimation.DAMAGE_LEFT to PlayerDamageLeftAnimation(),
                    EntityAnimation.DAMAGE_RIGHT to PlayerDamageRightAnimation(),
                    EntityAnimation.DIE_LEFT to PlayerDieLeftAnimation(),
                    EntityAnimation.DIE_RIGHT to PlayerDieRightAnimation()
                ),
                100,
                100,
                -25
            )
        )
    }
}

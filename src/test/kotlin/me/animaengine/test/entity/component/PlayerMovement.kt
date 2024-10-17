package me.animaengine.test.entity.component

data class PlayerMovement(
    var isMovingLeft: Boolean = false,
    var isMovingRight: Boolean = false,
    var isMovingUp: Boolean = false,
    var isMovingDown: Boolean = false
)
package me.animaengine.test.world

import org.springframework.util.ResourceUtils

object TestWorld : World(
    // Uncomment this for world "grassland"
    //ResourceUtils.getFile("classpath:assets/worlds/grassland.txt"),
    ResourceUtils.getFile("classpath:assets/worlds/island.txt"),
    106
)
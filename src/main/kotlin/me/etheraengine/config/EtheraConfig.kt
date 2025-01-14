package me.etheraengine.config

import me.etheraengine.sound.SoundHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * Defines internal and external configurations for Ethera
 */
@Configuration
open class EtheraConfig(
    val soundHandlers: List<SoundHandler>,
) {
    /**
     * Defines the width of the rendered screen.
     * Default: 1920
     *
     * NOTE: May vary by user settings
     */
    @Value("\${settings.width}")
    var width: Int = 1920

    /**
     * Defines the height of the rendered screen.
     * Default: 1080
     *
     * NOTE: May vary by user settings
     */
    @Value("\${settings.height}")
    var height: Int = 1080

    /**
     * Defines the max available rate at which the rendering engine requests screen rendering (not tied to game logic).
     * Default: 144
     *
     * NOTE: May vary by user settings
     */
    @Value("\${settings.max-fps}")
    var maxFps: Int = 144

    /**
     * Defines the rate at which the logic engine requests logic updates from scenes and entities (not tied to rendering engine).
     * Default: 144
     *
     * NOTE: Programmatically set, users may not change this value, as this changes game logic speed.
     */
    @Value("\${settings.tps}")
    var tps: Int = 144

    /**
     * Defines the current sound volume level where 1.0f is full volume and 0.0f is no volume.
     * Default: 1f
     *
     * NOTE: May vary by user settings
     */
    @Value("\${settings.sound-volume}")
    var soundVolume = 1f

    /**
     * Defines the URL in which all sounds are located (Used by the sound engine for initial sound loading).
     * Default: classpath
     */
    @Value("\${settings.sounds-url}")
    var soundsUrl = ""

    /**
     * Defines the font url that is used by default.
     * Default: null
     */
    @Value("\${settings.font-url}")
    var fontUrl = ""
}
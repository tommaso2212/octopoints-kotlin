package com.example.octopoints.ui.common

import android.graphics.Color

class ColorPalette {

    companion object {
        fun getColor(): Int {
            return arrayOf(
                Color.rgb(243,221,73),  //yellow
                Color.rgb(40,184,124),   //green
                Color.rgb(31,173,147),
                Color.rgb(230,178,143)
            ).random()
        }
    }

}
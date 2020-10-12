package com.example.drawapp

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class CanvasViewState(val color: COLOR, val size: SIZE)

enum class COLOR(
    @ColorRes
    val value: Int
) {

    BLACK(R.color.colorPaintBlack),
    RED(R.color.colorPaintRed),
    BLUE(R.color.colorPaintBlue),
    GREEN(R.color.colorPaintGreen);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}

enum class SIZE(
    val value: Int
) {
    SMALL(4),
    MEDIUM(16),
    LARGE(32);

    companion object {
        private val map = values().associateBy(SIZE::value)
        fun from(size: Int) = map[size] ?: SMALL
    }
}

enum class TOOLS(
    @DrawableRes
    val value: Int,
    val toolId: ToolId
) {
    NORMAL(R.drawable.ic_baseline_brush_24, ToolId.NORMAL_TOOL),
    STROKE(R.drawable.ic_baseline_brush_24, ToolId.STROKE_TOOL),
    SIZE(R.drawable.ic_baseline_brush_24, ToolId.SIZE_TOOL),
    PALETTE(R.drawable.ic_baseline_brightness_1_24, ToolId.COLOR_TOOL);

    enum class ToolId {
        NORMAL_TOOL,
        STROKE_TOOL,
        COLOR_TOOL,
        SIZE_TOOL
    }
}


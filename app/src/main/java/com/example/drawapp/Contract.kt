package com.example.drawapp

import com.example.drawapp.base.Event

data class ViewState(
    val toolsList: List<ToolItem.ToolModel>,
    val colorList: List<ToolItem.ColorModel>,
    val sizeList: List<ToolItem.SizeModel>,
    val canvasViewState: CanvasViewState,
    val isPaletteVisible: Boolean,
    val isBrushSizeChangerVisible: Boolean,
    val isToolsVisible: Boolean,
    val selectedTool: TOOLS.ToolId
)

sealed class UiEvent() : Event{
    data class OnColorClick(val toolItem: ToolItem) : UiEvent()
    data class OnSizeClick(val toolItem: ToolItem) : UiEvent()
    data class OnToolsClick(val toolItem: ToolItem) : UiEvent()
    object OnToolbarClicked : UiEvent()
    object OnCanvasTouched : UiEvent()
}

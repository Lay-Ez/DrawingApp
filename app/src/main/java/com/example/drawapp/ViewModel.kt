package com.example.drawapp

import com.example.drawapp.base.BaseViewModel
import com.example.drawapp.base.Event

class ViewModel : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        toolsList = enumValues<TOOLS>().map {
            ToolItem.ToolModel(
                it.value,
                it.toolId,
                COLOR.BLACK.value
            )
        },
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
        canvasViewState = CanvasViewState(COLOR.BLACK, SIZE.SMALL),
        isPaletteVisible = false,
        isBrushSizeChangerVisible = false,
        isToolsVisible = false,
        selectedTool = ToolItem.ToolModel(
            R.drawable.ic_baseline_brush_24,
            TOOLS.ToolId.NORMAL_TOOL,
            COLOR.BLACK.value
        )
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnColorClick -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        color = COLOR.from((event.toolItem as ToolItem.ColorModel).color),
                    )
                )
            }
            is UiEvent.OnSizeClick -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        size = SIZE.from((event.toolItem as ToolItem.SizeModel).size),
                    )
                )
            }
            is UiEvent.OnToolsClick -> {
                return when ((event.toolItem as ToolItem.ToolModel).toolId) {
                    TOOLS.ToolId.SIZE_TOOL -> {
                        previousState.copy(
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = true
                        )
                    }
                    TOOLS.ToolId.COLOR_TOOL -> {
                        previousState.copy(
                            isPaletteVisible = true,
                            isBrushSizeChangerVisible = false
                        )
                    }
                    else -> {
                        return null
                    }
                }
            }
            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible
                )
            }
        }
        return null
    }
}
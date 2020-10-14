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
        canvasViewState = CanvasViewState(COLOR.BLACK, SIZE.SMALL, false),
        isPaletteVisible = false,
        isBrushSizeChangerVisible = false,
        isToolsVisible = false,
        selectedTool = TOOLS.ToolId.NORMAL_TOOL
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
                    TOOLS.ToolId.NORMAL_TOOL -> {
                        return previousState.copy(
                            canvasViewState = previousState.canvasViewState.copy(
                                isDashed = false
                            ),
                            selectedTool = TOOLS.ToolId.NORMAL_TOOL
                        )
                    }
                    TOOLS.ToolId.STROKE_TOOL -> {
                        return previousState.copy(
                            canvasViewState = previousState.canvasViewState.copy(
                                isDashed = true
                            ),
                            selectedTool = TOOLS.ToolId.STROKE_TOOL
                        )
                    }
                }
            }

            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible
                )
            }

            is UiEvent.OnCanvasTouched -> {
                return previousState.copy(
                    isPaletteVisible = false,
                    isBrushSizeChangerVisible = false,
                    isToolsVisible = false
                )
            }
        }
        return null
    }
}
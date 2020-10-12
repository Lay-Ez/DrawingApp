package com.example.drawapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.drawapp.base.showIf
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModel()

    private lateinit var colorPalette: ToolsLayout
    private lateinit var size: ToolsLayout
    private lateinit var tools: ToolsLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPalette = colorPaletteLayout as ToolsLayout
        size = sizeLayout as ToolsLayout
        tools = brushStylesLayout as ToolsLayout

        colorPalette.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnColorClick(it))
        }
        size.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSizeClick(it))
        }
        tools.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolsClick(it))
        }
        viewModel.viewState.observe(this, Observer(::render))

        openPalette.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolbarClicked)
        }
    }

    private fun render(viewState: ViewState) {
        colorPalette.showIf(viewState.isPaletteVisible)
        colorPalette.render(viewState.colorList)

        size.showIf(viewState.isBrushSizeChangerVisible)
        size.render(viewState.sizeList)

        tools.showIf(viewState.isToolsVisible)
        tools.render(viewState.toolsList)

        drawView.render(viewState.canvasViewState)
    }
}


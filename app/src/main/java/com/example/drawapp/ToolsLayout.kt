package com.example.drawapp

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.view_tools.view.*

class ToolsLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var onClick: (ToolItem) -> Unit = {}

    private val adapterDelegate = ListDelegationAdapter(
        colorAdapterDelegate {
            onClick(it)
        },
        sizeChangeAdapterDelegate {
            onClick(it)
        },
        toolsAdapterDelegate {
            onClick(it)
        }
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        toolsList.adapter = adapterDelegate
        toolsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    fun render(list: List<ToolItem>, canvasViewState: CanvasViewState) {
        list.forEach { toolItem ->
            if (toolItem is ToolItem.ToolModel && toolItem.toolId == TOOLS.ToolId.COLOR_TOOL) {
                toolItem.currentColor = canvasViewState.color.value
            }
            if (toolItem is ToolItem.ToolModel && toolItem.toolId == TOOLS.ToolId.SIZE_TOOL) {
                when (canvasViewState.size) {
                    SIZE.SMALL -> toolItem.icon = R.drawable.ic_brush_size_small
                    SIZE.MEDIUM -> toolItem.icon = R.drawable.ic_brush_size_medium
                    SIZE.LARGE -> toolItem.icon = R.drawable.ic_brush_size_large
                }
            }
        }
        adapterDelegate.items = list
        adapterDelegate.notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (ToolItem) -> Unit) {
        this.onClick = onClick
    }
}
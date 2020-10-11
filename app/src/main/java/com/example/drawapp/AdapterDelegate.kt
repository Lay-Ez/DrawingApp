package com.example.drawapp

import android.graphics.PorterDuff
import com.example.drawapp.base.Item
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_palette.view.*
import kotlinx.android.synthetic.main.item_size.view.*
import kotlinx.android.synthetic.main.item_tools.view.*

fun colorAdapterDelegate(
    onClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ColorModel, Item>(
        R.layout.item_palette
    ) {
        bind { list ->
            itemView.color.setColorFilter(
                context.resources.getColor(item.color),
                PorterDuff.Mode.SRC_IN
            )
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }

fun sizeChangeAdapterDelegate(
    onSizeClick: (Int) -> Unit
): AdapterDelegate<List<Item>> = adapterDelegateLayoutContainer<ToolItem.SizeModel, Item>(
    R.layout.item_size
) {
    bind { list ->
        itemView.tvToolsSize.text = item.size.toString()
        itemView.setOnClickListener {
            onSizeClick(adapterPosition)
        }
    }
}

fun toolsAdapterDelegate(
    onToolsClick: (Int) -> Unit
): AdapterDelegate<List<Item>> = adapterDelegateLayoutContainer<ToolItem.ToolModel, Item>(
    R.layout.item_tools
) {
    bind { list ->
        itemView.ivTool.setImageResource(item.icon)
        itemView.setOnClickListener {
            onToolsClick(adapterPosition)
        }
    }
}
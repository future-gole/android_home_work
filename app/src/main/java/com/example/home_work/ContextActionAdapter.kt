package com.example.home_work
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ContextActionAdapter(
    context: Context,
    private val items: MutableList<String>
) : ArrayAdapter<String>(
    context,
    R.layout.list_item_context_action, // <-- 引用新的列表项布局
    R.id.item_text, // <-- 引用布局中的 TextView ID
    items
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)

        val iconView: ImageView = view.findViewById(R.id.item_icon)
        val textView: TextView = view.findViewById(R.id.item_text)

        textView.text = items[position]
        iconView.setImageResource(R.mipmap.ic_launcher)

        return view
    }
}
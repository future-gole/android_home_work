package com.example.home_work

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * 这是 RecyclerView 的核心。
 * 它获取数据 (animals) 和一个点击回调 (onItemClick)，
 * 并负责创建和绑定视图。
 */
class AnimalAdapter(
    private val animals: List<ListItem>,
    private val onItemClick: (ListItem) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    /**
     * 创建一个新的 ViewHolder (当 RecyclerView 需要一个新的行视图时调用)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        // 加载 list_item_animal.xml 布局
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    /**
     * 绑定数据 (将数据绑定到 ViewHolder 的视图上)
     */
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.bind(animal, onItemClick)
    }

    /**
     * 返回列表项的总数
     */
    override fun getItemCount(): Int = animals.size

    /**
     * ViewHolder: 持有单行视图的引用，以避免重复的 findViewById
     * (对应你的 AnimalRow Composable)
     */
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_animal_name)
        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_animal_icon)

        fun bind(item: ListItem, onItemClick: (ListItem) -> Unit) {
            tvName.text = item.name
            // 确保你有名为 lion, tiger 等的 drawable 资源
            ivIcon.setImageResource(item.icon)

            // 设置整行的点击事件
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

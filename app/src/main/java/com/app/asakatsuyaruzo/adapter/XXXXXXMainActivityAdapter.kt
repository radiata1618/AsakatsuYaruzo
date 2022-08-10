//package com.app.asakatsuyaroze.adapter
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.app.asakatsuyaroze.R
//import com.app.asakatsuyaroze.SetAlarmPattern
//import com.app.asakatsuyaruzo.data.AlarmPattern
//import com.app.asakatsuyaroze.databinding.AlarmpatternRowBinding
//
//class MainActivityViewHolder(var binding: AlarmpatternRowBinding) : RecyclerView.ViewHolder(binding.root)
//
//class MainActivityAdapter(private val items: List<MainActivityAlarmPatternData>) : RecyclerView.Adapter<MainActivityViewHolder>() {
//
//    private var alarmPatternDataList = emptyList<MainActivityAlarmPatternData>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding: AlarmpatternRowBinding = DataBindingUtil.inflate(inflater, R.layout.alarmpattern_row, parent, false)
//
//        val holder = MainActivityViewHolder(binding)
//
//
//        holder.itemView.setOnClickListener {
//            itemClickListener?.onItemClick(holder)
//        }
//
//        return holder//crate view holder
//    }
//
//    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
//        holder.binding.alarmPatternData = items[position] //apply data
//    }
//
//    override fun getItemCount(): Int {
//        return items.count()
//    }
//
//    var itemClickListener: OnItemClickListener? = null
//    interface OnItemClickListener {
//        fun onItemClick(holder: MainActivityViewHolder)
//    }
//
//}
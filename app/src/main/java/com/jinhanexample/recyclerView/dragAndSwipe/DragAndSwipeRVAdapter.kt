 package com.jinhanexample.recyclerView.dragAndSwipe

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R
import java.util.*
import kotlin.collections.ArrayList

class DragAndSwipeRVAdapter(
    var dataList: ArrayList<String>,
    var onRVDragListener: OnRVDragListener
) :
    RecyclerView.Adapter<DragAndSwipeRVAdapter.ViewHolder>(),
    ItemTouchHelperListener {
    lateinit var context: Context
    var positionList = ArrayList<Int>()
    lateinit var itemViewWrap : LinearLayout



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.drag_nad_drop_swipe_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], onRVDragListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    //드래그를 이용해 실제 ui상 포지션을 이동하고, 데이터를 수정하는 부분입니다.
    override fun onItemMove(fromPosition: Int, afterPosition: Int) {

        //Collections.swap을 이용해 dataList의 data를 변경해주고,
        //notifyItemMoved 메소드를 이용해 변경 사실을 알려줍니다.
        //한가지 조건으로 position값이 20 이상으로는 드래그할 수 없도록 했습니다.
        //afterPosition이 20이 넘어가면 positionList의 첫번 째 값으로 item과 데이터를 되돌렸습니다.

        positionList.add(fromPosition)

        if (afterPosition > 20) {
            Collections.swap(dataList, fromPosition, positionList[0])
            notifyItemMoved(fromPosition, positionList[0])
            Toast.makeText(context, "20번 이상으로는 이동할 수 없습니다.", Toast.LENGTH_SHORT).show()
//            itemViewWrap.setBackgroundColor(context.resources.getColor(R.color.white, null))
            return
        } else {
            Collections.swap(dataList, fromPosition, afterPosition)
            notifyItemMoved(fromPosition, afterPosition)
//            itemViewWrap.setBackgroundColor(context.resources.getColor(R.color.white, null))
        }

    }

    //swipe로 ui에서 해당 아이템을 삭제하고, 데이터 또한 삭제하는 부분
    override fun onItemDismiss(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var itemWrap = view.findViewById<LinearLayout>(R.id.itemWrap)
        var drag_rv_text = view.findViewById<TextView>(R.id.drag_rv_text)
        var dragIcon = view.findViewById<ImageView>(R.id.dragIcon)

        @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
        fun bind(string: String, onRVDragListener: OnRVDragListener) {
            //드래그 시 itemView의 컬러를 변경하기 위해 bind했습니다.
            itemViewWrap = itemWrap

            drag_rv_text.text = "아이템 $string"
//            itemViewWrap.setBackgroundColor(context.resources.getColor(R.color.white, null))
            dragIcon.setOnTouchListener { v, event ->
                //드래그 시 드래그되는 position들의 값을 저장하겠습니다.
                //드래그가 취소됐을 때 가장 첫번째 position으로 data를 돌리기 위함입니다.
                //드래그중에는 onItemMove함수를 계속 호출하기 때문에
                //처음 버튼을 누른 시점에서 초기화를 시켜야 합니다.
                positionList.clear()
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
//                        itemWrap.setBackgroundColor(context.resources.getColor(R.color.teal_200, null))
                        onRVDragListener.onDragListener(this as RecyclerView.ViewHolder)
                    }
                }
                true
            }

        }

    }


}
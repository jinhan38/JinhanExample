package com.jinhanexample.recyclerView.dragAndSwipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sign

open class ItemMoveCallback(var listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        //drag와 swipe의 방향 설정
        var flagDrag: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        var flagSwipe = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(flagDrag, flagSwipe)

        //headerView나 footerView가 있어서 type을 구분하여 스크롤 허용/비허용 설정이 필요하다면 시 아래 코드 사용
//        return if (viewHolder.itemViewType == { headerView의 타입값 } ||
//            viewHolder.itemViewType == { footerView의 타입값 }) {
//            makeMovementFlags(0, 0)
//        } else {
//            makeMovementFlags(flagDrag, flagSwipe)
//        }

    }

    

    override fun onMove(
        recyclerView: RecyclerView,
        fromHolder: RecyclerView.ViewHolder,
        afterHolder: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMove(
            fromPosition = fromHolder.absoluteAdapterPosition,
            afterPosition = afterHolder.absoluteAdapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemDismiss(viewHolder.absoluteAdapterPosition)
    }

    /**
     * 롱클릭 드래그 활성화 여부
     * 아이템에 있는 Icon으로 드래그 기능을 설정할 것이기 때문에 long클릭 드래그 false
     */
    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }


    /**
     * 아이템 드래그 시 스크롤이 발생할 때의 속도 조절
     */
    override fun interpolateOutOfBoundsScroll(
        recyclerView: RecyclerView,
        viewSize: Int,
        viewSizeOutOfBounds: Int,
        totalSize: Int,
        msSinceStartScroll: Long
    ): Int {
        val direction = sign(viewSizeOutOfBounds.toDouble()).toInt()
        return 30 * direction
    }
}
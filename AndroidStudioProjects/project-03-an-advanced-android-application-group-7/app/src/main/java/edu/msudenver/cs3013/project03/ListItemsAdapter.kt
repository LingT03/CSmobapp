package edu.msudenver.cs3013.project03
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.cs3013.project03.model.MealItemUIModel
import edu.msudenver.cs3013.project03.MealItemViewHolder
import edu.msudenver.cs3013.project03.model.MealImageDataResult
import edu.msudenver.cs3013.project03.model.toMealItemUIModel
import edu.msudenver.cs3013.project03.MealBrowserActivity

/** Adapter to help with RecyclerView.
 * */
class ListItemsAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MealItemViewHolder>() {
    private val mealData = mutableListOf<MealItemUIModel>()
    val swipeToDeleteCallback = SwipeToDeleteCallback()

    fun setData(newData: List<MealItemUIModel>) {
        mealData.clear()
        mealData.addAll(newData)
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, newData: MealImageDataResult) {
        if (position >= 0 && position < mealData.size) {
            mealData[position] = newData.toMealItemUIModel()
            notifyItemChanged(position)
        }
    }
    fun removeItem(position: Int) {
        mealData.removeAt(position)
        notifyItemRemoved(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        val view = layoutInflater.inflate(R.layout.item_meal, parent, false)

        return MealItemViewHolder(view, imageLoader, object : MealItemViewHolder.OnClickListener {
            override fun onClick(mealData: MealItemUIModel) =
                onClickListener.onItemClick(mealData)
        })
    }

    override fun getItemCount() = mealData.size

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        holder.bindData(mealData[position])
    }

    interface OnClickListener {
        fun onItemClick(mealData: MealItemUIModel)
    }

    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is MealItemViewHolder) {
            makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_IDLE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) or makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
        } else {
            0
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}


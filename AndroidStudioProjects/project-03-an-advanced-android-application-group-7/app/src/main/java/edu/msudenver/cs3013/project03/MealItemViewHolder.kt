package edu.msudenver.cs3013.project03


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.cs3013.project03.model.MealItemUIModel
class MealItemViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.ViewHolder(containerView) {
    interface OnClickListener {
        fun onClick(mealData: MealItemUIModel)
    }

    private val mealdataView: TextView
            by lazy { containerView.findViewById(R.id.item_meal_name) }
    private val mealdataView2: ImageView
            by lazy { containerView.findViewById(R.id.item_meal_url) }

    fun bindData(mealData: MealItemUIModel) {
        mealdataView.text = mealData.strMeal
        require(mealData is MealItemUIModel) { "ExpectedListItemUiModel.Cat" }
        val meal = mealData

        imageLoader.loadImage(mealData.strMealThumb, mealdataView2)
        containerView.setOnClickListener {//checks for click
            onClickListener.onClick(meal) //action taken when clicked
        }
    }
    }



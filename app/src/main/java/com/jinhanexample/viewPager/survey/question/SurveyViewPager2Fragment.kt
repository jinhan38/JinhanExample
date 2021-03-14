package com.jinhanexample.viewPager.survey.question

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.Common
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentSurveyViewPager2Binding
import com.jinhanexample.databinding.SurveyRecyclerviewItemBinding
import com.jinhanexample.eventBus.event.GlobalBus
import com.jinhanexample.viewPager.survey.model.SurveyChoiceModel
import com.jinhanexample.viewPager.survey.model.SurveyListModel
import kotlinx.android.synthetic.main.survey_recyclerview_item.view.*

class SurveyViewPager2Fragment : Fragment() {

    private lateinit var questionList: SurveyListModel.List
    lateinit var b: FragmentSurveyViewPager2Binding
    lateinit var surveyRecyclerViewAdapter: SurveyRecyclerViewAdapter

    fun newInstance(
        questionList: SurveyListModel.List,
    ): SurveyViewPager2Fragment {
        val args = Bundle()
        args.putParcelable("questionList", questionList)
        val surveyViewPager2Fragment = SurveyViewPager2Fragment()
        surveyViewPager2Fragment.arguments = args
        return surveyViewPager2Fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            questionList = requireArguments()["questionList"] as SurveyListModel.List
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        b = FragmentSurveyViewPager2Binding.inflate(layoutInflater)


        b.tvQuestionNumber.text = "Question Number ${questionList.number}"
        b.tvQuestion.text = "Q. ${questionList.question}"

        b.rvSurveyViewPager2.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            surveyRecyclerViewAdapter = SurveyRecyclerViewAdapter()
            surveyRecyclerViewAdapter.addItem(questionList)
            adapter = surveyRecyclerViewAdapter

        }



        return b.root

    }


    inner class SurveyRecyclerViewAdapter :
        RecyclerView.Adapter<SurveyRecyclerViewAdapter.ItemViewHolder>() {

        private lateinit var questionList: SurveyListModel.List

        var clickedPosition: Int = -1

        fun addItem(questionList: SurveyListModel.List) {
            this.questionList = questionList
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(SurveyRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(questionList, completion = {
                notifyDataSetChanged()
            })
        }

        override fun getItemCount(): Int {
            return questionList.answerList.size
        }


        inner class ItemViewHolder(itemView: SurveyRecyclerviewItemBinding) :
            RecyclerView.ViewHolder(itemView.root) {


            fun bind(
                questionList: SurveyListModel.List,
                completion: () -> Unit,
            ) {

                itemView.apply {

                    tvSurveyQuestionItem.text =
                        questionList.answerList[absoluteAdapterPosition].item

                    tvSurveyQuestionItem.setBackgroundColor(Common.setColor(context,
                        R.color.white))

                    tvSurveyQuestionItem.setOnClickListener {

                        clickedPosition = absoluteAdapterPosition

                        completion()

                        GlobalBus.bus!!.post(
                            SurveyChoiceModel(questionList.number, clickedPosition)
                        )

                    }


                    if (clickedPosition == absoluteAdapterPosition) {
                        tvSurveyQuestionItem.setBackgroundColor(Common.setColor(context,
                            R.color.lightPink))
                    } else {
                        tvSurveyQuestionItem.setBackgroundColor(Common.setColor(context,
                            R.color.white))
                    }


                }
            }

        }

    }

}
package com.jinhanexample.viewPager.survey.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.databinding.FragmentSurveyViewPager2Binding
import com.jinhanexample.databinding.SurveyRecyclerviewItemBinding
import com.jinhanexample.eventBus.event.GlobalBus
import com.jinhanexample.viewPager.survey.model.SurveyListModel
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.survey_recyclerview_item.view.*

class SurveyViewPager2Fragment : Fragment() {

    companion object {
        private const val TAG = "SurveyViewPager2Fragmen"
    }

    private lateinit var questionList: SurveyListModel.List
    lateinit var b: FragmentSurveyViewPager2Binding
    private lateinit var surveyRecyclerViewAdapter: SurveyRecyclerViewAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        b = FragmentSurveyViewPager2Binding.inflate(layoutInflater)

        Log.d(TAG, "onCreateView: questionList : $questionList")

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


    override fun onDestroy() {
        super.onDestroy()
        GlobalBus.bus!!.unregister(this)
    }

    class SurveyRecyclerViewAdapter :
        RecyclerView.Adapter<SurveyRecyclerViewAdapter.ItemViewHolder>() {

        private lateinit var questionList: SurveyListModel.List

        fun addItem(questionList: SurveyListModel.List) {
            this.questionList = questionList
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(SurveyRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(questionList)
        }

        override fun getItemCount(): Int {
            Log.d(TAG, "getItemCount: ${questionList.answerList.size}")
            return questionList.answerList.size
        }


        class ItemViewHolder(
            itemView: SurveyRecyclerviewItemBinding,
        ) :
            RecyclerView.ViewHolder(itemView.root) {

            fun bind(questionList: SurveyListModel.List) {

                itemView.apply {
                    tvSurveyQuestionItem.text =
                        questionList.answerList[absoluteAdapterPosition].item

                    tvSurveyQuestionItem.setOnClickListener {

                        Log.d(TAG, "bind: 클릭 : $absoluteAdapterPosition")
                    }

                }
            }

        }

    }

}
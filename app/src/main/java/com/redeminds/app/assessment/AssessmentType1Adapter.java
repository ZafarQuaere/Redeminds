package com.redeminds.app.assessment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.redeminds.app.R;
import com.redeminds.app.utils.Assessment21;

import java.util.List;


public class AssessmentType1Adapter extends RecyclerView.Adapter<AssessmentType1Adapter.ViewHolder> {

    private static final String TAG = "AssessmentType1Adapter";
    private final Context context;
    private LayoutInflater inflater;
    private List<Assessment21> assessment21List;

    public AssessmentType1Adapter(Context context, List<Assessment21> assessment21List) {
        this.inflater = LayoutInflater.from(context);
        this.assessment21List = assessment21List;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Assessment21 current = assessment21List.get(position);
        holder.setQuestion(current.getQuestion());
//        holder.setAnswer(current.answer);
        holder.setOptions(current, position);
        Log.e(TAG, position + " :onBindViewHolder: " + current.toString());
    }

    @Override
    public int getItemCount() {
        if (assessment21List == null) {
            return 0;
        } else {
            return assessment21List.size();
        }
    }

    public List<Assessment21> getSelectedItems() {
        return assessment21List;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayoutContainer;
        private TextView textViewQuestion;
        private RadioGroup radioGroupOptions;
        private RadioButton radioButtonOption1, radioButtonOption2;
        private RadioButton radioButtonOption3, radioButtonOption4;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayoutContainer = itemView.findViewById(R.id.linear_layout_container);
            textViewQuestion = itemView.findViewById(R.id.text_view_question);
            radioGroupOptions = itemView.findViewById(R.id.radio_group_options);
            radioButtonOption1 = itemView.findViewById(R.id.radio_button_option_1);
            radioButtonOption2 = itemView.findViewById(R.id.radio_button_option_2);
            radioButtonOption3 = itemView.findViewById(R.id.radio_button_option_3);
            radioButtonOption4 = itemView.findViewById(R.id.radio_button_option_4);
        }

        public void setQuestion(String Assessment21) {
            textViewQuestion.setText(Assessment21);
        }


        public void setOptions(Assessment21 assessment21, int position) {
            radioGroupOptions.setTag(position);

            radioButtonOption1.setSelected(false);
            if (assessment21.isAnswered()) {
                radioGroupOptions.check(assessment21.getCheckedId());
            } else {
                radioGroupOptions.check(-1);
            }

            radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
                int pos = (int) group.getTag();
                Assessment21 que = assessment21List.get(pos);
                que.setAnswered(true);
                que.setCheckedId(checkedId);
                int selectedId = radioGroupOptions.getCheckedRadioButtonId();
                View radioButton = radioGroupOptions.findViewById(selectedId);
                int idx = radioGroupOptions.indexOfChild(radioButton);
                que.setSelectedIndex(idx);
            });
        }
    }
}

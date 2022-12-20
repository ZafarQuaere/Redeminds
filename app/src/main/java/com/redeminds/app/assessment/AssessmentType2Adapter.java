package com.redeminds.app.assessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redeminds.app.R;
import com.redeminds.app.utils.Assessment31;

import java.util.List;

public class AssessmentType2Adapter extends  RecyclerView.Adapter<AssessmentType2Adapter.ViewHolder>{

    public int mSelectedItem = -1;
    public List<Assessment31> mItems;
    private Context mContext;

    public AssessmentType2Adapter(Context context, List<Assessment31> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public AssessmentType2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.assessment_row_type2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentType2Adapter.ViewHolder holder, int position) {
        holder.mText.setText(mItems.get(position).getQuestion());
        holder.mRadio.setChecked(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Assessment31 getSelectedItem() {
        return mItems.get(mSelectedItem);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton mRadio;
        public TextView mText;

        public ViewHolder(final View inflate) {
            super(inflate);
            mText = (TextView) inflate.findViewById(R.id.text);
            mRadio = (RadioButton) inflate.findViewById(R.id.radio);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, mItems.size());
                }
            };
            itemView.setOnClickListener(clickListener);
            mRadio.setOnClickListener(clickListener);
        }
    }
}

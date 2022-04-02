package com.example.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuizAdapter extends  RecyclerView.Adapter<QuizAdapter.MyViewHolder>  {

    private  Context context;
    private  ArrayList<Quiz> quizzes;

    public  QuizAdapter(Context context, ArrayList<Quiz> quizzes){
        this.context = context;
        this.quizzes = quizzes;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quiz_row, parent, false);
        return new QuizAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        initializeComponents(holder, holder.getAdapterPosition());
    }

    private void initializeComponents(MyViewHolder holder, int adapterPosition) {
        //question
        holder.txt_question_number.setText("Question :" + (adapterPosition +1));
        holder.txt_question.setText(quizzes.get(adapterPosition).getQuestion());
        //image here

        if(quizzes.get(adapterPosition).getContainsImage() == 1){
            String ImgUrl = API.IMAGES+ quizzes.get(adapterPosition).getImageUrl();
            Picasso.with(context).load(ImgUrl).into(holder.imageView_question_image);
            holder.imageView_question_image.setVisibility(View.VISIBLE);
        }


        ArrayList<QuizOption> options = quizzes.get(adapterPosition).getQuizOptions();
        if(quizzes.get(adapterPosition).getIsAnswerMultiple()== 0){
            holder.radio_button_group.setVisibility(View.VISIBLE);

            holder.txt_option_A.setText(options.get(0).getDescription());
            holder.txt_option_B.setText(options.get(1).getDescription());
            holder.txt_option_C.setText(options.get(2).getDescription());
            holder.txt_option_D.setText(options.get(3).getDescription());

        }
        else {
            holder.checkBox_group.setVisibility(View.VISIBLE);
            holder.checkBox_A_Text.setText(options.get(0).getDescription());
            holder.checkBox_B_Text.setText(options.get(1).getDescription());
            holder.checkBox_C_Text.setText(options.get(2).getDescription());
            holder.checkBox_D_Text.setText(options.get(3).getDescription());
        }

        holder.txt_answer.setText(quizzes.get(adapterPosition).getAnswers());


    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_question_number;
        TextView txt_question;
        ImageView imageView_question_image;

        ///////////////////////////////////////////////////////////////////
        //Radio_button_group
        RadioGroup radio_button_group;
        //radio option A
        ConstraintLayout radio_block_A;
        RadioButton radio_option_A;
        TextView txt_option_A;

        //radio option B
        ConstraintLayout radio_block_B;
        RadioButton radio_option_B;
        TextView txt_option_B;

        //radio option C
        ConstraintLayout radio_block_C;
        RadioButton radio_option_C;
        TextView txt_option_C;

        //radio option D
        ConstraintLayout radio_block_D;
        RadioButton radio_option_D;
        TextView txt_option_D;


        ////////////////////////////////////////////////////////////////
        //Checkbox group
        LinearLayout checkBox_group;

        //Checkbox A
        ConstraintLayout checkbox_block_A;
        CheckBox checkBox_A;
        TextView checkBox_A_Text;

        //Checkbox B
        ConstraintLayout checkbox_block_B;
        CheckBox checkBox_B;
        TextView checkBox_B_Text;

        //Checkbox c
        ConstraintLayout checkbox_block_C;
        CheckBox checkBox_C;
        TextView checkBox_C_Text;

        //Checkbox D
        ConstraintLayout checkbox_block_D;
        CheckBox checkBox_D;
        TextView checkBox_D_Text;


        ////////////////////////////////////////////////////////////////
        ///Answer block.
        ConstraintLayout quiz_answer_box;
        ImageView imageView_answer_icon;
        TextView txt_answer;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             txt_question_number = itemView.findViewById(R.id.txt_question_number);
             txt_question = itemView.findViewById(R.id.txt_question);
             imageView_question_image = itemView.findViewById(R.id.imageView_question_image);
             imageView_question_image.setVisibility(View.GONE);

            ///////////////////////////////////////////////////////////////////
            //Radio_button_group
            radio_button_group = itemView.findViewById(R.id.radio_button_group);
            radio_button_group.setVisibility(View.GONE);
            //radio option A
            radio_block_A = itemView.findViewById(R.id.radio_block_A);
            radio_option_A = itemView.findViewById((R.id.radio_option_A));
            txt_option_A = itemView.findViewById(R.id.txt_option_A);

            //radio option B
            radio_block_B = itemView.findViewById(R.id.radio_block_B);
            radio_option_B = itemView.findViewById(R.id.radio_option_B);
            txt_option_B = itemView.findViewById(R.id.txt_option_B);

            //radio option C
            radio_block_C = itemView.findViewById(R.id.radio_block_C);
            radio_option_C = itemView.findViewById(R.id.radio_option_C);
            txt_option_C = itemView.findViewById(R.id.txt_option_C);

            //radio option D
            radio_block_D = itemView.findViewById(R.id.radio_block_D);
            radio_option_D = itemView.findViewById(R.id.radio_option_D);
            txt_option_D = itemView.findViewById(R.id.txt_option_D);


            ////////////////////////////////////////////////////////////////
            //Checkbox group
            checkBox_group = itemView.findViewById(R.id.checkBox_group);
            checkBox_group.setVisibility(View.GONE);

            //Checkbox A
            checkbox_block_A = itemView.findViewById(R.id.checkbox_block_A);
            checkBox_A = itemView.findViewById(R.id.checkBox_A);
            checkBox_A_Text = itemView.findViewById(R.id.checkBox_A_Text);

            //Checkbox B
            checkbox_block_B = itemView.findViewById(R.id.checkbox_block_B);
            checkBox_B = itemView.findViewById(R.id.checkBox_B);
            checkBox_B_Text = itemView.findViewById(R.id.checkBox_B_Text);

            //Checkbox c
            checkbox_block_C = itemView.findViewById(R.id.checkbox_block_C);
            checkBox_C = itemView.findViewById(R.id.checkBox_C);
            checkBox_C_Text = itemView.findViewById(R.id.checkBox_C_Text);

            //Checkbox D
            checkbox_block_D = itemView.findViewById(R.id.checkbox_block_D);
            checkBox_D = itemView.findViewById(R.id.checkBox_D);
            checkBox_D_Text = itemView.findViewById(R.id.checkBox_D_Text);


            ////////////////////////////////////////////////////////////////
            ///Answer block.
            quiz_answer_box = itemView.findViewById(R.id.quiz_answer_box);
            imageView_answer_icon = itemView.findViewById(R.id.img_view_quiz_status_icon);
            txt_answer = itemView.findViewById(R.id.txt_answer);

        }
    }
}

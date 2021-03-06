package com.example.cars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class QuizTestAdapter extends  RecyclerView.Adapter<QuizTestAdapter.MyViewHolder>  {

    private  Context context;
    private  ArrayList<AttemptLine> attemptLines;
    private API api;
    private Attempt attempt;

    public QuizTestAdapter(Context context, ArrayList<AttemptLine> attemptLines, Attempt attempt){
        this.context = context;
        this.attemptLines = attemptLines;
        this.attempt = attempt;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quiz_practise_row, parent, false);
        return new QuizTestAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        initializeComponents(holder, holder.getAdapterPosition());





    }

    private void initializeComponents(MyViewHolder holder, int adapterPosition ) {


        //Control Array
        ArrayList<ConstraintLayout> radio_blocks = new ArrayList<ConstraintLayout>();
        radio_blocks.add(holder.radio_block_A);
        radio_blocks.add(holder.radio_block_B);
        radio_blocks.add(holder.radio_block_C);
        radio_blocks.add(holder.radio_block_D);

        ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
        radioButtons.add(holder.radio_option_A);
        radioButtons.add(holder.radio_option_B);
        radioButtons.add(holder.radio_option_C);
        radioButtons.add(holder.radio_option_D);

        ArrayList<ConstraintLayout> checkbox_blocks = new ArrayList<ConstraintLayout>();
        checkbox_blocks.add(holder.checkbox_block_A);
        checkbox_blocks.add(holder.checkbox_block_B);
        checkbox_blocks.add(holder.checkbox_block_C);
        checkbox_blocks.add(holder.checkbox_block_D);

        ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add(holder.checkBox_A);
        checkBoxes.add(holder.checkBox_B);
        checkBoxes.add(holder.checkBox_C);
        checkBoxes.add(holder.checkBox_D);

        if(attemptLines.size() > 0){
            Quiz quizzes = attemptLines.get(adapterPosition).getQuiz();
            api = new API(context);


            //question
            holder.txt_question_number.setText("Question :" + (adapterPosition +1));
            holder.txt_question.setText(attemptLines.get(adapterPosition).getQuiz().getQuestion());
            //image here

            if(quizzes.getContainsImage() == 1){
                String ImgUrl = api.getImageURL()+ quizzes.getImageUrl();
                Picasso.with(context).load(ImgUrl).into(holder.imageView_question_image);
                holder.imageView_question_image.setVisibility(View.VISIBLE);
            }


            ArrayList<QuizOption> options = quizzes.getQuizOptions();
            if(quizzes.getIsAnswerMultiple()== 0){
                holder.radio_button_group.setVisibility(View.VISIBLE);

                holder.txt_option_A.setText(options.get(0).getDescription());
                holder.txt_option_B.setText(options.get(1).getDescription());
                holder.txt_option_C.setText(options.get(2).getDescription());
                holder.txt_option_D.setText(options.get(3).getDescription());
                if(attempt.getIsSubmitted() ==1){
                    holder.radio_option_A.setEnabled(false);
                    holder.radio_option_B.setEnabled(false);
                    holder.radio_option_C.setEnabled(false);
                    holder.radio_option_D.setEnabled(false);
                }

                holder.radio_option_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleRadioButton(radioButtons);
                        holder.radio_option_A.setChecked(true);
                       int optionId = attemptLines.get(adapterPosition).getQuiz().getQuizOptions().get(0).getId();
                       attemptLines.get(adapterPosition).setUserSelection(optionId+"");
                        attemptLines.get(adapterPosition).setIsAnswered(1);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));

                    }
                });

                holder.radio_option_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleRadioButton(radioButtons);
                        holder.radio_option_B.setChecked(true);
                        int optionId = attemptLines.get(adapterPosition).getQuiz().getQuizOptions().get(1).getId();
                        attemptLines.get(adapterPosition).setUserSelection(optionId+"");
                        attemptLines.get(adapterPosition).setIsAnswered(1);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));

                    }
                });
                holder.radio_option_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleRadioButton(radioButtons);
                        holder.radio_option_C.setChecked(true);
                        int optionId = attemptLines.get(adapterPosition).getQuiz().getQuizOptions().get(2).getId();
                        attemptLines.get(adapterPosition).setUserSelection(optionId+"");
                        attemptLines.get(adapterPosition).setIsAnswered(1);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));

                    }
                });
                holder.radio_option_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleRadioButton(radioButtons);
                        holder.radio_option_D.setChecked(true);
                        int optionId = attemptLines.get(adapterPosition).getQuiz().getQuizOptions().get(3).getId();
                        attemptLines.get(adapterPosition).setUserSelection(optionId+"");
                        attemptLines.get(adapterPosition).setIsAnswered(1);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));

                    }
                });


            }
            else {
                holder.checkBox_group.setVisibility(View.VISIBLE);
                holder.checkBox_A_Text.setText(options.get(0).getDescription());
                holder.checkBox_B_Text.setText(options.get(1).getDescription());
                holder.checkBox_C_Text.setText(options.get(2).getDescription());
                holder.checkBox_D_Text.setText(options.get(3).getDescription());
                if(attempt.getIsSubmitted() ==1){
                   holder.checkBox_A.setEnabled(false);
                   holder.checkBox_B.setEnabled(false);
                   holder.checkBox_C.setEnabled(false);
                   holder.checkBox_D.setEnabled(false);
                }

                holder.checkBox_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = gettingSelectedCheckBox(checkBoxes, attemptLines.get(adapterPosition).getQuiz().getQuizOptions()) ;
                        if(answer== ""){
                            attemptLines.get(adapterPosition).setIsAnswered(0);
                        }
                        else {
                            attemptLines.get(adapterPosition).setIsAnswered(1);
                        }
                        attemptLines.get(adapterPosition).setUserSelection(answer);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));

                    }
                }); holder.checkBox_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = gettingSelectedCheckBox(checkBoxes, attemptLines.get(adapterPosition).getQuiz().getQuizOptions()) ;
                        if(answer== ""){
                            attemptLines.get(adapterPosition).setIsAnswered(0);
                        }
                        else {
                            attemptLines.get(adapterPosition).setIsAnswered(1);
                        }
                        attemptLines.get(adapterPosition).setUserSelection(answer);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));
                    }
                }); holder.checkBox_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = gettingSelectedCheckBox(checkBoxes, attemptLines.get(adapterPosition).getQuiz().getQuizOptions()) ;
                        if(answer== ""){
                            attemptLines.get(adapterPosition).setIsAnswered(0);
                        }
                        else {
                            attemptLines.get(adapterPosition).setIsAnswered(1);
                        }
                        attemptLines.get(adapterPosition).setUserSelection(answer);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));
                    }
                }); holder.checkBox_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String answer = gettingSelectedCheckBox(checkBoxes, attemptLines.get(adapterPosition).getQuiz().getQuizOptions()) ;
                        if(answer== ""){
                            attemptLines.get(adapterPosition).setIsAnswered(0);
                        }
                        else {
                            attemptLines.get(adapterPosition).setIsAnswered(1);
                        }
                        attemptLines.get(adapterPosition).setUserSelection(answer);
                        api.updateAttemptLine(attemptLines.get(adapterPosition));
                    }
                });

            }
            if(attempt.getIsSubmitted() == 1){
                holder.quiz_answer_box.setVisibility(View.VISIBLE);
                 ArrayList<String> RightAnswer = getRightAnswers(attemptLines.get(adapterPosition).getQuiz().getAnswers(), attemptLines.get(adapterPosition).getQuiz().getQuizOptions());
                 holder.txt_answer.setText("Correct Answer: " + TextUtils.join(",", RightAnswer));

                ArrayList<String> UserAnswer = getRightAnswers(attemptLines.get(adapterPosition).getUserSelection(), attemptLines.get(adapterPosition).getQuiz().getQuizOptions());
                 AnimateAnswer(UserAnswer, radio_blocks, radioButtons, checkbox_blocks, checkBoxes);
                holder.imageView_answer_icon.setVisibility(View.VISIBLE);

                if(attemptLines.get(adapterPosition).getUserSelection().equals(attemptLines.get(adapterPosition).getQuiz().getAnswers())){
                    holder.imageView_answer_icon.setImageResource(R.drawable.ic_correct_answer);
                }
                else {
                    holder.imageView_answer_icon.setImageResource(R.drawable.ic_wrong_answer);
                }
            }

        }




    }

    private  void  toggleRadioButton(ArrayList<RadioButton> buttons){
        for (RadioButton button: buttons) {
            button.setChecked(false);
        }
    }

    private String gettingSelectedCheckBox(ArrayList<CheckBox> boxes, ArrayList<QuizOption> options){
        ArrayList<Integer> answers = new ArrayList<Integer>();
        for(Integer i =0; i< boxes.size(); i++){
            if(boxes.get(i).isChecked()){
                answers.add(options.get(i).getId());
            }
        }
        if (answers.size() ==0){
            return "";
        }
        else {
            Collections.sort(answers);
            return  TextUtils.join("," , answers);
        }
    }

    private ArrayList<String> getRightAnswers (String Answer, ArrayList<QuizOption> options){
        ArrayList<String> output = new ArrayList<String>();

        try{

            //possible answers
            ArrayList<String> possibleAnswerOptions = new ArrayList<String>();
            possibleAnswerOptions.add("A");
            possibleAnswerOptions.add("B");
            possibleAnswerOptions.add("C");
            possibleAnswerOptions.add("D");


            String[] AnswerString = Answer.split(",");


            ArrayList<QuizOption> list= CloneArrayList(options);
            for(int i =0; i< AnswerString.length; i++){
                for(int j = 0; j< list.size(); j++){
                    if(Integer.parseInt(AnswerString[i].trim()) == list.get(j).getId()){
                        output.add( possibleAnswerOptions.get(j));
                        possibleAnswerOptions.remove(j);
                        list.remove(j);
                    }
                }
            }
            return output;
        }
        catch (Exception e){
            return  output;
        }
    }


    @SuppressLint("ResourceAsColor")
    private  void  AnimateAnswer(ArrayList<String> answers, ArrayList<ConstraintLayout> radioButtonBlock, ArrayList<RadioButton> radioButtons, ArrayList<ConstraintLayout> checkboxBlock, ArrayList<CheckBox> checkBoxes){

        ArrayList<String> possibleAnswerOptions = new ArrayList<String>();
        possibleAnswerOptions.add("A");
        possibleAnswerOptions.add("B");
        possibleAnswerOptions.add("C");
        possibleAnswerOptions.add("D");

        if(answers.size() == 1){

            for(int i =0; i<possibleAnswerOptions.size(); i++){
                if(answers.get(0) == possibleAnswerOptions.get(i) ){
                    radioButtons.get(i).toggle();
                }
            }

        }
        else {
            for(int i =0; i< answers.size(); i++){
                for(int j =0; j<possibleAnswerOptions.size(); j++){
                    if(answers.get(i) == possibleAnswerOptions.get(j)){
                        checkBoxes.get(j).setChecked(true);
                    }
                }
            }
        }


    }

    private  ArrayList<QuizOption> CloneArrayList(ArrayList<QuizOption> list){
        ArrayList<QuizOption> output =new ArrayList<QuizOption>();
        for(QuizOption item: list){
            output.add(item);
        }
        return output;
    }


    @Override
    public int getItemCount() {
        return attemptLines.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {




        ////
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



            /////////////////////////////////////////////////////////////////////
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
            quiz_answer_box.setVisibility(View.GONE);
            imageView_answer_icon = itemView.findViewById(R.id.imageview_answer_icon);
            txt_answer = itemView.findViewById(R.id.txt_answer);

        }
    }
}

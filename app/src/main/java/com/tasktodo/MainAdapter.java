package com.tasktodo;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    private List<MainTask> taskList;
    private Activity context;
    private ThetasksDB database;

   public MainAdapter(Activity context, List<MainTask> taskList) {
       this.context = context;
       this.taskList = taskList;
       notifyDataSetChanged();
   }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize main task

        MainTask task = taskList.get(position);

        //Initialize database

        database = ThetasksDB.getInstance(context);

        //Set txt on text view
        holder.textView.setText(task.getText());



        holder.btnEdit.setOnClickListener(view -> {

            //Initialize main data
            MainTask t = taskList.get(holder.getAdapterPosition());


            //Get id

            int sId = t.getID();

            //Get text

            String sText = t.getText();

            //Create dialog

            Dialog dialog = new Dialog(context);

            //Set content view

            dialog.setContentView(R.layout.dialog_update);


            //Initialize width

            int  width = WindowManager.LayoutParams.MATCH_PARENT;


            //Initialize height

            int height = WindowManager.LayoutParams.WRAP_CONTENT;


            //set layout

            dialog.getWindow().setLayout(width,height);


            //show dialog

            dialog.show();

            //Initialize and assign variable

            EditText editText = dialog.findViewById(R.id.edit_text);
            Button btnUpdate = dialog.findViewById(R.id.btn_update);

            //Set text on edit text

            editText.setText(sText);

            btnUpdate.setOnClickListener(view1 -> {


                //Dismiss dialog

                dialog.dismiss();


                //Get updated text from edit text

                String uText = editText.getText().toString().trim();


                //Update text in database

                database.mainDao().update(sId,uText);

                //Notify when data is updated

                taskList.clear();
                taskList.addAll(database.mainDao().getAll());
                notifyDataSetChanged();
            });

        });

        holder.btnDelete.setOnClickListener(view -> {

            //Initialize main data

            MainTask t = taskList.get(holder.getAdapterPosition());


            //Delete text from database

            database.mainDao().delete(t);


            //Notify when data is deleted


            int position1 = holder.getAdapterPosition();
            taskList.remove(position1);
            notifyItemRemoved(position1);
            notifyItemRangeChanged(position1, taskList.size());
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initialize variable

        TextView textView;
        ImageView btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable

            textView = itemView.findViewById(R.id.text_view);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);

        }
    }
}

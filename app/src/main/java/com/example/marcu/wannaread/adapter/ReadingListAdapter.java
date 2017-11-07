package com.example.marcu.wannaread.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcu.wannaread.R;
import com.example.marcu.wannaread.database.DataBaseControl;
import com.example.marcu.wannaread.database.DataBaseReading;
import com.example.marcu.wannaread.database.Reading;

/**
 * Created by marcu on 11/7/2017.
 */

public class ReadingListAdapter extends RecyclerView.Adapter<ReadingListAdapter.ViewHolder> {

    private RecyclerView teste;
    private Cursor mCursor;
    private OnReadingClickInterface clickInterface;
    private Context context;

    public ReadingListAdapter(Context context, OnReadingClickInterface onReadingClickInterface) {
        this.context = context;
        this.clickInterface = onReadingClickInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla o layout correto
        View view = LayoutInflater.from(context)
                .inflate(R.layout.reading_item_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Recupera os valores da lista na posicao atual
        // Mapeia o index do banco de dados
        final int idIndex = mCursor.getColumnIndex(DataBaseReading.ID);
        int nameIndex = mCursor.getColumnIndex(DataBaseReading.READING_NAME);
        int statusIndex = mCursor.getColumnIndex(DataBaseReading.READING_STATUS);

        // Move o cursor do bd para a posicaco
        mCursor.moveToPosition(position);

        // Mapeia os dados no item da lista
        holder.tvBookName.setText(mCursor.getString(nameIndex));

        if(mCursor.getInt(statusIndex) == 1)
            holder.icStatus.setImageResource(R.drawable.ic_status_stopped);
        else
            holder.icStatus.setImageResource(R.drawable.ic_status_started);


        // Deleta o livro ao clicar no delete para cada item da lista
        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Busca o banco de dados
                DataBaseControl db = new DataBaseControl(holder.itemView.getContext());
                db.deleteReading(mCursor.getInt(idIndex));
                // Atualiza a lista
                mCursor = db.loadReadings();
                notifyDataSetChanged();
            }
        });

        /*// Click do check box para cada item da lista
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Move o cursor para posicao recebida
                mCursor.moveToPosition(holder.getAdapterPosition());
                // Busca o banco de dados
                DataBaseControl db = new DataBaseControl(holder.itemView.getContext());
                *//*db.setChecked(mCursor.getString(idIndex), isChecked);
                // Risca o texto se checkbox for selecionado
                if(isChecked ){
                    holder.nameTask.setPaintFlags(holder.nameTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.nameTask.setPaintFlags(holder.nameTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }*//*


            }
        });*/

        // Click para cada item da lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onReadingClick(getTaskFromCursor(holder.getAdapterPosition()));
            }
        });


    }

    /*
     * Converte o cursor em uma tarefa
     */
    private Reading getTaskFromCursor(int position) {
        // Move cursor para posicao recebida
        mCursor.moveToPosition(position);

        // Mapeia o index do banco de dados
        int idIndex = mCursor.getColumnIndex(DataBaseReading.ID);
        int nameIndex = mCursor.getColumnIndex(DataBaseReading.READING_NAME);
        int authorIndex = mCursor.getColumnIndex(DataBaseReading.READING_AUTHOR);
        int priorityNameIndex = mCursor.getColumnIndex(DataBaseReading.READING_PRIORITY_NAME);
        int priorityIndex = mCursor.getColumnIndex(DataBaseReading.READING_PRIORITY);
        int genreIndex = mCursor.getColumnIndex(DataBaseReading.READING_GENRE);
        int sourceIndex = mCursor.getColumnIndex(DataBaseReading.READING_SOURCE);
        int statusIndex = mCursor.getColumnIndex(DataBaseReading.READING_STATUS);
        int pagesNumberIndex = mCursor.getColumnIndex(DataBaseReading.READING_PAGES_NUMBER);
        int pagesCurrentIndex = mCursor.getColumnIndex(DataBaseReading.READING_STATUS);

        // Adiciona na tarefa as informacoes recuperadas
        Reading reading = new Reading();
        reading.setId(mCursor.getString(idIndex));
        reading.setReadingName(mCursor.getString(nameIndex));
        reading.setReadingAuthor(mCursor.getString(authorIndex));
        reading.setReadingAuthor(mCursor.getString(authorIndex));
        reading.setReadingPriority(mCursor.getInt(priorityIndex));
        reading.setReadingPriorityName(mCursor.getString(priorityNameIndex));
        reading.setReadingGenre(mCursor.getString(genreIndex));
        reading.setReadingSource(mCursor.getString(sourceIndex));
        reading.setReadingStatus(mCursor.getInt(statusIndex));
        reading.setReadingPagesNumber(mCursor.getInt(pagesNumberIndex));
        reading.setReadingPagesCurrent(mCursor.getInt(pagesCurrentIndex));

        return reading;

    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    /*
     * Atualiza o cursor
     */
    public void swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return; // bc nothing has changed
        } else {
            mCursor = c;
            notifyDataSetChanged();
        }

    }

    /*
     * Objeto necessario para mapear os componentes do layout do item da lista
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView icStatus;
        private TextView tvBookName;
        private ImageView icDelete;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.tvBookName = (TextView) view.findViewById(R.id.tvBookName);
            this.icStatus = (ImageView) view.findViewById(R.id.imgStatus);
            this.icDelete = (ImageView) view.findViewById(R.id.imgDelete);
        }
    }

    /*
    * Interface para o click da tarefa
     */
    public interface OnReadingClickInterface {
        void onReadingClick(Reading item);
    }
}

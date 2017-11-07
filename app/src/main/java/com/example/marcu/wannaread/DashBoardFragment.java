package com.example.marcu.wannaread;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.marcu.wannaread.adapter.ReadingListAdapter;
import com.example.marcu.wannaread.adapter.ReadingListAdapter.OnReadingClickInterface;
import com.example.marcu.wannaread.database.DataBaseControl;
import com.example.marcu.wannaread.database.Reading;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends Fragment implements OnReadingClickInterface {

    private ReadingListAdapter adapter;
    private LinearLayout layoutEmpty;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla o layout correto
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        // Enconta o layout de lista vazia
        layoutEmpty = (LinearLayout) view.findViewById(R.id.layout_empty);

        // Busca a RV da tela e define seu layout
        RecyclerView readingsList = (RecyclerView) view.findViewById(R.id.rv);
        readingsList.setLayoutManager(new LinearLayoutManager(getContext()));

        // Define o adapter da RV
        adapter = new ReadingListAdapter(getContext(), this);
        readingsList.setAdapter(adapter);

        // CLick do fab
        FloatingActionButton fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewReadingActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }


    @Override
    public void onReadingClick(Reading item) {
        // Manda para tela de detalhes ao clicar na tarefa
        Intent intent = new Intent(getContext(), ReadingDetailActivity.class);
        intent.putExtra(ReadingDetailActivity.EXTRA_READING, item);
        startActivity(intent);
    }

    private void loadReadingsFromBD() {
        // Busca tarefas do bd
        DataBaseControl crud = new DataBaseControl(getContext());
        Cursor cursor = crud.loadReadings();
        // Envia apontador para o adapter
        adapter.swapCursor(cursor);

        // Seta o layout da lista se vazia
        if (cursor.getCount() == 0) {
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            layoutEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Atualiza a lista quando retorna a tela
        loadReadingsFromBD();
    }
}

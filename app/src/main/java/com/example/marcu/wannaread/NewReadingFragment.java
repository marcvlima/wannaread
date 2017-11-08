package com.example.marcu.wannaread;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcu.wannaread.database.DataBaseControl;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class NewReadingFragment extends Fragment {

    protected String bookName, author, source, priority, genre, reading;

    protected static EditText etBookName, etAuthor, etSource;
    protected static Spinner spPriority, spGenre;

    public NewReadingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_new_reading, container, false);

        // Finds the elements
        etBookName = (EditText)view.findViewById(R.id.etBookName);
        etAuthor = (EditText)view.findViewById(R.id.etAuthor);
        spPriority = (Spinner)view.findViewById(R.id.spPriority);
        spGenre = (Spinner)view.findViewById(R.id.spGenre);
        etSource = (EditText)view.findViewById(R.id.etSource);

        Button btnSave = (Button)view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DataBaseControl crud = new DataBaseControl(getContext());

                bookName = etBookName.getText().toString();
                author = etAuthor.getText().toString();
                priority = spPriority.getSelectedItem().toString();
                genre = spGenre.getSelectedItem().toString();
                source = etSource.getText().toString();

                reading = crud.addReading(bookName, author, priority, genre, source);

                Toast.makeText(getContext(), reading, Toast.LENGTH_SHORT).show();

                getActivity().finish();
            }
        } );

        return view;
    }


}

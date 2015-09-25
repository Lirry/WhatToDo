package com.example.netbook.whattodo;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> list;
    private ArrayAdapter<String> toDo;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        if(savedInstanceState != null){
            list = savedInstanceState.getStringArrayList("LIST");
        }

        toDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView = (ListView)findViewById(R.id.list_layout);
        listView.setAdapter(toDo);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list,
                                            View row,
                                            int index,
                                            long rowID) {
                        onListItemClick(row, index, rowID);
                    }
                }
        );




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putStringArrayList("LIST", list);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onListItemClick(View row, int index, long rowID){
        list.remove((int) rowID);
        toDo.notifyDataSetChanged();

    }

    public void adding_new_task(View view) {
        EditText new_edittext =(EditText)findViewById(R.id.editText);
        String new_entry = new_edittext.getText().toString();
        list.add(new_entry);

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        new_edittext.setText("");

    }
}

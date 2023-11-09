package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.theme.LinkedList;

public class SecondActivity extends Activity {

    private TextView listTextView;
    private EditText inputEditText;
    private Button addElementButton;
    private EditText deleteEditText;
    private Button deleteElementButton;
    private Button sortAscendingButton;
    private Button sortDescendingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listTextView = findViewById(R.id.listTextView);
        inputEditText = findViewById(R.id.inputEditText);
        addElementButton = findViewById(R.id.addElementButton);
        deleteEditText = findViewById(R.id.deleteEditText);
        deleteElementButton = findViewById(R.id.deleteElementButton);
        sortAscendingButton = findViewById(R.id.sortAscendingButton);
        sortDescendingButton = findViewById(R.id.sortDescendingButton);

        addElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList linkedList = LinkedList.getInstance();
                String input = inputEditText.getText().toString();
                try {
                    int value = Integer.parseInt(input);
                    linkedList.insert(value);
                    updateListDisplay();
                } catch (NumberFormatException e) {
                    // Обработка ошибки, если введено не число
                }
                inputEditText.setText(""); // Очистка поля ввода
            }
        });


        deleteElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList linkedList = LinkedList.getInstance();
                String input = deleteEditText.getText().toString();
                try {
                    int index = Integer.parseInt(input);

                    // Проверяем, существует ли элемент в списке перед удалением
                    if (index >= 0 && index < linkedList.size()) {
                        linkedList.deleteByIndex(index);
                        updateListDisplay();
                    } else {
                        // Используем Toast для показа уведомления
                        Toast.makeText(SecondActivity.this, "Такого индекса не существует!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Обработка ошибки, если введено не число
                }
                deleteEditText.setText(""); // Очистка поля ввода
            }
        });

        sortAscendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList linkedList = LinkedList.getInstance();
                linkedList.sortAscending();
                updateListDisplay();
            }
        });

        sortDescendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList linkedList = LinkedList.getInstance();
                linkedList.sortDescending();
                updateListDisplay();
            }
        });


        updateListDisplay();
    }
    @Override
    public void onBackPressed() {
        LinkedList linkedList = LinkedList.getInstance();
        linkedList.clear(); // Очистить список
        finish(); // Завершить текущую активность и вернуться на MainActivity
    }


    private void updateListDisplay() {
        LinkedList linkedList = LinkedList.getInstance();
        listTextView.setText(linkedList.display());
    }
}

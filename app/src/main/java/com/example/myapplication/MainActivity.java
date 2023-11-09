package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.ui.theme.LinkedList;

public class MainActivity extends Activity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void addElements(View view) {
        String input = editText.getText().toString().trim(); // Удаляем лишние пробелы в начале и конце

        if (input.isEmpty()) {
            // Список пуст, показываем сообщение "Список пуст"
            Toast.makeText(this, "Список пуст", Toast.LENGTH_SHORT).show();
            return; // Выходим из метода
        }

        String[] elements = input.split(",");

        LinkedList linkedList = LinkedList.getInstance();

        for (String element : elements) {
            try {
                int value = Integer.parseInt(element);
                linkedList.insert(value);
            } catch (NumberFormatException e) {
                // Элемент не является числом
            }
        }

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

        editText.setText(""); // Очистить поле ввода
        editText.clearFocus();
    }


    @Override
    public void onBackPressed() {
        // Ничего не делаем, чтобы предотвратить действия при нажатии кнопки "Назад"
    }
}

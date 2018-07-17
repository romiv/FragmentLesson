package com.ivrom.fragmentlesson;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Fragment1.OnSelectedButtonListener {
    public TextView textView;
    public ImageView imageView;
    private boolean mIsDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment2 fragment2 = (Fragment2) fragmentManager
                .findFragmentById(R.id.fragment2);
        mIsDynamic = fragment2 == null || !fragment2.isInLayout();
        Toast.makeText(getApplicationContext(), mIsDynamic + "", Toast.LENGTH_SHORT).show();

        // Зная, что второго фрагмента нет, загружаем первый
        if (mIsDynamic) {
            // начинаем транзакцию
            FragmentTransaction ft = fragmentManager.beginTransaction();
            // Создаем и добавляем первый фрагмент
            Fragment1 fragment1 = new Fragment1();
            ft.add(R.id.LinearLayout1, fragment1, "fragment1");
            // Подтверждаем операцию
            ft.commit();
        }

    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        // подключаем FragmentManager
        FragmentManager frManager = getFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        Fragment2 fragment2 = (Fragment2) frManager.findFragmentById(R.id.fragment2);

        if (mIsDynamic) {
            // динамическое переключение на другой фрагмент
        }
        else {
            // Если фрагмент недоступен
            fragment2 = (Fragment2) frManager.findFragmentById(R.id.fragment2);
            fragment2.setDescription(buttonIndex);
        }


        if (fragment2 == null || !fragment2.isVisible()) {
            // Если фрагмента не существует, или он невидим
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("buttonIndex", buttonIndex);
            startActivity(intent);
        }
        else
            fragment2.setDescription(buttonIndex);
    }
}

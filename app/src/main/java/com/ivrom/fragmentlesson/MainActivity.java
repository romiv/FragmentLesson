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
            ft.add(R.id.container, fragment1, "fragment1");
            // Подтверждаем операцию
            ft.commit();
            Toast.makeText(this, "fragment1 добавлен динамически", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        // подключаем FragmentManager
        FragmentManager frManager = getFragmentManager();

        // Получаем ссылку на второй фрагмент по ID
        Fragment2 fragment2;

        if (mIsDynamic) {
            // динамическое переключение на другой фрагмент
            Toast.makeText(this, "Динамическое переключение с fragment1 на fragment2", Toast.LENGTH_SHORT).show();
            FragmentTransaction fTransaction = frManager.beginTransaction();
            fragment2 = new Fragment2();

            //Подготавливаем аргументы
            Bundle args = new Bundle();
            args.putInt(Fragment2.BUTTON_INDEX, buttonIndex);
            fragment2.setArguments(args);

            fTransaction.replace(R.id.container, fragment2, "fragment2");
            fTransaction.addToBackStack(null);
            fTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            fTransaction.commit();
        } else {
            // Если фрагмент недоступен (девайс в альбомной ориентации)
            fragment2 = (Fragment2) frManager.findFragmentById(R.id.fragment2);
            fragment2.setDescription(buttonIndex);
        }


//        if (fragment2 == null || !fragment2.isVisible()) {
//            // Если фрагмента не существует, или он невидим
//            Intent intent = new Intent(this, SecondActivity.class);
//            intent.putExtra("buttonIndex", buttonIndex);
//            startActivity(intent);
//        } else {
//            fragment2.setDescription(buttonIndex);
//        }
    }
}

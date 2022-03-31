package presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recipeapp.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void buttonGroceryOnClick(View v)
    {
        Intent studentsIntent = new Intent(HomeActivity.this, MainActivity.class);
        HomeActivity.this.startActivity(studentsIntent);
    }

    public void buttonRecipeOnClick()
    {

    }
}
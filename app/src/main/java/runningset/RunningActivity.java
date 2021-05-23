package runningset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.Application.enumtype.ModeType;
import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.activity.SketchBookRunnerActivity;

public class RunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Button imageButton = (Button) findViewById((R.id.nornal_running_btn));
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RunningImageSelectionActivity.class);
            intent.putExtra("runningSetting", new RunningSetting(ModeType.GUIDERUNNER));
            startActivity(intent);
        });

        Button imageButton1 = (Button) findViewById((R.id.project_running_btn));
        imageButton1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RunningImageSelectionActivity.class);
            intent.putExtra("runningSetting", new RunningSetting(ModeType.PROJECTRUNNER));
            startActivity(intent);
        });

        Button imageButton2 = (Button) findViewById((R.id.Sketchbook_running_btn));
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SketchBookRunnerActivity.class);
            intent.putExtra("startType", StartType.NEW);
            startActivity(intent);
        });
    }
}
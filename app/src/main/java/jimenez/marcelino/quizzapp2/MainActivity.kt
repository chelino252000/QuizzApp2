package jimenez.marcelino.quizzapp2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import jimenez.marcelino.quizzapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
        }
        binding.falseButton.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }
        binding.nextButton.setOnClickListener() {
            currentIndex = (currentIndex+1) % questionBank.size
            val questionTextResId = questionBank[currentIndex].textResId
            binding.questionTextView.setText(questionTextResId)
        }

        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}

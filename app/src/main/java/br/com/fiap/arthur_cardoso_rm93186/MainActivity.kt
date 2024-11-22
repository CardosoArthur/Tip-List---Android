package br.com.fiap.arthur_cardoso_rm93186

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.arthur_cardoso_rm93186.viewmodel.TipsAdapter
import br.com.fiap.arthur_cardoso_rm93186.viewmodel.TipsViewModel
import br.com.fiap.arthur_cardoso_rm93186.viewmodel.TipsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TipsViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val tipsAdapter = TipsAdapter { _ ->}
        recyclerView.adapter = tipsAdapter

        val button = findViewById<Button>(R.id.button)
        val title = findViewById<EditText>(R.id.title)
        val description = findViewById<EditText>(R.id.description)

        button.setOnClickListener {
            if (title.text.isEmpty()) {
                title.error = "Preencha um título para a dica"
                return@setOnClickListener
            }

            if (description.text.isEmpty()) {
                description.error = "Insira um descrição da dica"
                return@setOnClickListener
            }

            viewModel.addTip(title.text.toString(), description.text.toString())
            title.text.clear()
            description.text.clear()
        }

        val viewModelFactory = TipsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TipsViewModel::class.java)

        viewModel.tipsLiveData.observe(this) { items ->
            tipsAdapter.updateTips(items.toMutableList())
        }

        val searchView = findViewById<SearchView>(R.id.search_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tipsAdapter.filter(newText ?: "")
                return true
            }
        })


    }
}
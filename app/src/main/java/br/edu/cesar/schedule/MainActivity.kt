package br.edu.cesar.schedule

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val people : MutableList<Person> by lazy { mutableListOf<Person>() }
    private var adapter: ArrayAdapter<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivityForResult(intent, REQUEST_DETAIL_CODE)
        }

        listPeople.setOnItemClickListener { _, _, position, _ ->
            showShortToast(people[position].name)
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, people)
        listPeople.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            val person = data?.getSerializableExtra(DetailActivity.EXTRA_PERSON) as? Person
            if (person != null) {
                people.add(person)
                people.sortBy { it.name }
                people.filter { it.age > 21 }.forEach { Log.d(LOGGER_TAG, it.name) }
                adapter?.notifyDataSetChanged()
            }
        }
    }

    companion object {
        val REQUEST_DETAIL_CODE = 0
        val LOGGER_TAG = "JEMA"
    }
}

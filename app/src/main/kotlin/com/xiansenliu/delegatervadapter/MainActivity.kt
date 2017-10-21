package com.xiansenliu.delegatervadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val items = mutableListOf<Any>()
        val one = ModelOne("type one,position = 0")
        items.add(one)
        for (i in 1..4) {
            if (i % 2 == 0) {
                items.add(ModelOne("type one,position = $i"))
            } else {
                items.add(ModelTwo("type two,position = $i"))
            }
        }
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = DelegateAdapter(items)
        adapter.addHolderDelegate(ModelOneDelegate(), ModelTwoDelegate())
        rv.adapter = adapter

        toolbar.setOnClickListener {
            adapter.removeItems(listOf(one))
        }

    }
}

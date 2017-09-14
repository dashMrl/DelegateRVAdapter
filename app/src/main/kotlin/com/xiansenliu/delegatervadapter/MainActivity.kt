package com.xiansenliu.delegatervadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val items = mutableListOf<Any>()
        for (i in 0..100) {
            if (i % 2 == 0) {
                items.add(ModelOne("type one,position = $i"))
            } else {
                items.add(ModelTwo("type two,position = $i"))
            }
        }
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = BaseAdapter(items)
        adapter.addHolderDelegate(ModelOneDelegate(), ModelTwoDelegate())
        rv.adapter = adapter
    }
}

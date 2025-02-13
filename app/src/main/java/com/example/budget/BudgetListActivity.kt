package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.CartItem

class BudgetListActivity : AppCompatActivity(), SelectedItemsAdapter.OnClickListenerInterface {

    lateinit var cartAdapter: SelectedItemsAdapter
    lateinit var cartItems:ArrayList<CartItem>
    lateinit var cart_recylerview: RecyclerView
    lateinit var checkout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_list)

        val actionbar = supportActionBar
        actionbar!!.title = "Cart"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        cartItems = ArrayList()
        checkout=findViewById(R.id.check_out)
        checkout.setOnClickListener {
            intent = Intent(this, CheckOutActivity::class.java)
            startActivity(intent)
        }
        cart_recylerview = findViewById(R.id.budget_recylerview)
        var database = BudgetDatabase.getInstance(application)
        val layoutManager = LinearLayoutManager(this,)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cart_recylerview.layoutManager = layoutManager

        cart_recylerview.adapter = null

        cartItems = ArrayList()
        database.budgetDao().getAllCartItem().observe(this, Observer {
        cartItems.addAll(it)
            cartAdapter = SelectedItemsAdapter(cartItems, this)
            cart_recylerview.setHasFixedSize(true)
            cart_recylerview.adapter = cartAdapter
            cartAdapter.notifyDataSetChanged()
        }
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(position: Int) {
        val database = BudgetDatabase.getInstance(application)
        database.budgetDao().deleteById(id = position)

    }


}
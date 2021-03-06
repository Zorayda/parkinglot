package com.example.parkinglot.parkinglot.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parkinglot.R
import com.example.parkinglot.databinding.ActivityMainBinding
import com.example.parkinglot.parkinglot.model.ItemBasic
import com.example.parkinglot.parkinglot.model.ItemBasic.Companion.POSITION_CAR
import com.example.parkinglot.parkinglot.model.ItemBasic.Companion.POSITION_INSIDE
import com.example.parkinglot.parkinglot.model.ItemBasic.Companion.POSITION_OUTSIDE
import com.example.parkinglot.parkinglot.model.ItemBasic.Companion.POSITION_PAYMENT
import com.example.parkinglot.vehicle.view.VehicleViewFragment
import com.example.parkinglot.vehicle.view.VehicleViewFragment.Companion.INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE
import com.example.parkinglot.vehicle.view.VehicleViewFragment.Companion.LIST_VEHICLES_VIEW_TYPE
import com.example.parkinglot.vehicle.view.VehicleViewFragment.Companion.OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE
import com.example.parkinglot.vehicle.view.VehicleViewFragment.Companion.VIEW_TYPE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mList: ArrayList<ItemBasic> = ArrayList<ItemBasic>()
    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var genericViewAdapter: GenericViewAdapter
    lateinit var recyclerVehicle: RecyclerView
    lateinit var manager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiceComponents()
    }

    private fun initialiceComponents() {
        // Configuración
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMainActivity.root
        setContentView(view)

        // Lista
        recyclerVehicle = bindingMainActivity.recyclerViewList

        manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerVehicle.layoutManager = manager

        genericViewAdapter =
            GenericViewAdapter(
                this@MainActivity.applicationContext,
                createList()
            )
        genericViewAdapter.setOnClickListener(View.OnClickListener {
            val position: Int = recyclerViewList.getChildAdapterPosition(it)
            goFragment(mList[position])
        })
        recyclerVehicle.adapter = genericViewAdapter
    }

    private fun goFragment(item: ItemBasic) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        when(item.id){
            POSITION_INSIDE -> {
                Log.i("TEST", "DENTRO"+ POSITION_INSIDE)
                val mFragment = VehicleViewFragment()
                val data = Bundle()
                data.putString(VIEW_TYPE, INSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE)
                mFragment.arguments = data
                transaction.add(R.id.frameLayoutContainer, mFragment, "")
            }
            POSITION_OUTSIDE -> {
                Log.i("TEST", "FUERA"+ POSITION_OUTSIDE)
                val mFragment = VehicleViewFragment()
                val data = Bundle()
                data.putString(VIEW_TYPE, OUTSIDE_VEHICLE_PARKINGLOT_VIEW_TYPE)
                mFragment.arguments = data
                transaction.add(R.id.frameLayoutContainer, mFragment, "")
            }
            POSITION_CAR -> {
                Log.i("TEST", "CARRO"+ POSITION_CAR)
                val mFragment = VehicleViewFragment()
                val data = Bundle()
                data.putString(VIEW_TYPE, LIST_VEHICLES_VIEW_TYPE)
                mFragment.arguments = data
                transaction.add(R.id.frameLayoutContainer, mFragment, "")
            }
            POSITION_PAYMENT -> {
                Log.i("TEST", "PAGOS"+ POSITION_PAYMENT)
                Toast.makeText(this, getString(R.string.pr_simamente), Toast.LENGTH_SHORT).show()
            }
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createList(): ArrayList<ItemBasic> {
        val itemBasicInside = ItemBasic()
        val itemBasicOutside = ItemBasic()
        val itemBasicVehicle = ItemBasic()
        val itemBasicMoney = ItemBasic()

        itemBasicInside.id = POSITION_INSIDE
        itemBasicInside.title = getString(R.string.ingreso)
        itemBasicInside.image =
            R.drawable.ic_enter_car_parking_lot

        itemBasicOutside.id = POSITION_OUTSIDE
        itemBasicOutside.title = getString(R.string.salida)
        itemBasicOutside.image =
            R.drawable.ic_logout_car_parking_lot

        itemBasicVehicle.id = POSITION_CAR
        itemBasicVehicle.title = getString(R.string.vehiculos)
        itemBasicVehicle.image =
            R.drawable.ic_car_parking_lot

        itemBasicMoney.id = POSITION_PAYMENT
        itemBasicMoney.title = getString(R.string.recaudo)
        itemBasicMoney.image =
            R.drawable.ic_money_parking_lot

        mList.add(itemBasicInside)
        mList.add(itemBasicOutside)
        mList.add(itemBasicVehicle)
        mList.add(itemBasicMoney)

        return mList
    }
}
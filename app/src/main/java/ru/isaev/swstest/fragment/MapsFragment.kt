package ru.isaev.swstest.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import ru.isaev.swstest.R
import ru.isaev.swstest.databinding.CoffeeShopMapsFragmentBinding

class MapsFragment : Fragment() {

    private lateinit var mBinding: CoffeeShopMapsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey(getString(R.string.maps_api_key))
        MapKitFactory.initialize(this.context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CoffeeShopMapsFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.map.map.move(
            CameraPosition(Point(43.598649, 39.732887), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F), null

        )

    }

    override fun onStart() {
        super.onStart()
        mBinding.map.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mBinding.map.onStop()
        MapKitFactory.getInstance().onStop()
    }

}


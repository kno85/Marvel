import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.acano.marvel.ui.CustomAdapter
import com.acano.marvel.ui.main.MainViewModel
import com.acano.marvel.ui.viewActions
import com.acano.marvel.usecases.UseCases
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

val ITEM_ID: String="item_id"


class HomeFragment : Fragment(), viewActions {
    private lateinit var mview: View
    //View model injection using Koin way
    private val mainViewModel by viewModel<MainViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mview = inflater.inflate(R.layout.fragment_main, container, false)
        setupView()
        return mview
    }

    private fun setupView() {
        val rV = mview.findViewById<RecyclerView>(R.id.rv)
        rV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mainViewModel.heroList.observe(viewLifecycleOwner, Observer<List<Hero>>() {
            rV.adapter = CustomAdapter(it, this)
        })
        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer<String>() {
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onItemClick(itemId: Int) {
        val bundle = bundleOf(ITEM_ID to itemId.toString())

        Navigation.findNavController(mview).navigate(R.id.action_mainFragment_to_detailFragment
            , bundle)
    }



}



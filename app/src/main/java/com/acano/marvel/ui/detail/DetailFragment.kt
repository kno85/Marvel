import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.acano.marvel.R
import com.acano.marvel.domain.Hero
import com.acano.marvel.ui.detail.DetailViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {
    private lateinit var mview: View
    val detailViewModel: DetailViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mview = inflater.inflate(R.layout.fragment_detail, container, false)

        val itemId= arguments?.getString(ITEM_ID)
        detailViewModel.submitHero(Integer.parseInt(itemId!!))
        setupView()
        return mview
    }
    private fun setupView() {
        detailViewModel.hero.observe(this, Observer<Hero>() {
            mview.findViewById<TextView>(R.id.detail_title).text=it.name
            mview.findViewById<TextView>(R.id.detail_description).text=it.description
            val imageView = mview.findViewById<ImageView>(R.id.detail_image)
            Glide.with(this).load(it.image).into(imageView)

        })
        detailViewModel.errorMessage.observe(this, Observer<String>() {
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        })
    }
}



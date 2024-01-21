package tuna.cinergyelite.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import tuna.cinergyelite.R
import tuna.cinergyelite.databinding.FragmentBookingBinding
import tuna.cinergyelite.utils.DeviceIdManager
import tuna.cinergyelite.viewModels.EscapeRoomViewModel
import tuna.core.data.model.request.MovieInfoRequest
import tuna.core.data.model.response.MovieInfoResponse
import tuna.core.data.network.ApiResult


class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    private val bookingViewModel: EscapeRoomViewModel by viewModel()
    private lateinit var movieInfo: MovieInfoResponse

    private val movieId by lazy {
        arguments?.getString("movieId")?:"0"
    }
    private lateinit var dateAdapter: DateAdapter
    private val timeAdapter by lazy {
        TimeAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //set binding to null when fragment is destroyed
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dateAdapter = DateAdapter{
            refreshDateAndTime(it)
        }
        bookingViewModel.getMovieInfo(
            MovieInfoRequest(
                movieId,
                DeviceIdManager.getDeviceId()
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateRv.adapter = dateAdapter
        binding.timeRv.adapter = timeAdapter
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        initObservers()
    }

    private fun initObservers(){
        bookingViewModel.movieInfo.observe(viewLifecycleOwner){
            when(it){
                is ApiResult.Error -> {
                    binding.progressBar.isGone = true
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is ApiResult.Success -> {
                    movieInfo = it.data
                    binding.apply {
                        layoutGroup.isVisible = true
                        progressBar.isGone = true
                        title.text = it.data.movieInfo.title
                        durationRating.text = getString(
                            R.string.ratingDuration,
                            it.data.movieInfo.rating,
                            it.data.movieInfo.runTime
                        )
                        inflateAdapters(it.data)
                    }
                }
            }
        }
    }

    private fun inflateAdapters(item: MovieInfoResponse) {
        Glide.with(requireContext()).load(item.movieInfo.imageUrl).into(binding.poster)
        val list = item.movieInfo.showTimes.map { showTime ->
            DateItem(showTime.date)
        }
        list[0].isSelected = true
        dateAdapter.differ.submitList(list)

        val timeList = movieInfo.movieInfo.showTimes.find { showTime ->
            showTime.date == list[0].date
        }?.sessions
        timeAdapter.differ.submitList(timeList)
    }


    private fun refreshDateAndTime(it: DateItem) {
        val timeList = movieInfo.movieInfo.showTimes.find { showTime ->
            showTime.date == it.date
        }?.sessions
        if (timeList == null) {
            Toast.makeText(requireContext(),
                getString(R.string.no_showtimes_available), Toast.LENGTH_SHORT)
                .show()
            return
        }
        val list = dateAdapter.differ.currentList
        list.forEach { dateItem ->
            dateItem.isSelected = dateItem.date == it.date
        }
        dateAdapter.differ.submitList(list)
        timeAdapter.differ.submitList(timeList)

        dateAdapter.notifyDataSetChanged()
    }


}
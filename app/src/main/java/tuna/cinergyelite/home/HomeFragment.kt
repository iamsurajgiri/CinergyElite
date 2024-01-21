package tuna.cinergyelite.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tuna.cinergyelite.databinding.FragmentHomeBinding
import tuna.cinergyelite.info.InfoFragment
import tuna.cinergyelite.utils.DeviceIdManager
import tuna.cinergyelite.utils.PreferenceHelper
import tuna.cinergyelite.viewModels.EscapeRoomViewModel
import tuna.core.data.model.request.EscapeRoomRequest
import tuna.core.data.network.ApiResult


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var ticketsStr = ""

    private val adapter by lazy {
        EscapeRoomAdapter {item->
            childFragmentManager.let {
                InfoFragment.newInstance(item,ticketsStr).show(it, "info")
            }
        }
    }

    private val homeViewModel: EscapeRoomViewModel by viewModel()

    override fun onDestroyView() {
        super.onDestroyView()
        //set binding to null when fragment is destroyed
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getEscapeRooms(
            EscapeRoomRequest(
                DeviceIdManager.getDeviceId()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            PreferenceHelper.clearData()
            findNavController().popBackStack()
        }

        binding.escapeRoomsRv.adapter = adapter

        initObservers()
    }

    private fun initObservers() {
        homeViewModel.escapeRooms.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is ApiResult.Success -> {
                    adapter.homeDiffer.submitList(it.data.escapeRoomsMovies)
                    ticketsStr = it.data.erTickets
                    binding.progressBar.isGone = true
                }

                is ApiResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isGone = true
                }
            }
        }
    }


}